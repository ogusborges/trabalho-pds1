import CadastroEgressoStepOneView from '@/views/egresso/cadastro/CadastroEgressoStepOneView.vue'
import CadastroEgressoStepTwoView from '@/views/egresso/cadastro/CadastroEgressoStepTwoView.vue'
import CadastroEgressoStepThreeView from '@/views/egresso/cadastro/CadastroEgressoStepThreeView.vue'
import CadastroEgressoTokenErrorView from '@/views/egresso/cadastro/CadastroEgressoTokenErrorView.vue'
import CadastroEgressoStepFourView from '@/views/egresso/cadastro/CadastroEgressoStepFourView.vue'
import CadastroEgressoStepFiveView from '@/views/egresso/cadastro/CadastroEgressoStepFiveView.vue'

export default [
    {
      path: '/egresso/cadastro/step/1/:token',
      name: 'Cadastrar Egresso Passo 1',
      component: CadastroEgressoStepOneView
    },
    {
      path: '/egresso/cadastro/step/2/:token',
      name: 'Cadastrar Egresso Passo 2',
      component: CadastroEgressoStepTwoView
    },
    {
      path: '/egresso/cadastro/step/3/:token',
      name: 'Cadastrar Egresso Passo 3',
      component: CadastroEgressoStepThreeView
    },
    {
      path: '/egresso/cadastro/step/4/:token',
      name: 'Cadastrar Egresso Passo 4',
      component: CadastroEgressoStepFourView
    },
    {
      path: '/egresso/cadastro/step/5/:token',
      name: 'Cadastrar Egresso Passo 5',
      component: CadastroEgressoStepFiveView
    }

]