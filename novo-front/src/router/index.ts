import { createRouter, createWebHistory } from 'vue-router'
import CadastroView from '../views/CadastroView.vue'

import EgressoRoutes from '@/router/Egresso'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/interno/cadastro',
      name: 'Cadastrar Entidade',
      component: CadastroView
    },
    ...EgressoRoutes
  ]
})

export default router