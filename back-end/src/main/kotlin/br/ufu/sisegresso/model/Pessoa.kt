package br.ufu.sisegresso.model

import jakarta.persistence.*
import org.springframework.lang.NonNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

@Entity
@Table(name = "pessoa", schema = "system")
class Pessoa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,

    @NonNull
    var nome: String? = "",

    @NonNull
    var sobrenome: String? = "",

    @NonNull
    @Column(name = "email")
    var email: String = "",

    @Column(name = "password")
    var senha: String = "",

    @NonNull
    @Column(name = "data_nasc")
    var dataNascimento: Date = Date(0),

    @OneToMany
    var contatos: List<Contato> = listOf(),

    @NonNull
    @OneToMany
    var funcoes: List<Funcao> = listOf(),

    @NonNull
    var aceitouTermo: Boolean = false,

    @NonNull
    var completouCadastro: Boolean = false,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }

}