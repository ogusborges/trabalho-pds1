package br.ufu.sisegresso.dtos

import br.ufu.sisegresso.model.Pessoa
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.springframework.lang.NonNull

data class ContatoDTO(
    val id: Int?,
    val tipo: String?,
    val valor: String?
)