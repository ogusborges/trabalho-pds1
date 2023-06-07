<script setup lang="ts">
import { onMounted, ref, watch, type PropType, type Ref, normalizeClass } from 'vue'
import { useEntityStore } from '@/stores/EntityRegister'
import type { ValidationError } from '@/service/RequestResponse'

interface InfoPessoalErrorState {
  showNomeError: Boolean
  showSobrenomeError: Boolean
  showEmailError: Boolean
  showTipoError: Boolean
}

const props = defineProps({
  errors: {
    type: Object as PropType<ValidationError[]>,
    required: true
  }
})

const errorState: Ref<InfoPessoalErrorState> = ref<InfoPessoalErrorState>({
  showNomeError: false,
  showSobrenomeError: false,
  showEmailError: false,
  showTipoError: false
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

const infoPessoais = useEntityStore().infoPessoais

interface informacoesPessoaisEmit {
  (eventName: 'changeEntityType', event: Event, entityType: String): void
}

const emit = defineEmits<informacoesPessoaisEmit>()
</script>

<template>
  <fieldset class="form-group">
    <legend>Informações Pessoais</legend>
    <div id="informacoes-pessoais">
      <div class="form-field-group" id="nome">
        <label class="form-field-label" for="nome">Nome</label>
        <input type="text" name="nome" v-model="infoPessoais.nome" />
        <span class="form-field-error" v-if="errorState.showNomeError">Não pode estar vazio</span>
      </div>
      <div class="form-field-group" id="sobrenome">
        <label class="form-field-label" for="sobrenome">Sobrenome</label>
        <input type="text" name="sobrenome" v-model="infoPessoais.sobrenome" />
        <span class="form-field-error" v-if="errorState.showSobrenomeError"
          >Não pode estar vazio</span
        >
      </div>
      <div class="form-field-group" id="email">
        <label class="form-field-label" for="email">E-mail</label>
        <input type="text" name="email" v-model="infoPessoais.email" />
        <span class="form-field-error" v-if="errorState.showEmailError"
          >Deve ser um e-mail valido</span
        >
      </div>
      <div class="form-field-group" id="tipo">
        <label class="form-field-label" for="tipo">Tipo</label>
        <select
          name="tipo"
          v-on:change="($event) => emit('changeEntityType', $event, infoPessoais.tipo ?? '')"
          v-model="infoPessoais.tipo"
        >
          <option value="" selected>Selecione uma opção</option>
          <option value="EGRESSO">Egresso</option>
          <option value="INTERNO">Interno</option>
        </select>
        <span class="form-field-error" v-if="errorState.showTipoError"
          >Deve selecionar um tipo</span
        >
      </div>
    </div>
  </fieldset>
</template>

<style scoped>
#informacoes-pessoais {
  display: grid;
  grid-template-columns: 1fr 2fr 1fr 1fr;
  grid-template-rows: repeat(2, 1fr);
  gap: 0.5em 1em;
}

#nome {
  grid-column: 1 / 2;
}

#sobrenome {
  grid-column: 2 / 5;
}

#email {
  grid-row: 2 / 2;
  grid-column: 1 / 3;
}

#tipo {
  grid-row: 2 / 2;
  grid-column: 3 / 5;
}
</style>
