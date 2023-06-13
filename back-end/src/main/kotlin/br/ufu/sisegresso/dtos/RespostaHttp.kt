package br.ufu.sisegresso.dtos

data class RespostaHttp(
    val status: Int = 200,
    val errorCount: Int = 0,
    val dataCount: Int = 0,
    val errors: List<Erro> = listOf(),
    val data: List<Any> = listOf(),
)
