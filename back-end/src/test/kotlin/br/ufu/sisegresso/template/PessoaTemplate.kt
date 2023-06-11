package br.ufu.sisegresso.template

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.model.Role
import java.util.*

class PessoaTemplate: TemplateLoader {
    override fun load() {
        val validEgressoRule: Rule = Rule().apply {
            add("id", 10)
            add("email", "gabrielrenatoaparicio@krika.com.br")
            add("nome", "Gabriel")
            add("sobrenome", "Renato Kauê Aparício")
            add("senha", "_123456")
            add("dataNascimento", Date())
            add("role", Role.EGRESSO)
        }

        val validInternoRule: Rule = Rule().apply {
            add("id", 10)
            add("email", "gabrielrenatoaparicio@krika.com.br")
            add("nome", "Gabriel")
            add("sobrenome", "Renato Kauê Aparício")
            add("senha", "_123456")
            add("dataNascimento", Date())
            add("role", Role.INTERNO)
        }

        Fixture.of(Pessoa::class.java).addTemplate("validPessoaEgresso", validEgressoRule)
        Fixture.of(Pessoa::class.java).addTemplate("validPessoaInterno", validInternoRule)
    }
}