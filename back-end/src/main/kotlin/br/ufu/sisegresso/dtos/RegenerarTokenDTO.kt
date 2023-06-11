package br.ufu.sisegresso.dtos

import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.validation.constraints.Email

data class RegenerarTokenDTO(
    @field:Email
    var email: String? = "",

    var token: String? = "",

    var tipoBusca: TipoBuscaDTO? = null
)
