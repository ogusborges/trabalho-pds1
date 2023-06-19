<script setup lang="ts">
import { type PropType } from 'vue'

const props = defineProps({
  completed: {
    type: Object as PropType<number[]>,
    required: true
  },
  active: {
    type: Number,
    required: true
  }
})

const STEP_NUMBER = 5
const stepElements = Array<String[]>(STEP_NUMBER)

for (let i = 0; i < STEP_NUMBER; i++) {
  if (props.completed.includes(i)) {
    stepElements[i] = ['etapa', 'etapa-completa']
  } else if (i == props.active) {
    stepElements[i] = ['etapa', 'etapa-ativa']
  } else {
    stepElements[i] = ['etapa']
  }
}
</script>

<template>
  <div class="field">
    <div id="informacoes-progresso">
      <div class="borda"></div>
      <div id="etapa1" :class="stepElements[0]"></div>
      <div class="barra"></div>
      <div id="etapa2" :class="stepElements[1]"></div>
      <div class="barra"></div>
      <div id="etapa3" :class="stepElements[2]"></div>
      <div class="barra"></div>
      <div id="etapa4" :class="stepElements[3]"></div>
      <div class="barra"></div>
      <div id="etapa5" :class="stepElements[4]"></div>
      <div class="borda"></div>
    </div>
    <div id="texto-informacoes-progresso">
      <div id="texto1" class="texto">
        <span>Esclarecimentos</span>
      </div>
      <div id="texto2" class="texto">
        <span>Termo de consentimento</span>
      </div>
      <div id="texto3" class="texto">
        <span>Informações Pessoais</span>
      </div>
      <div id="texto4" class="texto">
        <span>Informações Profissionais</span>
      </div>
      <div id="texto5" class="texto">
        <span>Informações Acadêmicas</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
#texto-informacoes-progresso {
  display: grid;
  grid-template-columns: repeat(9, 1fr);
  grid-template-rows: 1fr;
  gap: 0em 0em;
  margin: 0% 2%;
  justify-items: center;
  align-items: center;
  font-size: 0.9vw;
}
.borda {
  width: 1vw;
}

.texto {
  grid-row: 1 / 1;
  text-align: center;
  text-size-adjust: 50%;
}

#texto1 {
  grid-column-start: 1;
  grid-column-end: 2;
}

#texto2 {
  grid-column-start: 3;
  grid-column-end: 4;
}

#texto3 {
  grid-column-start: 5;
  grid-column-end: 6;
}

#texto4 {
  grid-column-start: 7;
  grid-column-end: 8;
}

#texto5 {
  grid-column-start: 9;
  grid-column-end: 10;
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

.field {
  border: none;
}

#etapa1 {
  grid-column: 2 / 2;
  grid-row: 1 / 1;
  width: 3vw;
  height: 3vw;
}

.etapa-completa {
  background-color: rgb(141, 235, 177) !important;
  border-radius: 50%;
  border-color: rgb(68, 154, 224);
  border-style: solid;
}

.etapa-ativa {
  background-color: rgb(141, 142, 235) !important;
}

.barra {
  background-color: rgb(68, 154, 224);
  width: 11vw;
  height: 5%;
}

.etapa {
  width: 3vw;
  height: 3vw;
  border-radius: 50%;
  border-color: rgb(68, 154, 224);
  border-style: solid;
  background-color: rgb(240, 240, 240);
}

#etapa2 {
  grid-column: 4 / 4;
}

#etapa3 {
  grid-column: 6 / 6;
  background-color: rgb(240, 240, 240);
}

#etapa4 {
  grid-column: 8 / 8;
  background-color: rgb(240, 240, 240);
}

#etapa5 {
  grid-column: 10 / 10;
  background-color: rgb(240, 240, 240);
}
</style>
