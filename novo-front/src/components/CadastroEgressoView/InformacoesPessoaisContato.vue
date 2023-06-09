<script setup lang="ts">
import { onMounted, ref, watch, type PropType, type Ref, normalizeClass } from 'vue'
import { useEntityEgressoStore } from '@/stores/EntityEgressoRegister'
import type { ValidationError } from '@/service/RequestResponse'

interface InfoPessoalContatoErrorState {
  showTipoError: Boolean
  showValorError: Boolean
}

const props = defineProps({
  errors: {
    type: Object as PropType<ValidationError[]>,
    required: true
  }
})

const errorState: Ref<InfoPessoalContatoErrorState> = ref<InfoPessoalContatoErrorState>({
  showTipoError: false,
  showValorError: false
})

watch(
  () => props.errors,
  (newValue: ValidationError[], _: ValidationError[]) => {
    const newErrorObj: InfoPessoalContatoErrorState = errorState.value

    for (let error of newValue) {
      let key = `show${
        error.field.charAt(0).toUpperCase() + error.field.slice(1, error.field.length)
      }Error`

      newErrorObj[key as keyof InfoPessoalContatoErrorState] = error.result
    }

    errorState.value = newErrorObj
  }
)

const infoPessoais = useEntityEgressoStore().infoPessoais

interface informacoesPessoaisEmit {
  (eventName: 'changeEntityType', event: Event, entityType: String): void
}

const emit = defineEmits<informacoesPessoaisEmit>()
</script>

<template>
  <fieldset class="form-group">
    <legend>Contato</legend>

    <div id="form-button-container">
        <button class="button-secondary" id="botaoRem" type="button">Remover</button>
        <button class="button-primary" id="botaoAdd" type="button">Adicionar</button>
    </div>

    <div id="informacoes-pessoais">
      <div class="form-field-group" id="tipo">
        <label class="form-field-label" for="tipo">Tipo</label>
        <select
          name="tipo"
          v-on:change="($event) => emit('changeEntityType', $event, infoPessoais.tipo ?? '')"
          v-model="infoPessoais.tipo"
        >
          <option value="" selected>Selecione uma opção</option>
          <option value="TEL">Telefone Fixo</option>
          <option value="CEL">Celular</option>
          <option value="EMAIL">E-mail</option>
        </select>
        <span class="form-field-error" v-if="errorState.showTipoError"
          >Deve selecionar um tipo</span
        >
      </div>
      <div class="form-field-group" id="valor">
        <label class="form-field-label" for="valor">Valor</label>
        <input type="text" name="valor" v-model="infoPessoais.valor" />
        <span class="form-field-error" v-if="errorState.showValorError"
          >Deve ser um valor válido</span
        >
      </div>
    </div>
  </fieldset>
</template>

<style scoped>
#informacoes-pessoais {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr;
  gap: 0.5em 1em;
}

#tipo {
  grid-row: 1 / 1;
  grid-column: 1 / 2;
}

#botaoAdd{
    background-color: rgb(106, 177, 206);
}

#botaoRem{
    background-color: rgb(185, 201, 216);
}

#valor {
  grid-row: 1 / 1;
  grid-column:  2/ 5;
}
</style>
