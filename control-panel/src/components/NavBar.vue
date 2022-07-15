<template>
  <div class="container">
    <div class="title-container">
      <p>Theta</p>
    </div>

    <div class="items">
      <p
        v-for="(route, index) in $router.getRoutes()"
        :key="index"
        :class="getClass(route.name)"
        @click="navTo(route.name)"
      >
        {{ route.name }}
      </p>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { RouteRecordName } from "vue-router";

export default defineComponent({
  name: "NavBar",
  methods: {
    navTo: function (page: RouteRecordName | undefined) {
      if (!page) return;

      this.$router.push({ name: page.toString() });
    },
    getClass: function (page: RouteRecordName | undefined) {
      if (!page) return "non-active-tab";

      return this.$route.name === page ? "active-tab" : "non-active-tab";
    },
  },
});
</script>

<style scoped>
.container {
  width: 100%;
  padding: 0px calc(var(--padding-spacer) * 3);

  background-color: var(--ctp-mantle);

  display: grid;
  grid-template-columns: auto auto;
}

.items {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}

.title-container {
  display: flex;
  align-items: center;

  color: var(--ctp-peach);
  text-transform: var(--text-transform);
  font-size: var(--text-size-label);
}

.active-tab,
.non-active-tab {
  padding: var(--padding-text);
  margin: var(--padding-spacer);

  border-radius: var(--border-radius);

  font-size: var(--text-size-label);
  text-transform: uppercase;
}

.active-tab {
  color: var(--ctp-text);
  background-color: var(--ctp-surface2);
}

.non-active-tab {
  color: var(--ctp-subtext0);
}

.non-active-tab:hover {
  background-color: var(--ctp-surface1);

  cursor: pointer;
}
</style>
