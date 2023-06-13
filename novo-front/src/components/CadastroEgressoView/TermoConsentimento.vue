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
    
    <div id="informacoes-termo">

      <div class="form-field-group">

            <h2>Convite ao Egresso</h2>
            <p>
                Gostaríamos de convidá-lo a participar de uma iniciativa importante que visa entender o impacto da universidade na sociedade. Como um ex-aluno,
                você tem uma perspectiva única sobre como sua experiência educacional o preparou para o mundo além da sala de aula. Para nos ajudar a entender 
                melhor esse impacto, estamos solicitando sua colaboração no preenchimento de um formulário com informações sobre sua vida pós-universidade.
            </p>
            <p>    
                É importante ressaltar que todas as informações fornecidas serão restritas ao âmbito da reitoria de graduação e pós-graduação da universidade,
                que as utilizarão para avaliar a eficácia de nossos programas e serviços educacionais. O formulário será utilizado para coletar informações sobre
                sua carreira, atividades de voluntariado, participação em organizações, entre outras informações relevantes. Isso nos permitirá avaliar melhor como
                a universidade contribuiu para o seu sucesso e como podemos continuar aprimorando nossos processos para melhor atender às necessidades de nossos alunos.
            </p>
            <p>
                É importante enfatizar que todas as informações fornecidas serão mantidas em estrita confidencialidade e utilizadas apenas para fins de pesquisa e análise
                interna. Os dados não serão compartilhados com terceiros, a menos que seja necessário por lei ou com autorização explícita do próprio titular dos dados.
            </p>   
            <p>
                Além disso, queremos ressaltar que você tem o direito de acessar, corrigir e excluir suas informações pessoais a qualquer momento. Para fazer isso, basta
                entrar em contato conosco por meio dos canais de comunicação disponibilizados. Você também pode visualizar o termo de consentimento ao clicar no botão
                "Ver Termo". 
            </p>
         
            <h2>Termo de Consentimento para Utilização do Aplicativo</h2>

            <p>Este <strong>Termo de Consentimento</strong> ("Termo") estabelece os termos e condições para o uso do aplicativo web de gestão de matrículas ("Aplicativo") e a coleta de dados profissionais, acadêmicos e pessoais dos usuários ("Alunos"). O Aplicativo é disponibilizado pela Universidade Federal de Uberlândia, e ao utilizar o Aplicativo, o Aluno concorda com os termos aqui apresentados.</p>

            <h3>1. Coleta de Dados</h3>

            <p>O Aplicativo solicitará aos Alunos o preenchimento de formulários contendo informações profissionais, acadêmicas e pessoais. Esses dados podem incluir, mas não estão limitados a:</p>

            <ul>
            <li>Informações pessoais, como nome completo, data de nascimento, gênero, endereço de e-mail e número de telefone.</li>
            <li>Informações profissionais, como ocupação, empregador e histórico de emprego.</li>
            <li>Informações acadêmicas, como instituição de ensino, curso, histórico escolar e certificados obtidos.</li>
            </ul>

            <h3>2. Uso dos Dados</h3>

            <p>Os dados coletados pelo Aplicativo serão utilizados exclusivamente para os seguintes propósitos:</p>

            <ul>
            <li>Gerenciamento do processo de matrículas, incluindo a avaliação e o processamento das informações fornecidas pelos Alunos.</li>
            <li>Comunicação com os Alunos sobre atualizações, notificações e informações relevantes relacionadas ao processo de matrículas.</li>
            <li>Geração de relatórios e análises estatísticas para aprimorar o processo de matrículas e o desempenho do Aplicativo.</li>
            </ul>

            <h3>3. Compartilhamento dos Dados</h3>

            <p>Os dados fornecidos pelos Alunos serão tratados como estritamente confidenciais e não serão compartilhados com terceiros, exceto nas seguintes circunstâncias:</p>

            <ul>
            <li>Compartilhamento com autoridades competentes, quando necessário para cumprir obrigações legais ou responder a solicitações legais.</li>
            <li>Compartilhamento com prestadores de serviços terceirizados que auxiliam na operação e manutenção do Aplicativo, desde que estejam vinculados a obrigações de confidencialidade.</li>
            </ul>

            <h3>4. Armazenamento e Segurança dos Dados</h3>

            <p>Os dados coletados serão armazenados em servidores seguros e protegidos contra acessos não autorizados, perdas ou alterações. Serão aplicadas medidas técnicas e organizacionais adequadas para garantir a segurança dos dados.</p>

            <h3>5. Retenção dos Dados</h3>

            <p>Os dados fornecidos pelos Alunos serão mantidos pelo Aplicativo pelo período necessário para cumprir os propósitos mencionados acima, a menos que seja exigido um período de retenção mais longo por obrigações legais ou regulatórias.</p>

            <p>Ao clicar em <strong>"Avançar"</strong> você está concordando com todos os termos apresentados.</p>
          
      </div>
    </div>
  </fieldset>
</template>

<style scoped>
#informacoes-termo {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: repeat(1, 1fr);
  gap: 0.5em 1em;
  text-align: justify;
}

p, ul, h2, h3{
   padding-bottom: 1%;
   font-family: Roboto, system-ui, sans-serif;
}

h2{
    text-align: center;
    padding-top: 1%;
}

#termo-consentimento{
  grid-row: 2 / 2;
  text-align: justify;
}

#frame{
  width: 35vw;
  height: 20vw;
}

.borda{
 background-color: rgb(248, 248, 248);
 border-color: antiquewhite;
 
}

</style>
