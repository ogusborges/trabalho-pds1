package br.ufu.sisegresso.dtos

import jakarta.validation.constraints.Email

data class RecuperarEgressoDTO (
    @field:Email
    val email: String?,

    val token: String?,

    val matricula: String?,

    val tipoBusca: TipoBuscaEgresso? = null
)