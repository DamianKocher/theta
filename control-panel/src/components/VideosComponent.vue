<template>
  <div class="container">
    <buttom-component text="Create video" />
    <label-component text="no videos" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import ButtomComponent from "./ButtomComponent.vue";
import LabelComponent from "./LabelComponent.vue";

export default defineComponent({
  name: "VideosComponent",
  components: { ButtomComponent, LabelComponent },
});
</script>

<style scoped>
.container {
}
</style>
