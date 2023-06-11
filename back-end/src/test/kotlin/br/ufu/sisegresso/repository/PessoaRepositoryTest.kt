package br.ufu.sisegresso.repository

import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.model.Role
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
class PessoaRepositoryTest(
    @Autowired private val pessoaRepository: PessoaRepository
) {

    @BeforeEach
    fun setUp() {
        val pessoa: Pessoa = Pessoa().apply {
            nome = "Diogo"
            sobrenome = "Martin Mário Caldeira"
            email = "diogo_martin_caldeira@mpv.org.br"
            role = Role.EGRESSO
        }

        pessoaRepository.save(pessoa)
    }

    @AfterEach
    fun tearDown() {
        pessoaRepository.deleteAll()
    }

    @Test
    fun shouldReturnThatPessoaExists() {
        val email: String = "diogo_martin_caldeira@mpv.org.br"

        val pessoa: Boolean = pessoaRepository.existsPessoaByEmail(email)

        assertThat(pessoa).isEqualTo(true)
    }
}