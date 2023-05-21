package br.ufu.sisegresso.service

import br.ufu.sisegresso.dtos.RegistroPessoaDTO
import br.ufu.sisegresso.exception.ResourceAlreadyExistsException
import br.ufu.sisegresso.exception.ServiceInternalError
import br.ufu.sisegresso.messages.Messages
import br.ufu.sisegresso.model.Contato
import br.ufu.sisegresso.model.Funcao
import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.repository.PessoaRepository
import br.ufu.sisegresso.util.TextUtil
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

interface IPessoaService {
    fun cadastrar(dadosPessoa: RegistroPessoaDTO)
}

@Service
class PessoaService(
    private val pessoaRepo: PessoaRepository
) : IPessoaService {
    override fun cadastrar(dadosPessoa: RegistroPessoaDTO) {
        val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

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


}