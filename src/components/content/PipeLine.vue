<script setup>

import PepeItem from "./pipeitem/PepeItem.vue";
import store from "../../store.js";
import {onMounted} from "vue";
onMounted(()=>{
  store.start()
})
</script>

<template>
<div class=" min-w-80 max-w-80 h-[100vh] border-2 border-b-gray-50 flex justify-center align-middle flex-col" style="background-color: #2c3e50;">
  <H2 class="text-center min-h-10 " style="padding-top: 1rem;border-bottom: 3px solid darkorange ">列表流水线</H2>
  <div class="pipelines  flex-grow overflow-scroll ">
      <pepe-item v-for="item in store.pepilines" :item="item" :key="item.id"></pepe-item>
  </div>
  <div class="footer h-40">

  </div>
</div>
</template>

<style scoped>
::-webkit-scrollbar{
  width: 0;
}
</style>