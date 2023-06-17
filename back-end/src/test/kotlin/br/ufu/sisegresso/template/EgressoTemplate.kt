package br.ufu.sisegresso.template

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import br.ufu.sisegresso.dtos.CadastroEgressoDTO
import br.ufu.sisegresso.model.Egresso
import br.ufu.sisegresso.model.Pessoa
import java.util.*

class EgressoTemplate: TemplateLoader {
    override fun load() {
        val validRule = Rule().apply {
            add("id", 10)
            add("matricula", "1234BSI200")
            add("dataUltNotificacao", Date())
            add("completouCadastro", false)
            add("aceitouTermos", false)
            add("pessoa", one(Pessoa::class.java, "validPessoaEgresso"))
        }

        val validCadastroEgressoDTORule = Rule().apply {
            add("email", "gabrielrenatoaparicio@krika.com.br")
            add("matricula", "12345CCC111")
            add("nome", "Gabriel")
            add("sobrenome", "Renato Aparecido")
        }

        Fixture.of(Egresso::class.java).addTemplate("validEgresso", validRule)
        Fixture.of(CadastroEgressoDTO::class.java).addTemplate("validCadastroEgressoDTO", validCadastroEgressoDTORule)
    }
}