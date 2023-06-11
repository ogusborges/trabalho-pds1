package br.ufu.sisegresso.service

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import br.ufu.sisegresso.dtos.CriarTokenDTO
import br.ufu.sisegresso.dtos.RegenerarTokenDTO
import br.ufu.sisegresso.dtos.TipoBuscaDTO
import br.ufu.sisegresso.dtos.TokenDTO
import br.ufu.sisegresso.exception.ResourceAlreadyExistsException
import br.ufu.sisegresso.exception.ResourceAttributeInvalidException
import br.ufu.sisegresso.exception.ResourceNotFoundException
import br.ufu.sisegresso.messages.TokenMessage
import br.ufu.sisegresso.model.Egresso
import br.ufu.sisegresso.model.Token
import br.ufu.sisegresso.repository.TokenRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class TokenServiceTest(
    @Mock private val tokenRepo: TokenRepository,
) {
    private var underTest: TokenService = TokenService(tokenRepo)

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUpFixtures() {
            FixtureFactoryLoader.loadTemplates("br.ufu.sisegresso.template")
        }
    }

    @Test
    fun criarToken_ThrowException_TokenAlreadyExists() {
        val egresso: Egresso = Fixture.from(Egresso::class.java)
            .gimme("validEgresso")

        Mockito.`when`(tokenRepo.existsByEgressoMatricula(Mockito.anyString()))
            .thenReturn(true)

        val expectedMessage = TokenMessage.TOKEN_ALREADY_EXISTS.message

        Assertions.assertThatExceptionOfType(ResourceAlreadyExistsException::class.java)
            .isThrownBy { underTest.criarToken(CriarTokenDTO(egresso)) }
            .withMessage(expectedMessage)
    }

    @Test
    fun criarToken_TokenCreated_EgressoExists() {
        val egresso: Egresso = Fixture.from(Egresso::class.java)
            .gimme("validEgresso")

        val argCaptor: ArgumentCaptor<Token> = ArgumentCaptor.forClass(Token::class.java)

        Mockito.`when`(tokenRepo.existsByEgressoMatricula(Mockito.anyString()))
            .thenReturn(false)

        // Returns true the first time to test while loop
        Mockito.`when`(tokenRepo.existsByToken(Mockito.anyString()))
            .thenReturn(true)
            .thenReturn(false)

        underTest.criarToken(CriarTokenDTO(egresso))

        Mockito.verify(tokenRepo, times(1)).save(argCaptor.capture())

        Assertions.assertThat(argCaptor.value).isNotNull()
    }

    @Test
    fun recuperarToken_TokenRecovered_TokenFound() {
        val token: Token = Fixture.from(Token::class.java)
            .gimme("validToken")

        val expected = TokenDTO(
            token = token.token,
            dataExpiracao = token.dataExpiracao,
            expirado = token.expirado
        )

        Mockito.`when`(tokenRepo.findByToken(Mockito.anyString()))
            .thenReturn(token)

        val result = underTest.recuperarToken(token.token!!)

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun recuperarToken_ThrowException_TokenNotFound() {
        val token: Token = Fixture.from(Token::class.java)
            .gimme("validToken")

        Assertions.assertThatExceptionOfType(ResourceNotFoundException::class.java)
            .isThrownBy { underTest.recuperarToken(token.token!!) }
            .withMessage(TokenMessage.TOKEN_NOT_FOUND.message)
    }

    @Test
    fun renegerarToken_ThrowException_QueryTypeNotValid() {
        val param = RegenerarTokenDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            tipoBusca = null
        )

        Assertions.assertThatExceptionOfType(ResourceAttributeInvalidException::class.java)
            .isThrownBy { underTest.regenerarToken(param) }
            .withMessage(TokenMessage.INVALID_QUERY_TYPE.message)
    }

    @Test
    fun renegerarToken_ThrowException_TokenNotFound() {
        val param = RegenerarTokenDTO(
            email = "gabrielrenatoaparicio@krika.com.br",
            tipoBusca = TipoBuscaDTO.EMAIL
        )

        Mockito.`when`(tokenRepo.findByEgressoPessoaEmail(param.email!!))
            .thenReturn(null)

        Assertions.assertThatExceptionOfType(ResourceNotFoundException::class.java)
            .isThrownBy { underTest.regenerarToken(param) }
            .withMessage(TokenMessage.TOKEN_NOT_FOUND.message)
    }

    @Test
    fun renegerarToken_TokenRegenerated_TokenFound() {
        val token: Token = Fixture.from(Token::class.java)
            .gimme("validExpiratedToken")

        val param = RegenerarTokenDTO(
            token = token.token,
            tipoBusca = TipoBuscaDTO.TOKEN
        )

        val expiredTokenDate = token.dataExpiracao

        val argCaptor: ArgumentCaptor<Token> = ArgumentCaptor.forClass(Token::class.java)

        Mockito.`when`(tokenRepo.findByToken(param.token!!))
            .thenReturn(token)

        underTest.regenerarToken(param)

        Mockito.verify(tokenRepo, times(1)).save(argCaptor.capture())

        val result = argCaptor.value

        Assertions.assertThat(result.dataExpiracao).isAfter(expiredTokenDate)
    }
}