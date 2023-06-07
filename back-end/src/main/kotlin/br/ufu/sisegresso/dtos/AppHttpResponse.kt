package br.ufu.sisegresso.dtos

data class RespostaHttp(
    val errors: List<Erro> = listOf(),
    val data: List<Any> = listOf(),
)
