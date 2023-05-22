package br.ufu.sisegresso.repository

import br.ufu.sisegresso.model.Pessoa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PessoaRepository : JpaRepository<Pessoa, Int> {

    fun existsPessoaByEmail(email: String): Boolean
}