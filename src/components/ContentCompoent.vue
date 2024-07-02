<script setup>

import ProLists from "./content/ProLists.vue";
import ProblemList from "./content/prolists/ProblemList.vue";
import ProblemsList from "./content/ProblemsList.vue";
import DetailInfo from "./content/DetailInfo.vue";
import store from "../store.js";
import { watch, ref, nextTick } from "vue";

</script>

<template>
  <div style="padding: 2rem;height: calc(100vh - 100px - 4rem)" >·
    <!--<ProLists></ProLists>-->
    <!--<ProblemsList></ProblemsList>-->
    <!--  <ProblemsList></ProblemsList>-->

    <Component :is="store.view"></Component>

  </div>

</template>

<style scoped>

</style>