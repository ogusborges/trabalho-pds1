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

const infoExpProfissionais = useCadastroEgressoStore().stepFour.expProfissionais

if(infoExpProfissionais == undefined) {
  throw Error()
}

interface InformacoesContatoEmit {
  (eventName: String, event: Event, id: Number | undefined, index: Number): void
}

const emit = defineEmits<InformacoesContatoEmit>()

const shouldDisableDataFim = ref<Boolean>(false)
const shouldDisableSalario = ref<Boolean>(false)

watch( () => shouldDisableSalario, (_newValue, _oldValue) => {
  infoExpProfissionais[props.index].salario = ''
})

const changeDataFimDisabledState = (_event: Event) => {
  infoExpProfissionais[props.index].dataFim = ''
  shouldDisableDataFim.value = !shouldDisableDataFim.value
}

const changeSalarioDisabledState = (_event: Event) => {
  infoExpProfissionais[props.index].salario = ''
  shouldDisableSalario.value = !shouldDisableSalario.value
}

</script>

<template>
  <fieldset class="form-group">
    <div class="remove-button-container">
      <button class="button button-remove" type="button" @click.prevent="$emit('removeProfessionalInfo', $event, props.id, props.index)">Remover</button>
    </div>
    <div class="informacoes-profissionais">
      <div class="form-field-group field-empresa">
        <label class="form-field-label" for="empresa">Empresa</label>
        <input type="text" name="empresa" v-model="infoExpProfissionais[props.index].empresa"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
      <div class="form-field-group field-data-inicio">
        <label class="form-field-label" for="data-inicio">Data de Início</label>
        <input type="date" name="data-inicio" v-model="infoExpProfissionais[props.index].dataInicio"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div> 
      <div class="form-field-group field-data-fim">
        <label class="form-field-label" for="data-fim">Data de Saída</label>
        <input type="date" name="data-fim" v-model="infoExpProfissionais[props.index].dataFim" :disabled="shouldDisableDataFim ? true : false"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
      <div class="form-field-group field-cargo">
        <label class="form-field-label" for="cargo">Cargo</label>
        <input type="text" name="cargo" v-model="infoExpProfissionais[props.index].cargo"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
      <div class="form-field-group field-salario">
        <label class="form-field-label" for="salario">Salário</label>
        <input type="text" name="salario" v-model="infoExpProfissionais[props.index].salario" :disabled="shouldDisableSalario ? true : false"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
      <div class="form-field-group field-tecnologias">
        <label class="form-field-label" for="tecnologias">Tecnologias (separadas por vírgula)</label>
        <input type="text" name="tecnologias" v-model="infoExpProfissionais[props.index].tecnologias"/>
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
      <div class="form-field-group field-informar-salario">
        <div class="form-field radio">
          <input type="checkbox" name="informar-salario" v-on:change="changeSalarioDisabledState">
          <label class="form-field-label" for="informar-salario">Não desejo informar meu salário</label>
        </div>
      </div>
      <div class="form-field-group field-emprego-atual">
        <div class="form-field radio">
          <input type="checkbox" name="emprego-atual" v-on:change="changeDataFimDisabledState">
          <label class="form-field-label" for="emprego-atual">É meu emprego atual</label>
        </div>
      </div>    
    </div>
  </fieldset>
</template>

<style scoped>

.radio > * {
  margin-right: 0.5em;
}

.informacoes-profissionais {
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

.field-cargo {
  grid-column: 1 / 2;
  grid-row: 2 / 2;
}

.field-salario {
  grid-column: 2 / 3;
  grid-row: 2 / 2;
}

.field-tecnologias {
  grid-column: 3 / 5;
  grid-row: 2 / 2;
}

.field-emprego-atual {
  grid-column: 2 / 3;
  grid-row: 3 / 3;
}

.field-informar-salario {
  grid-column: 1 / 2;
  grid-row: 3 / 3;
}


#tipo {
  grid-row: 1 / 1;
  grid-column: 1 / 2;
}

.remove-button-container {
  display: flex;
  align-items: center;
  margin: 0.3em;
}
</style>
