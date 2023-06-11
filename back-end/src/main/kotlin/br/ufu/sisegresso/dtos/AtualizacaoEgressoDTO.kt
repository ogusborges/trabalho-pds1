package br.ufu.sisegresso.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class AtualizacaoEgressoDTO(
    @field: NotBlank
    @field: Email
    val email: String?,

    val aceitouTermos: Boolean?,
    val completouCadastro: Boolean?,

    val escolaridades: List<EscolaridadeDTO>?,
    val expProfissionais: List<ExperienciaProfissionalDTO>?
)