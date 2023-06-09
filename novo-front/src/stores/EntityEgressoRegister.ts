import { defineStore } from 'pinia'
import { ref, type Ref } from 'vue'

export interface IInformacoesPessoais {
  nascimento?: Ref<String>
  email?: Ref<String>
  tipo?: Ref<String>
  valor?: Ref<String>
}

export interface IInformacoesProfissionais {

}

export interface IInformacoesAcademicas {

}

export const useEntityEgressoStore = defineStore('EntityEgressoRegister', () => {
  const infoPessoais = ref<IInformacoesPessoais>({})
  const infoProfissionais = ref<IInformacoesProfissionais>({})
  const infoAcademicas = ref<IInformacoesAcademicas>({})

  const resetEntityEgressoStore = () => {
    delete infoPessoais.value.nascimento
    delete infoPessoais.value.email
    delete infoPessoais.value.tipo
    delete infoPessoais.value.valor

  }

  return {
    infoPessoais,
    infoProfissionais,
    infoAcademicas,
    resetEntityEgressoStore
  }
})
