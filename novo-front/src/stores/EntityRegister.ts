import { defineStore } from 'pinia'
import { ref, type Ref } from 'vue'

export interface IInformacoesPessoais {
  nome?: Ref<String>
  sobrenome?: Ref<String>
  email?: Ref<String>
  tipo?: Ref<String>
}

export interface IInformacoesEgresso {
  matricula?: Ref<String>
}

export const useEntityStore = defineStore('EntityRegister', () => {
  const infoPessoais = ref<IInformacoesPessoais>({})
  const infoEgresso = ref<IInformacoesEgresso>({})

  const resetEntityStore = () => {
    delete infoPessoais.value.nome
    delete infoPessoais.value.sobrenome
    delete infoPessoais.value.email
    delete infoPessoais.value.tipo

    delete infoEgresso.value.matricula
  }

  return {
    infoPessoais,
    infoEgresso,
    resetEntityStore
  }
})
