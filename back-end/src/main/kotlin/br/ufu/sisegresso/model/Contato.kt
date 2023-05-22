package br.ufu.sisegresso.model

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy
import org.springframework.lang.NonNull
import javax.annotation.processing.Generated

@Entity
@Table(name = "contato", schema = "system")
class Contato(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @NonNull
    var tipo: String = "",

    @NonNull
    var valor: String = "",

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    var pessoa: Pessoa? = null
) {
}