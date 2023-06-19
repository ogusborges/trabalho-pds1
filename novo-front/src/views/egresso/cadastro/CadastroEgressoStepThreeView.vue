<script setup lang="ts">
import BarraProgresso from '@/components/CadastroEgressoView/BarraProgresso.vue'
import InformacoesPessoaisAcesso from '@/components/CadastroEgressoView/InformacoesPessoaisAcesso.vue'
import InformacoesContato from '@/components/CadastroEgressoView/InformacoesContato.vue'
import AppHeader from '@/components/AppHeader.vue'

import '@/assets/button.css'

import { onBeforeMount, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import { useCadastroEgressoStore} from '@/stores/CadastroEgressoStore'

import egressoService,  { type GETEgressoParams } from '@/service/EgressoService'
import pessoaService, { type PATCHPessoaRequest } from '@/service/CadastroPessoaService'

import { TipoBuscaEgresso, type Egresso } from '@/types/Egresso'
import { type FormaContato, type Contato } from '@/types/Pessoa'

import { EgressoServiceException } from '@/exception/EgressoServiceException'

import getRndInteger from '@/util/TextUtil'

import '@/assets/button.css'
import './container.css'
import { PessoaServiceException } from '@/exception/PessoaServiceException'

const stepThreeData = useCadastroEgressoStore().stepThree

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

    let contatos = (egresso as Egresso).contatos
    const contactList: Contato[] = new Array<Contato>();

    for(let i = 0; i < contatos.length; i++) {
      contactList.push({ 
        id: contatos[i].id, 
        tipo: contatos[i].tipo, 
        valor: contatos[i].valor, 
        index: getRndInteger() 
      })
    }

    stepThreeData.contatos = contactList.length > 0 ? contactList : []
    stepThreeData.dataNascimento = (egresso as Egresso).dataNascimento
    stepThreeData.email = (egresso as Egresso).email
  } catch (exception: any) {
    if(exception instanceof EgressoServiceException) {
      router.push(`/egresso/cadastro/erro`)
    }
  }
})

const adicionarContato = () => {
  if(stepThreeData.contatos == undefined) { return }
  
  stepThreeData.contatos.push({
    tipo: '',
    valor: '',
    index: getRndInteger()
  })
}

const removerContato = (event: Event, id: Number | undefined, index: Number) => {
  if(stepThreeData.contatos == undefined) { return }

  const newArray: Contato[] = new Array<Contato>()
  
  for(let i = 0; i < stepThreeData.contatos.length; i++) {
    let newContato: Contato = {
      'tipo': stepThreeData.contatos[i].tipo,
      'valor': stepThreeData.contatos[i].valor,
      'index': getRndInteger()
    }

    if(i == index) {
      if(stepThreeData.contatos[i].id != undefined) {
        newContato = {
          'id': stepThreeData.contatos[i].id,
        }
        
        newArray.push(newContato)
      }
      continue;
    }

    if(stepThreeData.contatos[i].id != undefined) {
      newContato = {
        'id': stepThreeData.contatos[i].id,
        ...newContato
      }
    }

    newArray.push(newContato)
  }

  stepThreeData.contatos = newArray
}

const enviarDados = () => {
  if(stepThreeData.contatos == undefined) { return }

  const listaContatos: FormaContato[] = new Array<FormaContato>();

  for(const contato of stepThreeData.contatos) {
    const newContato: Contato = {
      ...contato
    }

    delete newContato.index
    
    listaContatos.push(newContato)
  }

  const data: PATCHPessoaRequest = {
    email: stepThreeData.email,
    contatos: listaContatos,
    dataNascimento: stepThreeData.dataNascimento,
  }

  try {
    const result = pessoaService.updatePessoa(data)

    if(typeof result === 'boolean' || result instanceof Boolean) {
      router.push(`/egresso/cadastro/erro`)
    }

    stepThreeData.completed = true
    router.push(`/egresso/cadastro/step/4/${token}`)
  } catch(exception: any) {
    if(exception instanceof PessoaServiceException) {
      router.push(`/egresso/cadastro/erro`)
    }
  }
}

const showContactFields = (index: number) => {
  if(stepThreeData.contatos == undefined) { return }

  const contato = stepThreeData.contatos[index]

  return contato.tipo != undefined &&
        contato.valor != undefined
}

</script>

<template>
  <div id="pageContainer">
    <AppHeader />
    <main id="cadastrar-container">
      <h1 class="container-title">Informações Pessoais</h1>
      <BarraProgresso :completed="[0, 1]" :active="2" v-bind:errors="[]" />
      <form @submit.prevent="enviarDados">
        <InformacoesPessoaisAcesso v-bind:errors="[]" />
        <fieldset class="form-group">
          <legend>Contato</legend>
          <button class="button button-add" type="button" @click.prevent="adicionarContato">Adicionar</button>
          <span v-for="(value, index) in stepThreeData.contatos" v-bind="value" :key="value.index as number" >
            <InformacoesContato :id="value.id" :index="index" @remove-contact="removerContato" v-if="showContactFields(index)"/>
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
#pageContainer {
  background-color: #ededed;
  display: flex;
  position: absolute;
  width: 100%;
  flex-direction: column;
}
</style>