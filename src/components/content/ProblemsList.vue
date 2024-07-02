<template>
  <div style="overflow: scroll; max-height: 60vh">
    <table class="table-auto w-full text-left ">
      <thead>
      <tr>
        <th class="px-4 py-2">AC?</th>
        <th class="px-4 py-2">Problem</th>
        <th class="px-4 py-2">Title</th>
        <th class="px-4 py-2">Score</th>
        <th class="px-4 py-2">AC!</th>
      </tr>
      </thead>
      <tbody>
      <tr class="border-t dark:border-zinc-700" v-for="x in store.waitsdo">
        <td class="px-4 py-2" v-if="x.userAc">✔️</td>
        <td class="px-4 py-2" v-else>❌</td>
        <td class="px-4 py-2"><a href="#" class="text-blue-500" @click="goto(x.problemId)">{{ x.problemId }}</a></td>
        <td class="px-4 py-2">{{ x.title }}</td>
        <td class="px-4 py-2">{{ x.score }}</td>
        <td class="px-4 py-2">
          <div v-if="x.canac" class="cursor-pointer bg-green-500 hover:bg-green-600 active:bg-green-700 text-white text-center py-2 px-4 rounded transition duration-200" @click="aac(x.problemId,x.title)">
            一键AC
          </div>
          <div v-else class="cursor-not-allowed bg-red-500 hover:bg-red-600  text-white text-center py-2 px-4 rounded transition duration-200">
            无法AC
          </div>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
<PageC @change="refresh" v-model="page"></PageC>
</template>
<style scoped>
::-webkit-scrollbar{
  width: 0;
}
</style>
<script setup>
import store from "../../store.js";
import axios from "axios";
import {onMounted, ref} from "vue";
import PageC from "./PageC.vue";
import ProblemsList from "./ProblemsList.vue";
import DetailInfo from "./DetailInfo.vue";
const page=ref(1)
const refresh = async () => {
  const a = await axios.get(
      "https://acm.swust.edu.cn/api/contest/problem.do?rows=20&page=" +
      page.value +
      "&contestId=" +
      store.psid,
      {
        headers:{Authorization:'Bearer '+localStorage.getItem("token")}
      }
  );

  const promises = a.data.data.pagingList.map(async (x) => {
    const canac = await store.canac(x.problemId);
    return {
      ...x,
      canac: canac.data.data
    };
  });
  store.waitsdo = await Promise.all(promises);
  console.log(store.waitsdo);
};
const aac=(id,title)=>{

  store.pushPepi(id,title,store.psid)
}
onMounted( async ()=>{
  await refresh()

})
const goto=(x)=>{
  axios.get("https://acm.swust.edu.cn/api/problem.do?ids="+x+"&tagsReturn=2&page=1&rows=1").then((res)=>{
    store.nowdo=res.data.data.pagingList[0]
    store.view=DetailInfo
  })
}
</script>