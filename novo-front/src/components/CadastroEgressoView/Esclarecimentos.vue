<script setup lang="ts">
// import { onMounted, ref, watch, type PropType, type Ref, normalizeClass } from 'vue'
// import { useEntityStore } from '@/stores/EntityRegister'
// import type { ValidationError } from '@/service/RequestResponse'

// interface InfoPessoalErrorState {
//   showNomeError: Boolean
//   showSobrenomeError: Boolean
//   showEmailError: Boolean
//   showTipoError: Boolean
// }

// const props = defineProps({
//   errors: {
//     type: Object as PropType<ValidationError[]>,
//     required: true
//   }
// })

// const errorState: Ref<InfoPessoalErrorState> = ref<InfoPessoalErrorState>({
//   showNomeError: false,
//   showSobrenomeError: false,
//   showEmailError: false,
//   showTipoError: false
// })

// watch(
//   () => props.errors,
//   (newValue: ValidationError[], _: ValidationError[]) => {
//     const newErrorObj: InfoPessoalErrorState = errorState.value

//     for (let error of newValue) {
//       let key = `show${
//         error.field.charAt(0).toUpperCase() + error.field.slice(1, error.field.length)
//       }Error`

//       newErrorObj[key as keyof InfoPessoalErrorState] = error.result
//     }

//     errorState.value = newErrorObj
//   }
// )

// const infoPessoais = useEntityStore().infoPessoais

// interface informacoesPessoaisEmit {
//   (eventName: 'changeEntityType', event: Event, entityType: String): void
// }

// const emit = defineEmits<informacoesPessoaisEmit>()
</script>

<template>
  <fieldset class="form-group borda">
    
    <div id="informacoes-esclarecimentos">

      <div class="form-field-group" id="nome">
        <div id="video">
            <iframe id="frame" src="https://www.youtube-nocookie.com/embed/3QsNIaD4RbU" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
        </div>
        <h4 id="esclarecimentos">
            Gostaríamos de convidá-lo a participar de uma iniciativa importante que visa entender o impacto da universidade na sociedade. Como um ex-aluno, 
            você tem uma perspectiva única sobre como sua experiência educacional o preparou para o mundo além da sala de aula. Para nos ajudar a entender 
            melhor esse impacto, estamos solicitando sua colaboração no preenchimento de um formulário com informações sobre sua vida pós-universidade.
            É importante destacar que os interessados nessas informações são as reitorias de graduação e pós-graduação da universidade, que utilizam os 
            dados para avaliar a eficácia de nossos programas e serviços educacionais. O formulário será utilizado para coletar informações sobre sua carreira, 
            atividades de voluntariado, participação em organizações, entre outras informações relevantes. Isso nos permitirá avaliar melhor como a universidade 
            contribuiu para o seu sucesso e como podemos continuar aprimorando nossos processos para melhor atender às necessidades de nossos alunos.
            É importante enfatizar que todas as informações fornecidas serão mantidas em estrita confidencialidade e utilizadas apenas para fins de pesquisa e 
            análise interna. Os dados não serão compartilhados com terceiros, a menos que seja necessário por lei ou por autorização explícita do próprio titular 
            dos dados.
            Além disso, queremos ressaltar que você tem o direito de acessar, corrigir e excluir suas informações pessoais a qualquer momento. Para fazer isso, 
            basta entrar em contato conosco por meio dos canais de comunicação disponibilizados. Você também pode visualizar o termo de consentimento ao 
            clicar no botão "Visualizar Termo".
        </h4>
      </div>
    </div>
  </fieldset>
</template>

<style scoped>
#informacoes-esclarecimentos {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: repeat(1, 1fr);
  gap: 0.5em 1em;
}

#video {
  align-self: center;
  grid-row: 1 / 2;
  margin-bottom: 2%;
}

#esclarecimentos{
  grid-row: 2 / 2;
  text-align: justify;
}

#frame{
  width: 35vw;
  height: 20vw;
}

.borda{
 border: none;
}

</style>
