package br.ufu.sisegresso.model

import jakarta.persistence.*
import org.springframework.lang.NonNull

@Entity
@Table(name = "funcao", schema = "system")
class Funcao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @NonNull
    var descricao: String = "",

    @NonNull
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    var pessoa: Pessoa? = null,
) {

}
