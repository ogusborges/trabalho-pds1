import { createRouter, createWebHistory } from 'vue-router'
import CadastroView from '../views/CadastroView.vue'
import CadastroEgressoView from '../views/CadastroEgressoView.vue'

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
      path: '/egresso/cadastro',
      name: 'Cadastrar Egresso',
      component: CadastroEgressoView
    }


  ]
})

export default router
