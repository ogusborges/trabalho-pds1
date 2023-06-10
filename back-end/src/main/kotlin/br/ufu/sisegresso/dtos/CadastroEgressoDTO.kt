package br.ufu.sisegresso.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CadastroEgressoDTO(

    @field: NotBlank
    @field: Email
    val email: String?,

    @field: NotBlank
    val matricula: String?,

    @field:NotBlank
    val nome: String?,

    @field: NotBlank
    val sobrenome: String?,
)
