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
    private var id: Int,

    @NonNull
    private var tipo: String,

    @NonNull
    private var valor: String,

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private var pessoa: Pessoa
) {
}