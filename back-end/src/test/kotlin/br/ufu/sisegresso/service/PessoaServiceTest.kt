package br.ufu.sisegresso.service

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import br.ufu.sisegresso.dtos.AtualizacaoPessoaDTO
import br.ufu.sisegresso.dtos.ContatoDTO
import br.ufu.sisegresso.dtos.RegistroPessoaDTO
import br.ufu.sisegresso.exception.ResourceAlreadyExistsException
import br.ufu.sisegresso.exception.ResourceAttributeInvalidException
import br.ufu.sisegresso.exception.ResourceNotFoundException
import br.ufu.sisegresso.exception.ServiceInternalError
import br.ufu.sisegresso.messages.AppMessages
import br.ufu.sisegresso.messages.PessoaMessage
import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.model.Role
import br.ufu.sisegresso.model.TipoContato
import br.ufu.sisegresso.repository.PessoaRepository
import br.ufu.sisegresso.util.TextUtil
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class PessoaServiceTest {
    @MockK private lateinit var pessoaRepo: PessoaRepository
    @MockK private lateinit var passwordEncoder: PasswordEncoder
    private lateinit var underTest: PessoaService

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
        underTest = PessoaService(pessoaRepo, passwordEncoder)
        mockkObject(TextUtil)
    }

    @AfterEach
    fun tearDown() {
        unmockkObject(TextUtil)
    }

    @Test
    fun cadastrar_ThrowException_PessoaAlreadyExists() {
        val registroPessoa: RegistroPessoaDTO = Fixture.from(RegistroPessoaDTO::class.java)
            .gimme("validRegistroPessoaDTO")

        every { pessoaRepo.existsPessoaByEmail(any()) } returns true

        assertThatExceptionOfType(ResourceAlreadyExistsException::class.java)
            .isThrownBy { underTest.cadastrar(registroPessoa) }
            .withMessage(PessoaMessage.PESSOA_ALREADY_EXISTS.message)
    }

    @Test
    fun cadastrar_ThrowException_CouldNotCreatePassword() {
        val registroPessoa: RegistroPessoaDTO = Fixture.from(RegistroPessoaDTO::class.java)
            .gimme("validRegistroPessoaDTO")

        every { pessoaRepo.existsPessoaByEmail(any()) } returns false
        every { TextUtil.generateRandomString() } returns null

        assertThatExceptionOfType(ServiceInternalError::class.java)
            .isThrownBy { underTest.cadastrar(registroPessoa) }
            .withMessage(AppMessages.DFT_PASSWORD_GENERATION_ERROR.name)
    }

    @Test
    fun cadastrar_PessoaCreated_ValidPessoaDoesNotExist() {
        val registroPessoa: RegistroPessoaDTO = Fixture.from(RegistroPessoaDTO::class.java)
            .gimme("validRegistroPessoaDTO")

        val pessoa: Pessoa = Fixture.from(Pessoa::class.java)
            .gimme("validPessoaEgresso")

        val pessoaSlot = slot<Pessoa>()

        every { pessoaRepo.existsPessoaByEmail(any()) } returns false
        every { TextUtil.generateRandomString() } returns "dummyPassword"
        every { passwordEncoder.encode(any()) } returns "dummyEncodedPassword"
        every { pessoaRepo.save(capture(pessoaSlot)) } returns pessoa

        underTest.cadastrar(registroPessoa)

        assertThat(pessoaSlot.captured).isNotNull()
    }

    @Test
    fun atualizar_ThrowException_PessoaNotFound() {
        val atualizacaoPessoa = AtualizacaoPessoaDTO(
            email = "novoEmail@email.com",
            nome = "Novo Nome",
            sobrenome = "Novo Sobrenome",
            contatos = null,
            dataNascimento = LocalDate.MAX,
            role = Role.EGRESSO,
            senha = "novaSenha"
        )

        every { pessoaRepo.findByEmail(any()) } returns null

        assertThatExceptionOfType(ResourceNotFoundException::class.java)
            .isThrownBy { underTest.atualizar(atualizacaoPessoa) }
            .withMessage(PessoaMessage.PESSOA_NOT_FOUND.message)
    }

    @Test
    fun atualizar_ThrownException_ContactValueNull() {
        val pessoa: Pessoa = Fixture.from(Pessoa::class.java)
            .gimme("validPessoaEgresso")

        val contatoDTO = ContatoDTO(
            id = null,
            tipo = TipoContato.INSTAGRAM,
            valor = null
        )

        val atualizacaoPessoa = AtualizacaoPessoaDTO(
            email = "novoEmail@email.com",
            nome = "Novo Nome",
            sobrenome = "Novo Sobrenome",
            contatos = listOf(contatoDTO),
            dataNascimento = LocalDate.MAX,
            role = Role.EGRESSO,
            senha = "novaSenha"
        )

        every { pessoaRepo.findByEmail(any()) } returns pessoa
        every { passwordEncoder.encode(atualizacaoPessoa.senha) } returns "dummyEncodedPassword"

        assertThatExceptionOfType(ResourceAttributeInvalidException::class.java)
            .isThrownBy { underTest.atualizar(atualizacaoPessoa) }
            .withMessage(PessoaMessage.CONTACT_VALUE_REQUIRED.message)
    }

    @Test
    fun atualizar_ThrownException_ContactTypeNull() {
        val pessoa: Pessoa = Fixture.from(Pessoa::class.java)
            .gimme("validPessoaEgresso")

        val contatoDTO = ContatoDTO(
            id = null,
            tipo = null,
            valor = null
        )

        val atualizacaoPessoa = AtualizacaoPessoaDTO(
            email = "novoEmail@email.com",
            nome = "Novo Nome",
            sobrenome = "Novo Sobrenome",
            contatos = listOf(contatoDTO),
            dataNascimento = LocalDate.MAX,
            role = Role.EGRESSO,
            senha = "novaSenha"
        )

        every { pessoaRepo.findByEmail(any()) } returns pessoa
        every { passwordEncoder.encode(atualizacaoPessoa.senha) } returns "dummyEncodedPassword"

        assertThatExceptionOfType(ResourceAttributeInvalidException::class.java)
            .isThrownBy { underTest.atualizar(atualizacaoPessoa) }
            .withMessage(PessoaMessage.CONTACT_TYPE_REQUIRED.message)
    }

    @Test
    fun atualizar_ThrownException_ContactNotFound() {
        val pessoa: Pessoa = Fixture.from(Pessoa::class.java)
            .gimme("validPessoaEgresso")

        val contatoDTO = ContatoDTO(
            id = 15,
            tipo = TipoContato.INSTAGRAM,
            valor = "@dummyInvalidInstagram"
        )

        val atualizacaoPessoa = AtualizacaoPessoaDTO(
            email = "novoEmail@email.com",
            nome = "Novo Nome",
            sobrenome = "Novo Sobrenome",
            contatos = listOf(contatoDTO),
            dataNascimento = LocalDate.MAX,
            role = Role.EGRESSO,
            senha = "novaSenha"
        )

        every { pessoaRepo.findByEmail(any()) } returns pessoa
        every { passwordEncoder.encode(atualizacaoPessoa.senha) } returns "dummyEncodedPassword"

        assertThatExceptionOfType(ResourceNotFoundException::class.java)
            .isThrownBy { underTest.atualizar(atualizacaoPessoa) }
            .withMessage(PessoaMessage.CONTACT_NOT_FOUND.message)
    }

    @Test
    fun atualizar_PessoaUpdated_PessoaFound() {
        val pessoa: Pessoa = Fixture.from(Pessoa::class.java)
            .gimme("validPessoaEgresso")

        val contatoDTOList = listOf(
            ContatoDTO(id = 10, tipo = TipoContato.CELULAR, valor = "551091000119"),
            ContatoDTO(id = 11, tipo = null, valor = null),
            ContatoDTO(id = null, tipo = TipoContato.TWITTER, valor = "@dummyTwitter"),
            ContatoDTO(id = null, tipo = TipoContato.FACEBOOK, valor = "dummyFacebook"),
        )

        val atualizacaoPessoa = AtualizacaoPessoaDTO(
            email = "novoEmail@email.com",
            nome = "Novo Nome",
            sobrenome = "Novo Sobrenome",
            contatos = contatoDTOList,
            dataNascimento = LocalDate.MAX,
            role = Role.EGRESSO,
            senha = "novaSenha"
        )

        val pessoaSlot = slot<Pessoa>()

        every { pessoaRepo.findByEmail(any()) } returns pessoa
        every { passwordEncoder.encode(atualizacaoPessoa.senha) } returns "dummyEncodedPassword"
        every { pessoaRepo.save(capture(pessoaSlot)) } returns pessoa

        underTest.atualizar(atualizacaoPessoa)

        assertThat(pessoaSlot.captured).isNotNull()
        assertThat(pessoaSlot.captured.contatos.size).isEqualTo(4)
    }
}