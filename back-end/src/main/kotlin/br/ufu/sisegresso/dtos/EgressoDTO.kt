package br.ufu.sisegresso.dtos

import java.time.LocalDate

data class EgressoDTO(
    val email: String?,
    val nome: String?,
    val matricula: String?,
    val sobrenome: String?,
    val dataNascimento: LocalDate? = null,
    val contatos: List<ContatoDTO>? = listOf(),
    val expProfissionais: List<ExperienciaProfissionalDTO>? = listOf(),
    val infoAcademicas: List<EscolaridadeDTO>? = listOf(),
)