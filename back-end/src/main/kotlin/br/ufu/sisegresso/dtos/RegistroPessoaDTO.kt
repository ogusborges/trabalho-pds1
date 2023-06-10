package br.ufu.sisegresso.dtos

import br.ufu.sisegresso.messages.Messages
import br.ufu.sisegresso.model.Role
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.lang.NonNull
import java.util.Date

data class RegistroPessoaDTO(
    @field:NotBlank
    val nome: String = "",

    @field:NotBlank
    val sobrenome: String = "",

    @field:NotBlank
    @field:Email
    val email: String = "",


    @field:NonNull
    @field:Enumerated(EnumType.ORDINAL)
    val role: Role? = null,
)