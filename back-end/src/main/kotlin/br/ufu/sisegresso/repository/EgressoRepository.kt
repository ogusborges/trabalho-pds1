package br.ufu.sisegresso.repository

import br.ufu.sisegresso.model.Egresso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EgressoRepository : JpaRepository<Egresso, Int> {
    @Query(name = """
        SELECT
            E.*
        FROM
            egresso AS E
            
            INNER JOIN system.pessoa AS P
                ON E.pessoa_id = P.id
        WHERE
            P.email = :email
    """)
    fun findByPessoaEmail(@Param("email") email: String): Egresso?

    fun existsEgressoByMatricula(matricula: String): Boolean
}