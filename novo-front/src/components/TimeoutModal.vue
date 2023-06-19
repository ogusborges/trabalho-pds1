<script setup lang="ts">
import { type PropType, ref, defineProps } from 'vue'
import { type }


</script>

<template>
  <div class="modal-mask">
    <div class="modal-container">
      <div class="modal-header">
        <slot name="header">
          <h1>Erro</h1>
        </slot>
      </div>

      <div class="modal-body">
        <slot name="body"> default body </slot>
      </div>

      <div class="modal-footer">
        <slot name="footer">
          <button class="modal-default-button" @click="$emit('close')">OK</button>
        </slot>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr;
  justify-items: center;
  align-items: center;
  transition: opacity 0.3s ease;
}

.modal-container {
  width: 30em;
  height: 15em;
  padding: 20px 30px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background-color: #fff;
  border-radius: 0.3em;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-footer {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.modal-footer > * {
  margin: 0 0.7em;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter-from,
.modal-leave-to {
  opacity: 0;
}

.modal-enter-active .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
