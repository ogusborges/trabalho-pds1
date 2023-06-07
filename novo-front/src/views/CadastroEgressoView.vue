<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'

import '@/assets/button.css'

import entityService from '../service/EntityService'
import { useEntityStore } from '@/stores/EntityRegister'
import Modal from '@/components/Modal.vue'

// import { isApiError, isFieldValidationError, type ValidationError } from '@/service/RequestResponse'

// import type { SendEntityInfo } from '@/service/EntityService'

import { ref } from 'vue'
import Esclarecimentos from '@/components/CadastroEgressoView/Esclarecimentos.vue'
import BarraProgresso from '@/components/CadastroEgressoView/BarraProgresso.vue'
// import InformacoesEgresso from '@/components/CadastroView/InformacoesEgresso.vue'
// import { EntityValidationException } from '@/exception/EntityValidationException'

const entityStore = useEntityStore()
const showModal = ref(false)

const modalMessage = ref('')
const modalTitle = ref('')

// let pessoalValidationErrors = ref<ValidationError[] | undefined>()
// let egressoValidationErrors = ref<ValidationError[] | undefined>()

const enviarDados = async (_: Event) => {
//   let sendEntityInfo: SendEntityInfo = {
//     pessoais: {
//       nome: entityStore.infoPessoais.nome ?? '',
//       sobrenome: entityStore.infoPessoais.sobrenome ?? '',
//       email: entityStore.infoPessoais.email ?? '',
//       tipo: entityStore.infoPessoais.tipo ?? ''
//     },
//     entidade: {}
//   }

//   if (entityStore.infoPessoais.tipo === 'EGRESSO') {
//     sendEntityInfo.entidade = {
//       matricula: entityStore.infoEgresso.matricula ?? ''
//     }
//   }

//   try {
//     const result = await entityService.sendEntity(sendEntityInfo)

//     if (typeof result === 'boolean' || result instanceof Boolean) {
//       if (result == true) {
//         modalTitle.value = 'Sucesso'
//         modalMessage.value = 'Deu bom na hora de cadastrar o egresso'
//         entityStore.resetEntityStore()
//       } else {
//         modalTitle.value = 'Erro'
//         modalMessage.value = 'Um erro inesperado aconteceu'
//       }

//       showModal.value = true
//     } else if (isApiError(result)) {
//       modalTitle.value = 'Erro'
//       modalMessage.value = result.message.toString()
//     } else if (isFieldValidationError(result)) {
//       pessoalValidationErrors.value = result
//         .filter((elem) => ['nome', 'sobrenome', 'tipo', 'email'].includes(elem.name.toString()))
//         .map((elem) => {
//           return { field: elem.name, result: !!elem.message }
//         })

//       egressoValidationErrors.value = result
//         .filter((elem) => ['matricula'].includes(elem.name.toString()))
//         .map((elem) => {
//           return { field: elem.name, result: !!elem.message }
//         })
//     }
//   } catch (exception: any) {
//     if (exception instanceof EntityValidationException) {
//       pessoalValidationErrors.value = exception
//         .getValidationErrors()
//         .filter((elem) => ['nome', 'sobrenome', 'tipo', 'email'].includes(elem.field.toString()))

//       egressoValidationErrors.value = exception
//         .getValidationErrors()
//         .filter((elem) => ['matricula'].includes(elem.field.toString()))
//     }
//   }
// }

// const resetFormFields = (event: Event) => {
//   const form: HTMLFormElement | null = (event.target as HTMLButtonElement).form

//   if (form != null) {
//     entityStore.resetEntityStore()

//     pessoalValidationErrors.value = pessoalValidationErrors.value?.map((error) => {
//       return { field: error.field, result: false }
//     })

//     egressoValidationErrors.value = egressoValidationErrors.value?.map((error) => {
//       return { field: error.field, result: false }
//     })

//     form.reset()
//   }
}

// const isEgresso = () => entityStore.infoPessoais.tipo == 'EGRESSO'
</script>

<template>
  <div id="pageContainer">
    <AppHeader />
    <main id="cadastrar-container">
      
      <h1 class="container-title">Esclarecimentos</h1>
      <transition name="modal">
        <Modal v-if="showModal" @close="showModal = false">
          <template v-slot:header>
            <h1>{{ modalTitle }}</h1>
          </template>
          <template v-slot:body>
            {{ modalMessage }}
          </template>
        </Modal>
      </transition>

      <BarraProgresso v-bind:errors="[]"/>
      <form @submit.prevent="enviarDados">
        
        <Esclarecimentos v-bind:errors="[]" />
        <!-- <InformacoesEgresso v-bind:errors="egressoValidationErrors ?? []" v-if="isEgresso()" /> -->
        <div id="form-button-container">
          <button class="button-primary" type="submit">Enviar</button>
          <button class="button-secondary" type="reset">
          <!-- <button class="button-secondary" type="reset" v-on:click.stop="resetFormFields"> -->
            Avançar
          </button>
        </div>
      </form>

    </main>
  </div>
</template>

<style scoped>
#cadastrar-container {
  background-color: #fff;
  width: 70%;
  margin-top: 1.2em;
  align-self: center;
  padding: 1em 2em;
  border-top: #4b8af9 solid 0.3em;
}

#form-button-container {
  display: flex;
  justify-content: center;
  width: 100%;
}

#form-button-container > button {
  padding: 1.2em 3em;
  margin: 0.5em 1em 0 0;
}

.field-group {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  margin: 0.8em 0px;
}

.container-title {
  font-size: 2em;
  color: #639af9;
  font-weight: 600;
  text-align: center;
}

#pageContainer {
  background-color: #ededed;
  display: flex;
  position: absolute;
  width: 100%;
  flex-direction: column;
}
</style>
