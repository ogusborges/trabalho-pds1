package br.ufu.sisegresso.repository

import br.ufu.sisegresso.model.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TokenRepository: JpaRepository<Token, Int> {
    fun existsByToken(token: String): Boolean
    fun existsByEgressoMatricula(matricula: String): Boolean
    fun findByToken(token: String): Token?
    fun findByEgressoPessoaEmail(email: String): Token?
}