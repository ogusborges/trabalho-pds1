package br.ufu.sisegresso.dtos

import br.ufu.sisegresso.model.Role
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Null
import jakarta.validation.constraints.Past
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.*

data class AtualizacaoPessoaDTO(
    @field:NotBlank
    @field:Email
    val email: String,

    val nome: String?,

    val sobrenome: String?,

    val senha: String?,

    @field:Past
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    @field:Temporal(TemporalType.DATE)
    val dataNascimento: LocalDate?,

    val contatos: List<ContatoDTO>?,

    @field:Null
    @field:Enumerated(EnumType.ORDINAL)
    val role: Role?,
)