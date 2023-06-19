import { defineStore } from 'pinia'
import { ref, type Ref } from 'vue'
import type { ExperienciaProfissional, ExperienciaAcademica } from '@/types/Egresso'
import { type Contato } from '@/types/Pessoa'


export interface StepOne {
  completed?: Ref<Boolean>
}
export interface StepTwo {
  aceitouTermos?: Ref<Boolean>
  completed?: Ref<Boolean>
}

export interface StepThree {
  email?: Ref<String>
  dataNascimento?: Ref<String>
  contatos?: Ref<Contato[]>
  completed?: Ref<Boolean>
}

export interface StepFour {
  expProfissionais?: Ref<ExperienciaProfissional[]>
  completed?: Ref<Boolean>
}

export interface StepFive {
  expAcademicas?: Ref<ExperienciaAcademica[]>
  completed?: Ref<Boolean>
}

export const useCadastroEgressoStore = defineStore('cadastroEgressoStore', () => {
  const stepOne = ref<StepOne>({})
  const stepTwo = ref<StepTwo>({})
  const stepThree = ref<StepThree>({})
  const stepFour = ref<StepFour>({})
  const stepFive = ref<StepFive>({})

  return {
    stepOne,
    stepTwo,
    stepThree,
    stepFour,
    stepFive,
  }
})
