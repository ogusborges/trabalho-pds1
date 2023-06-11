package br.ufu.sisegresso.model
import jakarta.persistence.*
import org.springframework.lang.NonNull
import java.util.*


@Entity
@Table(name = "egresso")

class Egresso(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @field:NonNull
    @field:Column(unique = true)
    var matricula: String? = "",

    @field: Column(name = "data_ult_notif")
    var dataUltNotificacao: Date? = Date(),

    var aceitouTermos: Boolean? = false,

    var completouCadastro: Boolean? = false,

    @OneToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    var pessoa: Pessoa? = null,

    @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "egresso_id")
    var escolaridades: MutableList<Escolaridade> = mutableListOf(),

    @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "egresso_id")
    var experienciaProf: MutableList<ExperienciaProfissional> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Egresso

        return id == other.id
    }

    override fun hashCode(): Int {
        return id ?: 0
    }
}