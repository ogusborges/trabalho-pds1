package br.ufu.sisegresso.dtos

import br.ufu.sisegresso.model.Pessoa
import jakarta.persistence.*
import org.springframework.lang.NonNull

data class FuncaoDTO(
    val id: Int?,
    val descricao: String?
)
