package br.ufu.sisegresso.dtos

import br.ufu.sisegresso.model.Role
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.springframework.lang.NonNull

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