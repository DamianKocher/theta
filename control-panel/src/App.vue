<template>
  <nav-bar />
  <router-view v-slot="{ Component }">
    <transition mode="out-in">
      <component :is="Component" />
    </transition>
  </router-view>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import NavBar from "./components/NavBar.vue";

export default defineComponent({
  name: "App",
  components: { NavBar },
});
</script>

<style>
@import "./assets/style/style.css";

html {
  background-color: var(--ctp-base);
}
</style>
