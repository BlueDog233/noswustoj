<script setup>
import axios from "axios";
import store from "../../../store.js";
import ProblemsList from "../ProblemsList.vue";
import {ref} from "vue";

const prop=defineProps(['prl'])


const join=()=>{
  axios.get("https://acm.swust.edu.cn/api/contest/problem.do?rows=20&page=1&contestId="+prop.prl.id,{headers:{Authorization:'Bearer '+localStorage.getItem("token")}}).then((res)=>{
    store.view=ProblemsList
    store.psid=prop.prl.id
    store.waitsdo=res.data.data.pagingList
  })
}
const aac = async () => {
  const response = await axios.get(
      "https://acm.swust.edu.cn/api/contest/problem.do?" +
      "rows=300&page=1&contestId=" +
      prop.prl.id
  )

  const promisedResults = response.data.data.pagingList.reduce((acc, x) => {
    return acc.then(async (filteredResults) => {
      const t = await store.canac(x.problemId)
      if (t.data.data) {
        store.pushPepi(x.problemId, x.title, prop.prl.id)
        return [...filteredResults, x]
      } else {
        return filteredResults
      }
    })
  }, Promise.resolve([]))

  const filteredResults = await promisedResults
  filteredResults.forEach(x => console.log(x))
}
</script>

<template>
  <div
      style="display: flex; align-items: center; justify-content: space-between; padding: 10px; border-bottom: 1px solid #9d9898; background-color: rgba(255,255,255,0); color: #000000;"
  >
    <div style="display: flex; align-items: center;" @click="join">
      <span v-if="prop.prl.status === 0" class="text-green-500 mr-3">进行中</span>
      <span v-else class="text-red-500 mr-3">已结束</span>
      <span class="title" style=";margin-right: 10px;">{{prop.prl.title}}</span>

<!-- We've added the following line to display a loading message when required. -->
      <div v-if="loading" class="w-6 h-6 border-t-2 border-green-500 animate-spin rounded-full"></div>

    </div>
    <div class="grow"></div>
    <div v-if="prop.prl.status==0" class="cursor-pointer bg-green-500 hover:bg-green-600 active:bg-green-700 text-white text-center py-2 px-4 rounded transition duration-200" @click="aac">
      一键AC
    </div>
  </div>
</template>

<style scoped>
.title{
  cursor: pointer;
  color: whitesmoke;
  transition: all .3s ease;
}
.title:hover{
  color: #888888;
}
</style>