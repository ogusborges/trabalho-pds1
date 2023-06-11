package br.ufu.sisegresso.template

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import br.ufu.sisegresso.model.Egresso
import br.ufu.sisegresso.model.Token
import java.time.LocalDateTime

class TokenTemplate: TemplateLoader {
    override fun load() {
        val validRule = Rule().apply {
           add("id", 10)
           add("token", "a1badb6554a54cc6930db47581391dc4")
           add("dataExpiracao", LocalDateTime.MAX)
           add("egresso", one(Egresso::class.java, "validEgresso"))
        }

        val validExpiratedRule = Rule().apply {
            add("id", 10)
            add("token", "a1badb6554a54cc6930db47581391dc4")
            add("dataExpiracao", LocalDateTime.MIN)
            add("egresso", one(Egresso::class.java, "validEgresso"))
        }

        Fixture.of(Token::class.java).addTemplate("validExpiratedToken", validExpiratedRule)
        Fixture.of(Token::class.java).addTemplate("validToken", validRule)
    }

}