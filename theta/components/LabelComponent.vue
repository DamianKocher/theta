<template>
  <p class="label">{{ text }}</p>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "LabelComponent",
  props: {
    text: String,
  },
});
</script>

<style scoped>
.label {
  color: var(--ctp-text);
  font-size: var(--text-size-label);
}
</style>
