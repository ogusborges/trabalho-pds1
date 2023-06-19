import { defineStore } from 'pinia'
import { ref, type Ref } from 'vue'

export interface Contato {
  index?: Ref<String>
  id?: Ref<Number>
  tipo?: Ref<String>
  valor?: Ref<String>
}

export interface StepOne {
  completed?: Ref<Boolean>
}

export interface StepTwo {
  aceitouTermos?: Ref<Boolean>
  completed?: Ref<Boolean>
}

export interface StepThree {
  email?: Ref<String>
  dataNascimento?: Ref<Date>
  contatos?: Ref<Contato[]>
  completed?: Ref<Boolean>
}

export const useEgressoRegisterStore = defineStore('egressoRegister', () => {
  const stepOne = ref<StepOne>({})
  const stepTwo = ref<StepTwo>({})
  const stepThree = ref<StepThree>({})

  return {
    stepOne,
    stepTwo,
    stepThree
  }
})
