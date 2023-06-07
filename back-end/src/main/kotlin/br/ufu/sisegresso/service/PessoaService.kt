package br.ufu.sisegresso.service

import br.ufu.sisegresso.dtos.AtualizacaoPessoaDTO
import br.ufu.sisegresso.dtos.RegistroPessoaDTO
import br.ufu.sisegresso.exception.*
import br.ufu.sisegresso.messages.Messages
import br.ufu.sisegresso.model.Contato
import br.ufu.sisegresso.model.Funcao
import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.repository.PessoaRepository
<<<<<<< HEAD
import br.ufu.sisegresso.util.ReflectionUtils
import br.ufu.sisegresso.util.TextUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

interface IPessoaService {
    fun cadastrar(dadosPessoa: RegistroPessoaDTO)
    fun atualizar(id: Int, dadosAtualizacao: AtualizacaoPessoaDTO)
=======
import br.ufu.sisegresso.util.TextUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

interface IPessoaService {
    fun cadastrar(dadosPessoa: RegistroPessoaDTO)
    fun atualizar(dadosAtualizacao: AtualizacaoPessoaDTO)
>>>>>>> main
}



@Service
class PessoaService(
    private val pessoaRepo: PessoaRepository,
    private val passwordEncoder: PasswordEncoder
) : IPessoaService {

    override fun cadastrar(dadosPessoa: RegistroPessoaDTO) {
        if(pessoaRepo.existsPessoaByEmail(dadosPessoa.email)) {
            throw ResourceAlreadyExistsException(Messages.PESSOA_ALREADY_EXISTS.name)
        }

        val senhaPadrao = TextUtil.generateRandomString()
            ?: throw ServiceInternalError(Messages.DFT_PASSWORD_GENERATION_ERROR.name)

        val pessoa = Pessoa().apply {
            email = dadosPessoa.email
            nome = dadosPessoa.nome
            sobrenome = dadosPessoa.sobrenome
            senha = passwordEncoder.encode(senhaPadrao)
        }

        pessoaRepo.save(pessoa)
    }

<<<<<<< HEAD
    override fun atualizar(id: Int, dadosAtualizacao: AtualizacaoPessoaDTO) {
        val pessoa: Pessoa? = pessoaRepo.findByIdOrNull(id)
=======
    override fun atualizar(dadosAtualizacao: AtualizacaoPessoaDTO) {
        val pessoa: Pessoa? = pessoaRepo.findByEmail(dadosAtualizacao.email)
>>>>>>> main

        if(pessoa == null) {
            throw ResourceNotFoundException(Messages.PESSOA_NOT_FOUND.name)
        }

        pessoa.apply {
            nome = dadosAtualizacao.nome ?: nome
            sobrenome = dadosAtualizacao.sobrenome ?: sobrenome

            if(dadosAtualizacao.senha != null) {
                senha = passwordEncoder.encode(dadosAtualizacao.senha)
            }

            dataNascimento = dadosAtualizacao.dataNascimento ?: dataNascimento
            aceitouTermos = dadosAtualizacao.aceitouTermos ?: aceitouTermos
            completouCadastro = dadosAtualizacao.completouCadastro ?: completouCadastro
        }

        if(dadosAtualizacao.contatos != null) {
            val contatos: MutableList<Contato> = pessoa.contatos

            for(contatoInfo in dadosAtualizacao.contatos) {
                if (contatoInfo.id == null) {
                    if (contatoInfo.tipo == null) {
                        throw ResourceAttributeInvalidException(Messages.CONTACT_TYPE_REQUIRED.name)
                    }

                    if (contatoInfo.valor == null) {
                        throw ResourceAttributeInvalidException(Messages.CONTACT_VALUE_REQUIRED.name)
                    }

                    val contato: Contato = Contato().apply {
                        tipo = contatoInfo.tipo
                        valor = contatoInfo.valor
                        this.pessoa = pessoa
                    }

                    contatos.add(contato)
                } else {
                    var contato = contatos.find { it.id == contatoInfo.id }

                    if (contato == null) {
                        throw ResourceNotFoundException(Messages.CONTACT_NOT_FOUND.name)
                    }

                    if (contatoInfo.tipo == null && contatoInfo.valor == null) {
                        contatos.remove(contato)
                    } else {
                        contato = contato.apply {
                            tipo = contatoInfo.tipo ?: tipo
                            valor = contatoInfo.valor ?: valor
                        }
                    }
                }
            }

            pessoa.contatos = contatos
        }

        if(dadosAtualizacao.funcao != null) {
            val funcoes: MutableList<Funcao> = pessoa.funcoes

            for(funcaoInfo in dadosAtualizacao.funcao) {
                if(funcaoInfo.id == null) {
                    if(funcaoInfo.descricao == null) {
                        throw ResourceAttributeInvalidException(Messages.ROLE_DESCRIPTION_REQUIRED.name)
                    }

                    val funcao: Funcao = Funcao().apply {
                        descricao = funcaoInfo.descricao
                        this.pessoa = pessoa
                    }

                    funcoes.add(funcao)
                } else {
                    var funcao = funcoes.find { it.id == funcaoInfo.id }

                    if (funcao == null) {
                        throw ResourceNotFoundException(Messages.ROLE_NOT_FOUND.name)
                    }

                    if(funcaoInfo.descricao == null) {
                        funcoes.remove(funcao)
                    } else {
                        funcao = funcao.apply {
                            descricao = funcaoInfo.descricao
                        }
                    }
                }
            }

            pessoa.funcoes = funcoes
        }

        pessoaRepo.save(pessoa)
    }
}