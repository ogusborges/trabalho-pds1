package br.ufu.sisegresso.service

import br.ufu.sisegresso.dtos.CriarTokenDTO
import br.ufu.sisegresso.dtos.RegenerarTokenDTO
import br.ufu.sisegresso.dtos.TipoBuscaDTO
import br.ufu.sisegresso.dtos.TokenDTO
import br.ufu.sisegresso.exception.ResourceAlreadyExistsException
import br.ufu.sisegresso.exception.ResourceAttributeInvalidException
import br.ufu.sisegresso.exception.ResourceNotFoundException
import br.ufu.sisegresso.exception.ServiceInternalError
import br.ufu.sisegresso.messages.AppMessages
import br.ufu.sisegresso.messages.EgressoMessage
import br.ufu.sisegresso.messages.TokenMessage
import br.ufu.sisegresso.model.Token
import br.ufu.sisegresso.repository.EgressoRepository
import br.ufu.sisegresso.repository.TokenRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

interface ITokenService {
    fun criar(dadosToken: CriarTokenDTO): TokenDTO
    fun recuperar(token: String): TokenDTO
    fun regenerar(dadosToken: RegenerarTokenDTO)
}

@Service
class TokenService(
    private val tokenRepo: TokenRepository
): ITokenService {
    private final val tokenExpirationDays = 30L

    @Transactional
    override fun criar(dadosToken: CriarTokenDTO): TokenDTO {
        if (tokenRepo.existsByEgressoMatricula(dadosToken.egresso.matricula!!)) {
            throw ResourceAlreadyExistsException(
                TokenMessage.TOKEN_ALREADY_EXISTS.name,
                TokenMessage.TOKEN_ALREADY_EXISTS.message
            )
        }

        val dataExpiracao = LocalDateTime.now()
            ?.atOffset(ZoneOffset.UTC)
            ?.plusDays(tokenExpirationDays)
            ?.toLocalDateTime()

        if (dataExpiracao == null) {
            throw ServiceInternalError(AppMessages.LOCAL_DATE_TIME_ERROR.name)
        }

        var tokenValue = UUID.randomUUID().toString().replace("-", "")

        while (tokenRepo.existsByToken(tokenValue)) {
            tokenValue = UUID.randomUUID().toString().replace("-", "")
        }

        val token = Token(
            token = tokenValue,
            egresso = dadosToken.egresso,
            dataExpiracao = dataExpiracao
        )

        val savedToken = tokenRepo.save(token)

        return TokenDTO(
            token = savedToken.token,
            dataExpiracao = savedToken.dataExpiracao,
            expirado = savedToken.expirado,
            egresso = savedToken.egresso?.matricula,
        )
    }

    override fun recuperar(token: String): TokenDTO {
        val recoveredToken = tokenRepo.findByToken(token)

        if(recoveredToken == null) {
            throw ResourceNotFoundException(
                TokenMessage.TOKEN_NOT_FOUND.name,
                TokenMessage.TOKEN_NOT_FOUND.message
            )
        }

        return TokenDTO(
            token = recoveredToken.token,
            dataExpiracao = recoveredToken.dataExpiracao,
            expirado = recoveredToken.expirado,
            egresso = recoveredToken.egresso?.matricula
        )
    }

    @Transactional
    override fun regenerar(dadosToken: RegenerarTokenDTO) {
        val token = when(dadosToken.tipoBusca) {
            TipoBuscaDTO.TOKEN -> dadosToken.token?.let { tokenRepo.findByToken(it) }
            TipoBuscaDTO.EMAIL -> dadosToken.email?.let { tokenRepo.findByEgressoPessoaEmail(it) }
            else -> throw ResourceAttributeInvalidException(
                TokenMessage.INVALID_QUERY_TYPE.name,
                TokenMessage.INVALID_QUERY_TYPE.message
            )
        }

        if (token == null) {
            throw ResourceNotFoundException(
                TokenMessage.TOKEN_NOT_FOUND.name,
                TokenMessage.TOKEN_NOT_FOUND.message
            )
        }

        token.dataExpiracao = LocalDateTime.now()
            ?.atOffset(ZoneOffset.UTC)
            ?.plusDays(tokenExpirationDays)
            ?.toLocalDateTime()

        if (token.dataExpiracao == null) {
            throw ServiceInternalError(AppMessages.LOCAL_DATE_TIME_ERROR.name)
        }

        token.expirado = false

        tokenRepo.save(token)
    }

}