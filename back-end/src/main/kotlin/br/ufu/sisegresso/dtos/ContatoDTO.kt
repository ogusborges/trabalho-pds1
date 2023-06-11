package br.ufu.sisegresso.dtos

import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.model.TipoContato
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Null
import org.springframework.lang.NonNull

data class ContatoDTO(
    val id: Int?,

    @field:Null
    @field:Enumerated(EnumType.ORDINAL)
    val tipo: TipoContato?,

    val valor: String?
)