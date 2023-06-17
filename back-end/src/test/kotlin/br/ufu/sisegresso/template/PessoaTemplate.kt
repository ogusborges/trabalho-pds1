package br.ufu.sisegresso.template

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import br.ufu.sisegresso.dtos.AtualizacaoPessoaDTO
import br.ufu.sisegresso.dtos.ContatoDTO
import br.ufu.sisegresso.dtos.RegistroPessoaDTO
import br.ufu.sisegresso.model.Contato
import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.model.Role
import java.time.LocalDate

class PessoaTemplate: TemplateLoader {
    override fun load() {
        val validEgressoRule: Rule = Rule().apply {
            add("id", 10)
            add("email", "gabrielrenatoaparicio@krika.com.br")
            add("nome", "Gabriel")
            add("sobrenome", "Renato Kauê Aparício")
            add("senha", "_123456")
            add("dataNascimento", LocalDate.now())
            add("role", Role.EGRESSO)
            add("contatos", has(3).of(
                Contato::class.java,
                "validContatoEmail",
                "validContatoLinkedin",
                "validContatoCelular"
            ))
        }

        val validInternoRule: Rule = Rule().apply {
            add("id", 10)
            add("email", "gabrielrenatoaparicio@krika.com.br")
            add("nome", "Gabriel")
            add("sobrenome", "Renato Kauê Aparício")
            add("senha", "_123456")
            add("dataNascimento", LocalDate.now())
            add("role", Role.INTERNO)
        }

        val validRegistroPessoaDTO = Rule().apply {
            add("nome", "Gabriel")
            add("email", "gabrielrenatoaparicio@krika.com.br")
            add("sobrenome", "Renato Kauê Aparício")
            add("role", Role.EGRESSO)
        }

        val validAtualizacaoPessoaDTO = Rule().apply {
            add("email", "novoEmail@gmail.com")
            add("nome", "Novo Nome")
            add("sobrenome", "Novo Sobrenome")
        }

        Fixture.of(Pessoa::class.java).addTemplate("validPessoaEgresso", validEgressoRule)
        Fixture.of(Pessoa::class.java).addTemplate("validPessoaInterno", validInternoRule)

        Fixture.of(RegistroPessoaDTO::class.java).addTemplate("validRegistroPessoaDTO", validRegistroPessoaDTO)
        Fixture.of(AtualizacaoPessoaDTO::class.java).addTemplate("validAtualizacaoPessoaDTO", validAtualizacaoPessoaDTO)
    }
}