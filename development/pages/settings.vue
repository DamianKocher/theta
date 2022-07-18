<template>
  <div class="settings-container">
    <div class="settings-group">
      <div class="setting"><label-component text="reddit client" /></div>
      <div class="setting"><label-component text="reddit secret" /></div>
    </div>

    <div class="settings-group">
      <div class="setting"><label-component text="test 1" /></div>
      <div class="setting"><label-component text="test 2" /></div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import LabelComponent from '@/components/LabelComponent.vue'

export default defineComponent({
  name: 'SettingsPage',
  components: { LabelComponent },
})
</script>

<style>
.settings-container {
  margin-top: var(--padding-view);

  display: flex;
  align-items: center;
  flex-direction: column;
  gap: calc(var(--padding-spacer) * 3);
}

.settings-group {
  width: min(calc(100% - calc(var(--padding-view) * 2)), 800px);

  display: grid;
  grid-gap: var(--padding-spacer);
}

.setting {
  background-color: var(--ctp-surface0);
  padding: var(--padding-text);
}
</style>
