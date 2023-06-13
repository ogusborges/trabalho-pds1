package br.ufu.sisegresso.dtos

data class EmailDTO(
    val recipient: String?,
    val subject: String?,
    val messageBody: String?,
    val attachment: String? = null,
)