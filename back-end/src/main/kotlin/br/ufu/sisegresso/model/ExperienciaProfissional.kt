package br.ufu.sisegresso.model

import jakarta.persistence.*
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
    var dataInicio: Date? = Date(),
    var dataFim: Date? = Date(),

    @ManyToOne
    @JoinColumn(name = "egresso_id", referencedColumnName = "id")
    var egresso: Egresso? = null
) {



}