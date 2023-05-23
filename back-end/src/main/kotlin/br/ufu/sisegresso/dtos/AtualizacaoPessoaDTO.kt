package br.ufu.sisegresso.dtos

import br.ufu.sisegresso.model.Contato
import br.ufu.sisegresso.model.Funcao
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Past
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.lang.NonNull
import java.util.*

data class AtualizacaoPessoaDTO(
    @field:NonNull
    @field:Email
    val email: String,

    val nome: String?,

    val sobrenome: String?,

    val senha: String?,

    @field:Past
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    @field:Temporal(TemporalType.DATE)
    val dataNascimento: Date?,

    val aceitouTermos: Boolean?,

    val completouCadastro: Boolean?,

    val contatos: List<ContatoDTO>?,

    val funcao: List<FuncaoDTO>?
)