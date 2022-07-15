<template>
  <div class="home-container">
    <div class="home">
      <div class="section">
        <p class="header">Videos</p>
        <videos-component />
      </div>

      <div class="section">
        <p class="header">Operations</p>
        <operations-component />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import VideosComponent from "@/components/VideosComponent.vue";
import OperationsComponent from "@/components/OperationsComponent.vue";

export default defineComponent({
  name: "HomeView",
  components: {
    VideosComponent,
    OperationsComponent,
  },
});
</script>

<style scoped>
.home-container {
  display: flex;
  justify-content: center;
}

.home {
  width: var(--view-width);
  margin-top: var(--padding-view);

  display: grid;
  grid-gap: calc(var(--padding-spacer) * 2);
}

@media (min-width: 960px) {
  .home {
    grid-template-columns: 5fr 3fr;
  }
}

.section {
  height: fit-content;
  display: flex;
  flex-direction: column;
  gap: var(--padding-spacer);

  background-color: var(--ctp-surface0);
  border-radius: var(--border-radius);
  padding: var(--padding-spacer);
}

.header {
  font-size: var(--text-size-label);
  color: var(--ctp-lavender);
  font-size: var(--text-size-header);
  text-align: center;
  text-transform: var(--text-transform);
}
</style>
