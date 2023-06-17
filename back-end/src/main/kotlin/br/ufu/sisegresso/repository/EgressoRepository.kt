package br.ufu.sisegresso.repository

import br.ufu.sisegresso.model.Egresso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EgressoRepository : JpaRepository<Egresso, Int> {
    fun findByPessoaEmail(email: String): Egresso?
    fun findByMatricula(matricula: String): Egresso?
    fun existsEgressoByMatricula(matricula: String): Boolean
}