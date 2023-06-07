<script setup lang="ts">
import type { ValidationError } from '@/service/RequestResponse'
import { useEntityStore } from '@/stores/EntityRegister'
import { onMounted, type PropType, ref, watch } from 'vue'

const props = defineProps({
  errors: {
    type: Object as PropType<ValidationError[]>,
    required: true
  }
})

const showMatriculaError = ref<Boolean>(false)

watch(
  () => props.errors,
  (newValue: ValidationError[], _) => {
    showMatriculaError.value = newValue.length > 0 && newValue[0].result
  }
)

const infoEgresso = useEntityStore().infoEgresso
</script>

<template>
  <fieldset class="form-group">
    <legend>Egresso</legend>
    <div id="informacoes-egresso">
      <div class="form-field-group" id="matricula">
        <label class="form-field-label" for="matricula">Matrícula</label>
        <input type="text" name="matricula" v-model="infoEgresso.matricula" />
        <span class="form-field-error" v-if="showMatriculaError"
          >Deve ser uma matrícula válida</span
        >
      </div>
    </div>
  </fieldset>
</template>

<style scoped>
#informacoes-egresso {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 0.5em 1em;
}

#matricula {
  grid-column: 1 / 2;
}
</style>
