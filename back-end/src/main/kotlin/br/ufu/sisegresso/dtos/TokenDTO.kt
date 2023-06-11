package br.ufu.sisegresso.dtos

import java.time.LocalDateTime

data class TokenDTO(
    var token: String?,
    var dataExpiracao: LocalDateTime?,
    var expirado: Boolean? = null,
)
