<script setup lang="ts">
import { onMounted, ref, watch, type PropType, type Ref, normalizeClass, onBeforeMount } from 'vue'
import { useCadastroEgressoStore } from '@/stores/CadastroEgressoStore'

const props = defineProps({
  id: {
    type: Object as PropType<Number | undefined>
  },
  index: {
    type: Number,
    required: true
  }
})

const infoExpAcademicas = useCadastroEgressoStore().stepFive.expAcademicas

if(infoExpAcademicas == undefined) {
  throw Error()
}

interface InformacoesAcademicasEventEmits {
  (eventName: String, event: Event, id: Number | undefined, index: Number): void
}

const emit = defineEmits<InformacoesAcademicasEventEmits>()

const shouldDisableDataFim = ref<Boolean>(false)

const changeDataFimDisabledState = (_event: Event) => {
  infoExpAcademicas[props.index].dataFim = ''
  shouldDisableDataFim.value = !shouldDisableDataFim.value
}

</script>

<template>
  <fieldset class="form-group">
    <div class="remove-button-container">
      <button class="button button-remove" type="button" @click.prevent="emit('removeAcademicInfo', $event, props.id, props.index)">Remover</button>
    </div>
    <div class="informacoes-academicas">
      <div class="form-field-group field-empresa">
        <label class="form-field-label" for="instituicao">Instituição</label>
        <input type="text" name="instituicao" v-model="infoExpAcademicas[props.index].instituicao"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
      <div class="form-field-group field-data-inicio">
        <label class="form-field-label" for="data-inicio">Data de Início</label>
        <input type="date" name="data-inicio" v-model="infoExpAcademicas[props.index].dataInicio"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div> 
      <div class="form-field-group field-data-fim">
        <label class="form-field-label" for="data-fim">Data de Saída</label>
        <input type="date" name="data-fim" v-model="infoExpAcademicas[props.index].dataFim" :disabled="shouldDisableDataFim ? true : false"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
      <div class="form-field-group field-descricao">
        <label class="form-field-label" for="descricao">Descrição</label>
        <input type="text" name="descricao" v-model="infoExpAcademicas[props.index].descricao"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
      <div class="form-field-group field-tipo">
        <label class="form-field-label" for="tipo">Tipo</label>
        <select name="tipo" v-model="infoExpAcademicas[props.index].tipo">
            <option value="" selected disabled>Selecione uma opção</option>
            <option value="ENSINO SUPERIOR">Ensino Superior</option>
            <option value="ENSINO BÁSICO">Ensino Básico</option>
            <option value="ENSINO TÉCNICO">Ensino Técnico</option>
            <option value="CURSOS">Cursos</option>
            <option value="OUTROS">Outros</option>
        </select>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
      <div class="form-field-group field-finalizado">
        <div class="form-field radio">
          <input type="checkbox" name="emprego-atual" v-on:change="changeDataFimDisabledState">
          <label class="form-field-label" for="emprego-atual">Não terminado</label>
        </div>
      </div>    
    </div>
  </fieldset>
</template>

<style scoped>

.radio > * {
  margin-right: 0.5em;
}

.informacoes-academicas {
  display: grid;
  grid-template-columns: 1fr repeat(3, 0.7fr);
  grid-template-rows: repeat(2, 1fr) 0.3fr;
  gap: 0.5em 1em;
}

.field-empresa {
  grid-column: 1 / 3;
  grid-row: 1 / 1;
}

.field-data-inicio {
  grid-column: 3 / 4;
  grid-row: 1 / 1;
}

.field-data-fim {
  grid-column: 4 / 4;
  grid-row: 1 / 1;
}

.field-descricao {
  grid-column: 1 / 2;
  grid-row: 2 / 2;
}

.field-tipo {
  grid-column: 2 / 3;
  grid-row: 2 / 2;
}

.field-tecnologias {
  grid-column: 3 / 5;
  grid-row: 2 / 2;
}

.field-finalizado {
  grid-column: 1 / 2;
  grid-row: 3 / 3;
}

.remove-button-container {
  display: flex;
  align-items: center;
  margin: 0.3em;
}
</style>
