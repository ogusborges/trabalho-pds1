import { createRouter, createWebHistory } from 'vue-router'
import CadastroView from '../views/CadastroView.vue'
import CadastroEgressoViewStepOne from '../views/CadastroEgressoViewStep1.vue'
import CadastroEgressoViewStepTwo from '../views/CadastroEgressoViewStep2.vue'
import CadastroEgressoViewStepThree from '../views/CadastroEgressoViewStep3.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // {
    //   path: '/',
    //   name: 'home',
    //   component: HomeView
    // },
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // },
    {
      path: '/interno/cadastro',
      name: 'Cadastrar Entidade',
      component: CadastroView
    },
    {
      path: '/egresso/cadastro/step/1',
      name: 'Cadastrar Egresso Passo 1',
      component: CadastroEgressoViewStepOne
    },
    {
      path: '/egresso/cadastro/step/2',
      name: 'Cadastrar Egresso Passo 2',
      component: CadastroEgressoViewStepTwo
    },
    {
      path: '/egresso/cadastro/step/3',
      name: 'Cadastrar Egresso Passo 3',
      component: CadastroEgressoViewStepThree
    }


  ]
})

export default router
