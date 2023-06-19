<script setup lang="ts">
import BarraProgresso from '@/components/CadastroEgressoView/BarraProgresso.vue'
import InformacoesAcademicas from '@/components/CadastroEgressoView/InformacoesAcademicas.vue'
import AppHeader from '@/components/AppHeader.vue'
import Modal from '@/components/Modal.vue'

import '@/assets/button.css'

import { onBeforeMount, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import { useCadastroEgressoStore } from '@/stores/CadastroEgressoStore'

import egressoService, { type GETEgressoParams, type PATCHEgressoRequest } from '@/service/EgressoService'
import pessoaService from '@/service/CadastroPessoaService'

import { TipoBuscaEgresso, type Egresso } from '@/types/Egresso'
import { type FormaContato, type Contato } from '@/types/Pessoa'

import { EgressoServiceException } from '@/exception/EgressoServiceException'

import getRndInteger from '@/util/TextUtil'

import '@/assets/button.css'
import '@/assets/forms.css'
import './container.css'

import { PessoaServiceException } from '@/exception/PessoaServiceException'

import type { ExperienciaAcademica, ExperienciaProfissional } from '@/types/Egresso'

const egressoStore = useCadastroEgressoStore()
const stepThreeData = egressoStore.stepThree
const stepFiveData = egressoStore.stepFive

const route = useRoute()
const router = useRouter()

const showModal = ref(false)

const modalMessage = ref('')
const modalTitle = ref('')

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
    let expAcademicas = (egresso as Egresso).infoAcademicas
    const expAcademicasList: ExperienciaAcademica[] = new Array<ExperienciaAcademica>()

    for(let i = 0; i < expAcademicas.length; i++) {
      expAcademicasList.push(
        { 
            id: expAcademicas[i].id,
            instituicao: expAcademicas[i].instituicao,
            tipo: expAcademicas[i].tipo,
            descricao: expAcademicas[i].descricao,
            dataInicio: expAcademicas[i].dataInicio,
            dataFim: expAcademicas[i].dataFim ?? '',
            index: getRndInteger(),
        }
      )
    }

    stepFiveData.expAcademicas = expAcademicasList.length > 0 ? expAcademicasList : []
  } catch (exception: any) {
    if(exception instanceof EgressoServiceException) {
      router.push(`/egresso/cadastro/erro`)
    }
  }
})

const adicionarExpAcademica = () => {
  if(stepFiveData.expAcademicas == undefined) { return }

  stepFiveData.expAcademicas.push({
    instituicao: '',
    descricao: '',
    tipo: '',
    dataInicio: '',
    dataFim: '',
    index: getRndInteger()
  })
}

const removerExpAcademica = (event: Event, id: Number | undefined, index: Number) => {
    if(stepFiveData.expAcademicas == undefined) { return }

    const newArray: ExperienciaAcademica[] = new Array<ExperienciaAcademica>()

    for(let i = 0; i < stepFiveData.expAcademicas.length; i++) {
      let newExpAcademica: ExperienciaAcademica = {
        instituicao: stepFiveData.expAcademicas[i].instituicao,
        descricao: stepFiveData.expAcademicas[i].descricao,
        tipo: stepFiveData.expAcademicas[i].tipo,
        dataInicio: stepFiveData.expAcademicas[i].dataInicio,
        dataFim: stepFiveData.expAcademicas[i].dataFim,
        index: getRndInteger()
      }

      if(i == index) {
        if(stepFiveData.expAcademicas[i].id != undefined) {
          newExpAcademica = {
            id: stepFiveData.expAcademicas[i].id
          }

          newArray.push(newExpAcademica)
        }
        continue;
      }

      if(stepFiveData.expAcademicas[i].id != undefined) {
        newExpAcademica = {
          id: stepFiveData.expAcademicas[i].id,
          ...newExpAcademica
        }
      }

      newArray.push(newExpAcademica)
    }

  stepFiveData.expAcademicas = newArray
}

const enviarDados = () => {
  if(stepFiveData.expAcademicas == undefined) { return }

  const listaExpAcademicas: ExperienciaAcademica[] = new Array<ExperienciaAcademica>()

  for(const expAcademica of stepFiveData.expAcademicas) {
    const newExpAcademica: ExperienciaProfissional = {
      ...expAcademica
    }

    delete newExpAcademica.index
    
    listaExpAcademicas.push(newExpAcademica)
  }

  const data: PATCHEgressoRequest = {
    email: stepThreeData.email,
    escolaridades: listaExpAcademicas
  }

  try {
    const result = egressoService.updateEgresso(data)

    if(typeof result === 'boolean' || result instanceof Boolean) {
      router.push(`/egresso/cadastro/erro`)
    }

    stepFiveData.completed = true
    router.push(`/egresso/cadastro/step/5/${token}`)
  } catch(exception: any) {
    if(exception instanceof PessoaServiceException) {
      router.push(`/egresso/cadastro/erro`)
    }
  }
}

const showExpAcademicaFields = (index: number) => {
  if(stepFiveData.expAcademicas == undefined) { return }

  const expAcademica = stepFiveData.expAcademicas[index]

  return expAcademica.instituicao != undefined && 
        expAcademica.tipo != undefined && 
        expAcademica.descricao != undefined && 
        expAcademica.dataInicio != undefined &&
        expAcademica.dataFim != undefined
}

</script>

<template>
  <div id="pageContainer">
    <AppHeader />
    <main id="cadastrar-container">
      <h1 class="container-title">Informações Acadêmicas</h1>
      <BarraProgresso :completed="[0, 1, 2, 3]" :active="4" />
      <form @submit.prevent="enviarDados">
        <fieldset class="form-group">
          <legend>Experiência Acadêmica</legend>
          <button class="button button-add" type="button" @click.prevent="adicionarExpAcademica">Adicionar</button>
          <span v-for="(value, index) in stepFiveData.expAcademicas" v-bind="value" :key="value.index as number">
            <InformacoesAcademicas :id="value.id" :index="index" @remove-academic-info="removerExpAcademica" v-if="showExpAcademicaFields(index)"/>
          </span>
        </fieldset>
        <div id="form-button-container">
          <button class="button button-secondary" type="button">Retroceder</button>
          <button class="button button-primary" type="submit">Avançar</button>
        </div>
      </form>
    </main>
  </div>
</template>

<style scoped>
#botaoAdd {
  background-color: rgb(106, 177, 206);
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
