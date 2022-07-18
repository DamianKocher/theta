<template>
  <p class="button">{{ text }}</p>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ButtonComponent',
  props: {
    text: String,
  },
})
</script>

<style scoped>
.button {
  color: var(--ctp-text);
  outline: var(--ctp-text) solid 2px;
  background-color: var(--ctp-surface2);
  display: inline-block;

  padding: 2px 12px;
  border-radius: 100px;
}

.button:hover {
  --ctp-text: var(--ctp-sky);
  background-color: var(--ctp-overlay0);
}
</style>
