<template>
  <div><LabelComponent text="No active operations" /></div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import LabelComponent from "./LabelComponent.vue";

export default defineComponent({
  name: "OperationsComponent",
  components: { LabelComponent },
});
</script>
