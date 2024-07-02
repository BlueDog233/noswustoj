<script setup>

import ProblemList from "./prolists/ProblemList.vue";
import axios from "axios";
import {onMounted, ref} from "vue";
import PageC from "./PageC.vue";
onMounted(()=>{
  change()
})
const vs=ref([])
const change=()=>{
  axios.get("https://acm.swust.edu.cn/api/contest.do?rows=20&page="+page.value).then((res)=>{
    vs.value=res.data.data.pagingList
  })

}
const page=ref(1)
</script>

<template>
  <tr>
    <th style="width: 100vw;text-align: center">训练题库</th>
  </tr>
  <div class="overflow-scroll max-h-[65vh] ">
    <ProblemList v-for="x in vs" :prl="x"></ProblemList>
  </div>
  <PageC @change="change" v-model="page"></PageC>
</template>

<style scoped>
::-webkit-scrollbar{
  width: 0;
}
</style>