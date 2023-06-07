<script setup lang="ts">
// import type { ValidationError } from '@/service/RequestResponse'
// import { useEntityStore } from '@/stores/EntityRegister'
// import { onMounted, type PropType, ref, watch } from 'vue'

// const props = defineProps({
//   errors: {
//     type: Object as PropType<ValidationError[]>,
//     required: true
//   }
// })

// const showMatriculaError = ref<Boolean>(false)

// watch(
//   () => props.errors,
//   (newValue: ValidationError[], _) => {
//     showMatriculaError.value = newValue.length > 0 && newValue[0].result
//   }
// )

// const infoEgresso = useEntityStore().infoEgresso
</script>

<template>
  <fieldset class="form-group" id="field">
    <div id="informacoes-progresso">
      <div class="borda">

      </div>
      <div id="etapa1">
      </div>
      <div class="barra">
      </div>
      <div id="etapa2">
      </div>
      <div class="barra">
      </div>
      <div id="etapa3">
      </div>
      <div class="barra">
      </div>
      <div id="etapa4">
      </div>
      <div class="barra">
      </div>
      <div id="etapa5">
      </div> 
      <div class="borda">

      </div>     
    </div>
    <div id="texto-informacoes-progresso">
        <div id="texto1">
            <span>Esclarecimentos</span>
        </div>
        <div id="texto2">
            <span>Termo de consentimento</span>
        </div>
        <div id="texto3">
            <span>Informações Pessoais</span>
        </div>
        <div id="texto4">
            <span>Informações Profissionais</span>
        </div>
        <div id="texto5">
            <span>Informações Acadêmicas</span>
        </div>                                
    </div>
  </fieldset>
</template>

<style scoped>

#texto-informacoes-progresso{
  display: grid;
  grid-template-columns: repeat(9, 1fr);
  grid-template-rows: 1fr;
  gap: 0em 0em;
  margin: 0% 2%;
  justify-items: center;
  align-items: center;
  font-size: .9vw;
}
.borda{
    width: 1vw;
}

#texto1{
  grid-column-start: 1;
  grid-column-end: 2;
  grid-row: 1 / 1;
  text-align: center;
  text-size-adjust: 50%;
}

#texto2{
  grid-column-start: 3;
  grid-column-end: 4;
  grid-row: 1 / 1;
  text-align: center;
  text-size-adjust: 50%;
}

#texto3{
  grid-column-start: 5;
  grid-column-end: 6;
  grid-row: 1 / 1;
  text-align: center;
  text-size-adjust: 50%;
}

#texto4{
  grid-column-start: 7;
  grid-column-end: 8;
  grid-row: 1 / 1;
  text-align: center;
  text-size-adjust: 50%;
}

#texto5{
  grid-column-start: 9;
  grid-column-end: 10;
  grid-row: 1 / 1;
  text-align: center;
  text-size-adjust: 50%;
}

#informacoes-progresso {
  display: grid;
  grid-template-columns: repeat(6, 1fr min-content);
  grid-template-rows: 1fr;
  gap: 0em 0em;
  margin: 1%;
  justify-items: center;
  align-items: center;
  max-width: fit-content;
}

#field{
    border: none;
}

#etapa1 {

  grid-column: 2 / 2;
  grid-row: 1 / 1;
  background-color: rgb(141, 235, 177);
  width: 3vw;
  height: 3vw;
  border-radius: 50%;
  border-color: rgb(68, 154, 224);
  border-style: solid;
}

.barra{
  background-color: rgb(68, 154, 224);
  width: 11vw;
  height: 5%;
}

#etapa2 {
  grid-column: 4 / 4;
  background-color: rgb(141, 142, 235);
  width: 3vw;
  height: 3vw;
  border-radius: 50%;
  border-color: rgb(68, 154, 224);
  border-style: solid;
}
#etapa3 {
  grid-column: 6 / 6;
  background-color: rgb(240, 240, 240);
  width: 3vw;
  height: 3vw;
  border-radius: 50%;
  border-color: rgb(68, 154, 224);
  border-style: solid;
}
#etapa4 {
  grid-column: 8 / 8;
  background-color: rgb(240, 240, 240);
  width: 3vw;
  height: 3vw;
  border-radius: 50%;
  border-color: rgb(68, 154, 224);
  border-style: solid;
}
#etapa5 {
  grid-column: 10 / 10;
  background-color: rgb(240, 240, 240);
  width: 3vw;
  height: 3vw;
  border-radius: 50%;
  border-color: rgb(68, 154, 224);
  border-style: solid;
}
</style>
