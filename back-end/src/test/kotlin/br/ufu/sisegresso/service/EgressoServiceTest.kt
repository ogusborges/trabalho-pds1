package br.ufu.sisegresso.service

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import br.ufu.sisegresso.dtos.*
import br.ufu.sisegresso.exception.ResourceAlreadyExistsException
import br.ufu.sisegresso.exception.ResourceAttributeInvalidException
import br.ufu.sisegresso.exception.ResourceNotFoundException
import br.ufu.sisegresso.messages.EgressoMessage
import br.ufu.sisegresso.model.Egresso
import br.ufu.sisegresso.model.Escolaridade
import br.ufu.sisegresso.model.ExperienciaProfissional
import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.repository.EgressoRepository
import br.ufu.sisegresso.repository.PessoaRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.assertj.core.api.Assertions.*
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class EgressoServiceTest {
    @MockK private lateinit var pessoaRepo: PessoaRepository
    @MockK private lateinit var pessoaService: PessoaService
    @MockK private lateinit var egressoRepo: EgressoRepository
    @MockK private lateinit var tokenService: TokenService
    @MockK private lateinit var emailService: EmailService
    private lateinit var underTest: EgressoService

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUpFixtures() {
            FixtureFactoryLoader.loadTemplates("br.ufu.sisegresso.template")
        }
    }

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        underTest = EgressoService(pessoaRepo, pessoaService, egressoRepo, tokenService, emailService)
    }

    @Test
    fun cadastrar_ThrowException_EgressoAlreadyExists() {
        val cadastroEgressoDTO = CadastroEgressoDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            nome = "Gabriel",
            sobrenome = "Renato Aparecido",
            matricula = "12345CCC111"
        )

        every { egressoRepo.existsEgressoByMatricula(any()) } returns true

        assertThatExceptionOfType(ResourceAlreadyExistsException::class.java)
            .isThrownBy { underTest.cadastrar(cadastroEgressoDTO) }
            .withMessage(EgressoMessage.EGRESSO_ALREADY_EXISTS.message)
    }

    @Test
    fun cadastrar_EgressoCreated_ValidEgressoData() {
        val cadastroEgressoDTO = CadastroEgressoDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            nome = "Gabriel",
            sobrenome = "Renato Aparecido",
            matricula = "12345CCC111"
        )

        val egresso: Egresso = Fixture.from(Egresso::class.java)
            .gimme("validEgresso")

        val pessoa: Pessoa = Fixture.from(Pessoa::class.java)
            .gimme("validPessoaEgresso")

        val token = TokenDTO(
            token = "a1badb6554a54cc6930db47581391dc4",
            dataExpiracao = LocalDateTime.MAX,
            expirado = false,
            egresso = "12345CCC111"
        )

        every { egressoRepo.existsEgressoByMatricula(any()) } returns false
        every { pessoaRepo.findByEmail(any()) } returns null
        every { pessoaService.cadastrar(any()) } returns pessoa
        every { tokenService.criar(any()) } returns token
        every { emailService.sendEmail(any()) } returns Unit
        every { egressoRepo.save(any()) } returns egresso

        val result = underTest.cadastrar(cadastroEgressoDTO)

        assertThat(result).isNotNull()
    }

    @Test
    fun atualizar_ThrowException_EgressoNotfound() {
        val atualizacaoEgressoDTO = AtualizacaoEgressoDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            aceitouTermos = null,
            completouCadastro = null,
            escolaridades = null,
            expProfissionais = null,
        )

        every { egressoRepo.findByPessoaEmail(any()) } returns null

        assertThatExceptionOfType(ResourceNotFoundException::class.java)
            .isThrownBy { underTest.atualizar(atualizacaoEgressoDTO) }
            .withMessage(EgressoMessage.EGRESSO_NOT_FOUND.message)
    }

    @Test
    fun atualizar_ThrowException_EscolaridadeNotFound() {
        val atualizacaoEgressoDTO = AtualizacaoEgressoDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            aceitouTermos = true,
            completouCadastro = false,
            escolaridades = listOf(
                EscolaridadeDTO(
                    id = 10,
                    instituicao = "Universidade de Maringá",
                    tipo = "Ensino Superior",
                    descricao = "Bacharelado em Sistemas de Informação",
                    dataInicio = LocalDate.of(2019, 8, 9),
                    dataFim = null
                )
            ),
            expProfissionais = null,
        )

        val egresso: Egresso = Fixture.from(Egresso::class.java)
            .gimme("validEgresso")

        every { egressoRepo.findByPessoaEmail(any()) } returns egresso

        assertThatExceptionOfType(ResourceNotFoundException::class.java)
            .isThrownBy { underTest.atualizar(atualizacaoEgressoDTO) }
            .withMessage(EgressoMessage.ESCOLARIDADE_NOT_FOUND.message)
    }

    @Test
    fun atualizar_ThrowException_ExperienciaProfissionalNotFound() {
        val atualizacaoEgressoDTO = AtualizacaoEgressoDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            aceitouTermos = true,
            completouCadastro = false,
            escolaridades = null,
            expProfissionais = listOf(
                ExperienciaProfissionalDTO(
                    id = 10,
                    cargo = "Desenvolvedor Java",
                    empresa = "Sun Microsystems",
                    salario = null,
                    dataInicio = LocalDate.of(2019, 8, 9),
                    dataFim = null,
                    tecnologias = "Java,PostgreSQL"
                )
            ),
        )

        val egresso: Egresso = Fixture.from(Egresso::class.java)
            .gimme("validEgresso")

        every { egressoRepo.findByPessoaEmail(any()) } returns egresso

        assertThatExceptionOfType(ResourceNotFoundException::class.java)
            .isThrownBy { underTest.atualizar(atualizacaoEgressoDTO) }
            .withMessage(EgressoMessage.EXP_PROFISSIONAL_NOT_FOUND.message)
    }

    @Test
    fun atualizar_EgressoUpdated_EgressoFound() {
        val atualizacaoEgressoDTO = AtualizacaoEgressoDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            aceitouTermos = true,
            completouCadastro = false,
            escolaridades = listOf(
                EscolaridadeDTO(
                    id = 10,
                    instituicao = "Universidade de Maringá",
                    tipo = "Ensino Superior",
                    descricao = "Bacharelado em Sistemas de Informação",
                    dataInicio = LocalDate.of(2019, 8, 9),
                    dataFim = null
                ),
                EscolaridadeDTO(
                    id = null,
                    instituicao = "Alura Cursos Online",
                    tipo = "Ensino Técnico",
                    descricao = "Curso de Javascript",
                    dataInicio = LocalDate.of(2016, 5, 10),
                    dataFim = LocalDate.of(2016, 6, 10)
                ),
                EscolaridadeDTO(
                    id = 11,
                    instituicao = null,
                    tipo = null,
                    descricao = null,
                    dataInicio = null,
                    dataFim = null
                )
            ),
            expProfissionais = listOf(
                ExperienciaProfissionalDTO(
                    id = 10,
                    cargo = "Desenvolvedor Java",
                    empresa = "Sun Microsystems",
                    salario = null,
                    dataInicio = LocalDate.of(2019, 8, 9),
                    dataFim = null,
                    tecnologias = "Java,PostgreSQL"
                ),
                ExperienciaProfissionalDTO(
                    id = null,
                    cargo = "Desenvolvedor PHP",
                    empresa = "Facebook",
                    salario = 2000.45,
                    dataInicio = LocalDate.of(2018, 1, 10),
                    dataFim = LocalDate.of(2019, 8, 1),
                    tecnologias = "Java,PostgreSQL"
                ),
                ExperienciaProfissionalDTO(
                    id = 11,
                    cargo = null,
                    empresa = null,
                    salario = null,
                    dataInicio = null,
                    dataFim = null,
                    tecnologias = null
                ),
            ),
        )

        val egresso: Egresso = Fixture.from(Egresso::class.java)
            .gimme("validEgresso")

        egresso.experienciaProf.add(
            ExperienciaProfissional(
                id = 10,
                cargo = "Desenvolvedor ?",
                egresso = egresso
            ),
        )

        egresso.experienciaProf.add(
            ExperienciaProfissional(
                id = 11,
                cargo = "Desenvolvedor X",
                egresso = egresso
            ),
        )

        egresso.escolaridades.add(
            Escolaridade(
                id = 10,
                instituicao = "Universidade do Ceará",
                egresso = egresso
            ),
        )

        egresso.escolaridades.add(
            Escolaridade(
                id = 11,
                instituicao = "Universidade de Uberlândia",
                egresso = egresso
            )
        )

        val egressoSlot = slot<Egresso>()

        every { egressoRepo.findByPessoaEmail(any()) } returns egresso
        every { egressoRepo.save(any()) } returns egresso

        underTest.atualizar(atualizacaoEgressoDTO)

        verify(exactly = 1) { egressoRepo.save(capture(egressoSlot)) }

        val result = egressoSlot.captured

        assertThat(result).isNotNull()
        assertThat(result.escolaridades.size).isEqualTo(2)
        assertThat(result.experienciaProf.size).isEqualTo(2)
    }

    @Test
    fun recuperar_ThrowException_InvalidQueryType() {
        val recuperarEgressoDTO = RecuperarEgressoDTO(
            email = null,
            token = null,
            matricula = null,
            tipoBusca = null
        )

        assertThatExceptionOfType(ResourceAttributeInvalidException::class.java)
            .isThrownBy { underTest.recuperar(recuperarEgressoDTO) }
            .withMessage(EgressoMessage.INVALID_QUERY_TYPE.message)
    }

    @Test
    fun recuperar_ThrowException_EgressoNotFound() {
        val recuperarEgressoDTO = RecuperarEgressoDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            token = null,
            matricula = null,
            tipoBusca = TipoBuscaEgresso.EMAIL
        )

        every { egressoRepo.findByPessoaEmail(any()) } returns null

        assertThatExceptionOfType(ResourceNotFoundException::class.java)
            .isThrownBy { underTest.recuperar(recuperarEgressoDTO) }
            .withMessage(EgressoMessage.EGRESSO_NOT_FOUND.message)
    }

    @Test
    fun recuperar_EgressoRecovered_EgressoFound() {
        var recuperarEgressoDTO = RecuperarEgressoDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            matricula = "1234BSI200",
            token = null,
            tipoBusca = TipoBuscaEgresso.MATRICULA
        )

        val egresso: Egresso = Fixture.from(Egresso::class.java)
            .gimme("validEgresso")

        egresso.experienciaProf.add(
            ExperienciaProfissional(
                id = 10,
                cargo = "Desenvolvedor ?",
                egresso = egresso
            ),
        )

        egresso.escolaridades.add(
            Escolaridade(
                id = 10,
                instituicao = "Universidade do Ceará",
                egresso = egresso
            ),
        )

        val token = TokenDTO(
            token = "a1badb6554a54cc6930db47581391dc4",
            dataExpiracao = LocalDateTime.MAX,
            expirado = false,
            egresso = "12345CCC111"
        )

        every { egressoRepo.findByPessoaEmail(any()) } returns egresso
        every { tokenService.recuperar(any()) } returns token
        every { egressoRepo.findByMatricula(any()) } returns egresso

        val resultRecoverByMatricula = underTest.recuperar(recuperarEgressoDTO)

        recuperarEgressoDTO = recuperarEgressoDTO.copy(
            token = "a1badb6554a54cc6930db47581391dc4",
            tipoBusca = TipoBuscaEgresso.TOKEN
        )

        val resultRecoverByToken = underTest.recuperar(recuperarEgressoDTO)

        assertThat(resultRecoverByMatricula).isNotNull()
        assertThat(resultRecoverByToken).isNotNull()
    }
}