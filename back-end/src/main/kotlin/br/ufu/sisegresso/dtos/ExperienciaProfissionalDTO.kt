package br.ufu.sisegresso.dtos

import br.ufu.sisegresso.model.Egresso
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

data class ExperienciaProfissionalDTO(
    var id: Int?,
    var cargo: String?,
    var empresa: String?,
    var salario: Double?,

    @field: DateTimeFormat(style = "yyyy-MM-dd")
    var dataInicio: Date?,

    @field: DateTimeFormat(style = "yyyy-MM-dd")
    var dataFim: Date?
)