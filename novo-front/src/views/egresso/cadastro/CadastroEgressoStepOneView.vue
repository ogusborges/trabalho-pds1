<script setup lang="ts">
import Esclarecimentos from '@/components/CadastroEgressoView/Esclarecimentos.vue'
import BarraProgresso from '@/components/CadastroEgressoView/BarraProgresso.vue'
import AppHeader from '@/components/AppHeader.vue'

import { onBeforeMount } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import { useCadastroEgressoStore } from '@/stores/CadastroEgressoStore'

import egressoService from '@/service/EgressoService'

import { EgressoServiceException } from '@/exception/EgressoServiceException'

import '@/assets/button.css'
import '@/assets/forms.css'
import './container.css'

const stepOneData = useCadastroEgressoStore().stepOne

const route = useRoute()
const router = useRouter()

let token: String = ''

onBeforeMount(async () => {
  token = route.params.token as String

  try {
    const tokenValidationResult = await egressoService.getToken(token)

    if((typeof tokenValidationResult === 'boolean' || tokenValidationResult instanceof Boolean) ||
      (egressoService.isValid(tokenValidationResult) == false)
    ) {
      router.push(`/egresso/cadastro/erro`)
    }
  } catch (exception: any) {
    if(exception instanceof EgressoServiceException) {
      router.push(`/egresso/cadastro/erro`)
    }
  }
})

const setStepOneCompleted = () => {
  stepOneData.completed = true
  router.push(`/egresso/cadastro/step/2/${token}`)
}

</script>

<template>
  <div id="pageContainer">
    <AppHeader />
    <main id="cadastrar-container">
      <h1 class="container-title">Esclarecimentos</h1>
      <BarraProgresso :completed="[]" :active="0" />
      <Esclarecimentos />
      <div id="form-button-container">
        <button class="button button-secondary" @click.prevent="setStepOneCompleted">Avançar</button>
      </div>
    </main>
  </div>
</template>

<style scoped>
#pageContainer {
  background-color: #ededed;
  display: flex;
  position: absolute;
  width: 100%;
  flex-direction: column;
}
</style>
