package br.ufu.sisegresso.template

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import br.ufu.sisegresso.dtos.ContatoDTO
import br.ufu.sisegresso.model.Contato
import br.ufu.sisegresso.model.TipoContato

class ContatoTemplate: TemplateLoader {
    override fun load() {
        val validContatoEmailRule = Rule().apply {
            add("id", 10)
            add("tipo", TipoContato.EMAIL)
            add("valor", "dummyEmail@gmail.com")
        }

        val validContatoCelularRule = Rule().apply {
            add("id", 11)
            add("tipo", TipoContato.CELULAR)
            add("valor", "5515990001111")
        }

        val validContatoLinkedinRule = Rule().apply {
            add("id", 12)
            add("tipo", TipoContato.LINKEDIN)
            add("valor", "@dummyLinkedin")
        }

//        val validContatoRemoveCelularRule = Rule().apply {
//            add("id", 10L)
//
//        }
//
//        val validContatoAddInstagramRule = Rule().apply {
//            add("tipo", TipoContato.INSTAGRAM)
//            add("valor", "@dummyInstagram")
//        }
//
//        val invalidContatoAddInstagramRule = Rule().apply {
//            add("tipo", TipoContato.INSTAGRAM)
//        }

        Fixture.of(Contato::class.java).addTemplate("validContatoEmail", validContatoEmailRule)
        Fixture.of(Contato::class.java).addTemplate("validContatoCelular", validContatoCelularRule)
        Fixture.of(Contato::class.java).addTemplate("validContatoLinkedin", validContatoLinkedinRule)

        Fixture.of(ContatoDTO::class.java).addTemplate("validContatoDTOEmail", validContatoEmailRule)
        Fixture.of(ContatoDTO::class.java).addTemplate("validContatoDTOCelular", validContatoCelularRule)
        Fixture.of(ContatoDTO::class.java).addTemplate("validContatoDTOEmail", validContatoLinkedinRule)
//        Fixture.of(ContatoDTO::class.java).addTemplate("validContatoDTORemoveCelular", validContatoRemoveCelularRule)
//        Fixture.of(ContatoDTO::class.java).addTemplate("validContatoDTOAddInstagram", validContatoAddInstagramRule)
//        Fixture.of(ContatoDTO::class.java).addTemplate("invalidContatoAddInstagramRule", invalidContatoAddInstagramRule)
    }

}