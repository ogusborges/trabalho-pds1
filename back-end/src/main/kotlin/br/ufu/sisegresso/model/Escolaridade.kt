package br.ufu.sisegresso.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*
import javax.annotation.processing.Generated

@Entity
@Table(name = "escolaridade")
class Escolaridade(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    var instituicao: String = "",

    var tipo: String = "",

    var descricao: String = "",

    @Column(name = "data_ini")
    var dataInicio: LocalDate = LocalDate.now(),

    @Column(name = "data_fim")
    var dataFim: LocalDate? = LocalDate.now(),

    @ManyToOne
    @JoinColumn(name = "egresso_id", referencedColumnName = "id")
    var egresso: Egresso
) {

}