<script setup lang="ts">
import TermoConsentimento from '@/components/CadastroEgressoView/TermoConsentimento.vue'
import BarraProgresso from '@/components/CadastroEgressoView/BarraProgresso.vue'
import AppHeader from '@/components/AppHeader.vue'
import Modal from '@/components/Modal.vue'

import { onBeforeMount, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import { useCadastroEgressoStore } from '@/stores/CadastroEgressoStore'

import egressoService, { type GETEgressoParams } from '@/service/EgressoService'

import { EgressoServiceException } from '@/exception/EgressoServiceException'

import { TipoBuscaEgresso, type Egresso } from '@/types/Egresso'

import '@/assets/button.css'
import './container.css'

const egressoStore = useCadastroEgressoStore()
const stepTwoData = egressoStore.stepTwo
const stepThreeData = egressoStore.stepThree

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

  try {
    const searchParams: GETEgressoParams = {
      token: token,
      tipoBusca: TipoBuscaEgresso.TOKEN,
    }

    const egresso = await egressoService.getEgresso(searchParams)

    if(typeof egresso === 'boolean' || egresso instanceof Boolean) {
      router.push(`/egresso/cadastro/erro`)
    }

    stepThreeData.email = (egresso as Egresso).email
  } catch (exception: any) {
    if(exception instanceof EgressoServiceException) {
      router.push(`/egresso/cadastro/erro`)
    }
  }
})

const sendAgreedTermsOfUse = async () => {
  stepTwoData.aceitouTermos = true
  try {
    const response = await egressoService.updateEgresso(
      { aceitouTermos: true, email: stepThreeData.email }
    )

    if((typeof response === 'boolean' || response instanceof Boolean) && response == false) {
      stepTwoData.aceitouTermos = false
      router.push(`/egresso/cadastro/erro`)
    } else {
      stepTwoData.completed = true
      router.push(`/egresso/cadastro/step/3/${token}`)
    }
  } catch (exception: any) {
    if(exception instanceof EgressoServiceException) {
      router.push(`/egresso/cadastro/erro`)
    }
  }
}
</script>

<template>
  <div id="pageContainer">
    <AppHeader />
    <main id="cadastrar-container">
      <h1 class="container-title">Termo de Consentimento</h1>
      <BarraProgresso :active="1" :completed="[0]" :errors="[]" />
      <TermoConsentimento />
      <div id="form-button-container">
        <button class="button button-primary" @click.prevent="sendAgreedTermsOfUse">Avançar</button>
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
