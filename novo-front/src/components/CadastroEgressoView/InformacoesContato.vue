<script setup lang="ts">
import { type PropType } from 'vue'
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

const infoContato = useCadastroEgressoStore().stepThree.contatos

if(infoContato == undefined) {
  throw Error()
}

interface InformacoesContatoEmit {
  (eventName: String, event: Event, id: Number | undefined, index: Number): void
}

const emit = defineEmits<InformacoesContatoEmit>()
</script>

<template>
  <fieldset class="form-group">
    <div class="remove-button-container">
        <button class="button button-remove" type="button" @click.prevent="$emit('removeContact', $event, props.id, props.index)">Remover</button>
    </div>
    <div id="informacoes-pessoais">
      <div class="form-field-group" id="tipo">
        <label class="form-field-label" for="tipo">Tipo</label>
        <select name="tipo" v-model="infoContato[props.index].tipo">
          <option value="" selected disabled>Selecione uma opção</option>
          <option value="TELEFONE">Telefone</option>
          <option value="CELULAR">Celular</option>
          <option value="EMAIL">E-mail</option>
          <option value="LINKEDIN">Linkedin</option>
          <option value="GITHUB">Github</option>
        </select>
        <span class="form-field-error" v-if="false">Deve selecionar um tipo</span>
      </div>
      <div class="form-field-group" id="valor">
        <label class="form-field-label" for="valor">Valor</label>
        <input type="text" name="valor" v-model="infoContato[props.index].valor" />
        <span class="form-field-error" v-if="false">Deve ser um valor válido</span>
      </div>
    </div>
  </fieldset>
</template>

<style scoped>

#informacoes-pessoais {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: 1fr;
  gap: 0.5em 1em;
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



#valor {
  grid-row: 1 / 1;
  grid-column: 2/ 5;
}
</style>
