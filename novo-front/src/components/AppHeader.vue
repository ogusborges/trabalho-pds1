<script setup lang="ts">
import Logotipo from '@/assets/logobranco.png'
</script>

<template>
  <header>
    <div id="header-container">
      <img id="logotipo-ufu" :src="Logotipo" />
      <div id="primary-header">
        <ul class="primary-header-list">
          <li class="primary-header-list-item">Egresso</li>
          <li class="primary-header-list-item">Formulário</li>
          <li class="primary-header-list-item">Relatórios</li>
        </ul>
      </div>
      <div id="profile-container">
        <span>Gustavo Borges</span>
        <img id="expand-icon" />
      </div>
    </div>
    <div id="secondary-header">
      <ul class="secondary-header-list">
        <li class="secondary-header-list-item">Cadastrar</li>
        <li class="secondary-header-list-item">Listar</li>
      </ul>
    </div>
  </header>
</template>

<style scoped>
#header-container {
  background-color: #2f6abd;
  height: 9vh;
  padding: 0 1em;
  display: flex;
}

#profile-container {
  display: flex;
  background-color: #0e4097;
  height: 50%;
  align-self: center;
  color: #f4f4f4;
}

#profile-container > p {
  display: inline;
}

#primary-header {
  padding: 0;
  display: flex;
  flex-grow: 3;
}

#primary-header > ul {
  list-style: none;
  display: flex;
  flex-grow: 3;
}

.primary-header-list-item,
.secondary-header-list-item {
  color: #f4f4f4;
  font-weight: 500;
}

.primary-header-list-item {
  display: flex;
  justify-content: center;
  flex-direction: column;
  margin: 0 0;
  padding: 1em 2em;
  transition: 0.1s;
  cursor: default;
}

.primary-header-list-item:hover {
  background-color: #0e4097;
  transition: 0.15s;
}

#secondary-header {
  display: flex;
  background-color: #0e4097;
  width: 100%;
  height: 4vh;
}

.secondary-header-list {
  list-style: none;
  display: flex;
  padding: 0 2em;
}

.secondary-header-list-item {
  display: flex;
  align-items: center;
  padding: 0 1em;
  transition: 0.1s;
}

.secondary-header-list-item:hover {
  transition: 0.5s;
  font-weight: 600;
  background-color: #104db6;
}

#logotipo-ufu {
  height: 100%;
}
</style>
