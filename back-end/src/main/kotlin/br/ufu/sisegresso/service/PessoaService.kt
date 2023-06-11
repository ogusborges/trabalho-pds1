package br.ufu.sisegresso.service

import br.ufu.sisegresso.dtos.AtualizacaoPessoaDTO
import br.ufu.sisegresso.dtos.RegistroPessoaDTO
import br.ufu.sisegresso.exception.ResourceAlreadyExistsException
import br.ufu.sisegresso.exception.ResourceAttributeInvalidException
import br.ufu.sisegresso.exception.ResourceNotFoundException
import br.ufu.sisegresso.exception.ServiceInternalError
import br.ufu.sisegresso.messages.AppMessages
import br.ufu.sisegresso.messages.PessoaMessage
import br.ufu.sisegresso.model.Contato
import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.repository.PessoaRepository
import br.ufu.sisegresso.util.TextUtil
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

interface IPessoaService {
    fun cadastrar(dadosPessoa: RegistroPessoaDTO): Pessoa
    fun atualizar(dadosAtualizacao: AtualizacaoPessoaDTO)
}

@Service
class PessoaService(
    private val pessoaRepo: PessoaRepository,
    private val passwordEncoder: PasswordEncoder
) : IPessoaService {

    @Transactional
    override fun cadastrar(dadosPessoa: RegistroPessoaDTO): Pessoa {
        println(pessoaRepo.existsPessoaByEmail(dadosPessoa.email))
        if (pessoaRepo.existsPessoaByEmail(dadosPessoa.email)) {
            throw ResourceAlreadyExistsException(
                PessoaMessage.PESSOA_ALREADY_EXISTS.name,
                PessoaMessage.PESSOA_ALREADY_EXISTS.message
            )
        }

        val senhaPadrao = TextUtil.generateRandomString()
            ?: throw ServiceInternalError(AppMessages.DFT_PASSWORD_GENERATION_ERROR.name)

        val pessoa = Pessoa().apply {
            email = dadosPessoa.email
            nome = dadosPessoa.nome
            sobrenome = dadosPessoa.sobrenome
            senha = passwordEncoder.encode(senhaPadrao)
        }

        return pessoaRepo.save(pessoa)
    }

    @Transactional
    override fun atualizar(dadosAtualizacao: AtualizacaoPessoaDTO) {
        val pessoa: Pessoa? = pessoaRepo.findByEmail(dadosAtualizacao.email)

        if (pessoa == null) {
            throw ResourceNotFoundException(
                PessoaMessage.PESSOA_NOT_FOUND.name,
                PessoaMessage.PESSOA_NOT_FOUND.message
            )
        }

        pessoa.apply {
            nome = dadosAtualizacao.nome ?: nome
            sobrenome = dadosAtualizacao.sobrenome ?: sobrenome

            if(dadosAtualizacao.senha != null) {
                senha = passwordEncoder.encode(dadosAtualizacao.senha)
            }

            dataNascimento = dadosAtualizacao.dataNascimento ?: dataNascimento
        }

        if(dadosAtualizacao.contatos != null) {
            val contatos: MutableList<Contato> = pessoa.contatos

            for(contatoInfo in dadosAtualizacao.contatos) {
                if (contatoInfo.id == null) {
                    if (contatoInfo.tipo == null) {
                        throw ResourceAttributeInvalidException(
                            PessoaMessage.CONTACT_TYPE_REQUIRED.name,
                            PessoaMessage.CONTACT_TYPE_REQUIRED.message
                        )
                    }

                    if (contatoInfo.valor == null) {
                        throw ResourceAttributeInvalidException(
                            PessoaMessage.CONTACT_VALUE_REQUIRED.name,
                            PessoaMessage.CONTACT_VALUE_REQUIRED.message
                        )
                    }

                    val contato: Contato = Contato().apply {
                        tipo = contatoInfo.tipo
                        valor = contatoInfo.valor
                        this.pessoa = pessoa
                    }

                    contatos.add(contato)
                } else {
                    val contato = contatos.find { it.id == contatoInfo.id }

                    if (contato == null) {
                        throw ResourceNotFoundException(
                            PessoaMessage.CONTACT_NOT_FOUND.name,
                            PessoaMessage.CONTACT_NOT_FOUND.message
                        )
                    }

                    if (contatoInfo.tipo == null && contatoInfo.valor == null) {
                        contatos.remove(contato)
                    } else {
                        contato.apply {
                            tipo = contatoInfo.tipo ?: tipo
                            valor = contatoInfo.valor ?: valor
                        }
                    }
                }
            }

            pessoa.contatos = contatos
        }

        pessoaRepo.save(pessoa)
    }
}