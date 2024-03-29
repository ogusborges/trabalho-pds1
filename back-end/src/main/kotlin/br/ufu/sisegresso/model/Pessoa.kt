package br.ufu.sisegresso.model

import jakarta.persistence.*
import org.springframework.lang.NonNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

@Entity
@Table(name = "pessoa")
class Pessoa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @NonNull
    var nome: String = "",

    @NonNull
    var sobrenome: String = "",

    @NonNull
    var email: String = "",

    var senha: String = "",

    @NonNull
    @Column(name = "data_nasc")
    var dataNascimento: LocalDate = LocalDate.now(),

    @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "pessoa_id")
    var contatos: MutableList<Contato> = mutableListOf(),

    @Enumerated(EnumType.ORDINAL)
    var role: Role? = null,

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