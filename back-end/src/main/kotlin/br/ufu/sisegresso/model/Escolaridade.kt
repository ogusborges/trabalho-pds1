package br.ufu.sisegresso.model

import jakarta.persistence.*
import java.util.*
import javax.annotation.processing.Generated

@Entity
@Table(name = "escolaridade")
class Escolaridade(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    var instituicao: String? = "",

    var tipo: String? = "",

    var descricao: String? = "",

    @Column(name = "data_ini")
    var dataInicio: Date? = Date(),

    @Column(name = "data_fim")
    var dataFim: Date? = Date(),

    @ManyToOne
    @JoinColumn(name = "egresso_id", referencedColumnName = "id")
    var egresso: Egresso
) {

}