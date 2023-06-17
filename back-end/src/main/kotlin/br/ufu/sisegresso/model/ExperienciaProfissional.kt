package br.ufu.sisegresso.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "exp_profissional")
class ExperienciaProfissional(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    var cargo: String? = "",
    var empresa: String? = "",
    var salario: Double? = null,

    @Column(name = "data_ini")
    var dataInicio: LocalDate = LocalDate.now(),

    @Column(name = "data_fim")
    var dataFim: LocalDate? = null,

    var tecnologias: String? = null,

    @ManyToOne
    @JoinColumn(name = "egresso_id", referencedColumnName = "id")
    var egresso: Egresso? = null
) {



}