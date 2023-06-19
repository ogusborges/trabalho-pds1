<script setup lang="ts">
import InformacoesProfissionais from '@/components/CadastroEgressoView/InformacoesProfissionais.vue'
import BarraProgresso from '@/components/CadastroEgressoView/BarraProgresso.vue'
import AppHeader from '@/components/AppHeader.vue'

import { onBeforeMount } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import { useCadastroEgressoStore } from '@/stores/CadastroEgressoStore'

import egressoService, { type GETEgressoParams, type PATCHEgressoRequest } from '@/service/EgressoService'
import { EgressoServiceException } from '@/exception/EgressoServiceException'
import { PessoaServiceException } from '@/exception/PessoaServiceException'

import { TipoBuscaEgresso, type Egresso, type ExperienciaProfissional } from '@/types/Egresso'

import getRndInteger from '@/util/TextUtil'

import '@/assets/button.css'
import '@/assets/forms.css'
import './container.css'

const egressoStore = useCadastroEgressoStore()
const stepThreeData = egressoStore.stepThree
const stepFourData = egressoStore.stepFour

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
    let expProfissionais = (egresso as Egresso).expProfissionais
    const expProfissionaisList: ExperienciaProfissional[] = new Array<ExperienciaProfissional>()

    for(let i = 0; i < expProfissionais.length; i++) {
      expProfissionaisList.push(
        { 
          id: expProfissionais[i].id, 
          cargo: expProfissionais[i].cargo, 
          salario: expProfissionais[i].salario ?? '',
          empresa: expProfissionais[i].empresa,
          dataInicio: expProfissionais[i].dataInicio,
          dataFim: expProfissionais[i].dataFim ?? '',
          tecnologias: expProfissionais[i].tecnologias ?? '',
          index: getRndInteger(),
        }
      )
    }

    stepFourData.expProfissionais = expProfissionaisList
  } catch (exception: any) {
    if(exception instanceof EgressoServiceException) {
      router.push(`/egresso/cadastro/erro`)
    }
  }
})

const adicionarExpProfissional = () => {
  if(stepFourData.expProfissionais != undefined) {
    stepFourData.expProfissionais.push({
      cargo: '',
      salario: '',
      empresa: '',
      dataInicio: '',
      dataFim: '',
      tecnologias: '',
      index: getRndInteger(),
    })
  }
}

const removerExpProfissional = (event: Event, id: Number | undefined, index: Number) => {
  if(stepFourData.expProfissionais == undefined) { return }

  const newArray: ExperienciaProfissional[] = new Array<ExperienciaProfissional>()
  
  for(let i = 0; i < stepFourData.expProfissionais.length; i++) {
    let newExpProfissional: ExperienciaProfissional = {
      'cargo': stepFourData.expProfissionais[i].cargo,
      'empresa': stepFourData.expProfissionais[i].empresa,
      'salario': stepFourData.expProfissionais[i].salario,
      'dataInicio': stepFourData.expProfissionais[i].dataInicio,
      'dataFim': stepFourData.expProfissionais[i].dataFim,
      'tecnologias': stepFourData.expProfissionais[i].tecnologias,
      'index': getRndInteger(),
    }
    
    if(i == index) {
      if(stepFourData.expProfissionais[i].id != undefined) {
        newExpProfissional = {
          'id': stepFourData.expProfissionais[i].id,
        }

        newArray.push(newExpProfissional)
      }
      continue;
    }

    if(stepFourData.expProfissionais[i].id != undefined) {
      newExpProfissional = {
        'id': stepFourData.expProfissionais[i].id,
        ...newExpProfissional
      }
    }

    newArray.push(newExpProfissional)
  }

  stepFourData.expProfissionais = newArray.length > 0 ? newArray : []
}

const enviarDados = () => {
  if(stepFourData.expProfissionais == undefined) { return }

  const listaExpProfissionais: ExperienciaProfissional[] = new Array<ExperienciaProfissional>()

  for(const expProfissional of stepFourData.expProfissionais) {
    const newExpProfissional: ExperienciaProfissional = {
      ...expProfissional
    }

    delete newExpProfissional.index
    
    listaExpProfissionais.push(newExpProfissional)
  }

  const data: PATCHEgressoRequest = {
    email: stepThreeData.email,
    expProfissionais: listaExpProfissionais
  }

  try {
    const result = egressoService.updateEgresso(data)

    if(typeof result === 'boolean' || result instanceof Boolean) {
      router.push(`/egresso/cadastro/erro`)
    }

    stepFourData.completed = true
    router.push(`/egresso/cadastro/step/5/${token}`)
  } catch(exception: any) {
    if(exception instanceof PessoaServiceException) {
      router.push(`/egresso/cadastro/erro`)
    }
  }
}

const showExperienciaProfissionalFields = (index: number) => {
  if(stepFourData.expProfissionais == undefined) { return }

  const expProfissional = stepFourData.expProfissionais[index]

  return expProfissional.cargo != undefined && 
        expProfissional.empresa != undefined && 
        expProfissional.salario != undefined && 
        expProfissional.dataInicio != undefined &&
        expProfissional.dataFim != undefined
}

</script>

<template>
  <div id="pageContainer">
    <AppHeader />
    <main id="cadastrar-container">
      <h1 class="container-title">Informações Profissionais</h1>
      <BarraProgresso :completed="[0, 1, 2]" :active="3" v-bind:errors="[]" />
      <form @submit.prevent="enviarDados">
        <fieldset class="form-group">
          <legend>Experiência Profissional</legend>
          <button class="button button-add" type="button" @click.prevent="adicionarExpProfissional">Adicionar</button>
          <span v-for="(value, index) in stepFourData.expProfissionais" v-bind="value" :key="value.index as number" >
            <InformacoesProfissionais :id="value.id" :index="index" @remove-professional-info="removerExpProfissional" v-if="showExperienciaProfissionalFields(index)"/>
          </span>
        </fieldset>
        <div id="form-button-container">
          <button class="button button-primary" type="button">Retroceder</button>
          <button class="button button-secondary" type="submit">Avançar</button>
        </div>
      </form>
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
