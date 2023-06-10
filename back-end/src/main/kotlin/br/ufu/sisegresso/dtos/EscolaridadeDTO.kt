package br.ufu.sisegresso.dtos

import org.springframework.format.annotation.DateTimeFormat
import java.util.*

data class EscolaridadeDTO(
    var id: Int?,
    var instituicao: String?,
    var tipo: String?,
    var descricao: String?,

    @field: DateTimeFormat(style = "yyyy-MM-dd")
    var dataInicio: Date?,

    @field: DateTimeFormat(style = "yyyy-MM-dd")
    var dataFim: Date?
)