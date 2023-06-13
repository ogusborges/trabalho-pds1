<script setup lang="ts">
import { onMounted, ref, watch, type PropType, type Ref, normalizeClass } from 'vue'
import { useEntityEgressoStore } from '@/stores/EntityEgressoRegister'
import type { ValidationError } from '@/service/RequestResponse'

interface InfoPessoalErrorState {
  showNascimentoError: Boolean
  showEmailError: Boolean
}

const props = defineProps({
  errors: {
    type: Object as PropType<ValidationError[]>,
    required: true
  }
})

const errorState: Ref<InfoPessoalErrorState> = ref<InfoPessoalErrorState>({
  showNascimentoError: false,
  showEmailError: false
})

watch(
  () => props.errors,
  (newValue: ValidationError[], _: ValidationError[]) => {
    const newErrorObj: InfoPessoalErrorState = errorState.value

    for (let error of newValue) {
      let key = `show${
        error.field.charAt(0).toUpperCase() + error.field.slice(1, error.field.length)
      }Error`

      newErrorObj[key as keyof InfoPessoalErrorState] = error.result
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
    <legend>Acesso</legend>
    <div id="informacoes-pessoais">
      <div class="form-field-group" id="email">
        <label class="form-field-label" for="email">E-mail</label>
        <input type="text" name="email" v-model="infoPessoais.email" />
        <span class="form-field-error" v-if="errorState.showEmailError"
          >Deve ser um e-mail valido</span
        >
      </div>
      <div class="form-field-group" id="nascimento">
        <label class="form-field-label" for="nascimento">Data de Nascimento</label>
        <input type="text" name="nascimento" v-model="infoPessoais.nascimento" />
        <span class="form-field-error" v-if="errorState.showNascimentoError"
          >Deve ser uma data de nascimento válida</span
        >
      </div>
    </div>
  </fieldset>
</template>

<style scoped>
#informacoes-pessoais {
  display: grid;
  grid-template-columns: 2fr 2fr;
  grid-template-rows: 1fr;
  gap: 0.5em 1em;
}

#email {
  grid-row: 1 / 1;
  grid-column: 1 / 2;
}

#nascimento {
  grid-row: 1 / 1;
  grid-column:  2/ 3;
}
</style>
