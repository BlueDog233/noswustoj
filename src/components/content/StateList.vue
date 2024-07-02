<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";
import PageC from "./PageC.vue";
const vs=ref([])
const page=ref(1)
onMounted(()=>{
  axios.get("https://acm.swust.edu.cn/api/submit.do?status=&compilerId=&username=&problemId=&rows=10&page="+page.value+"&contestId=").then((res)=>{
    vs.value=res.data.data.pagingList
  })
})
const change=(e)=>{
  axios.get("https://acm.swust.edu.cn/api/submit.do?status=&compilerId=&username=&problemId=&rows=10&page="+page.value+"&contestId=").then((res)=>{
    vs.value=res.data.data.pagingList
  })
}
</script>

<template>
  <section class="table-container" style="margin-top: -3rem">
    <div class="table-header">
      <button>📋 All</button>
      <button>🔄 Refresh</button>
    </div>
    <table>
      <thead>
      <tr>
        <th>Run ID</th>
        <th>Problem ID</th>
        <th>Username</th>
        <th>Result</th>

      </tr>
      </thead>
      <tbody>
      <tr v-for="x in vs">
        <td>{{x.id}}</td>
        <td><a href="#">{{x.problemId}}</a></td>
        <td><a href="#">{{x.username}}</a></td>
        <td><span class="badge warning">{{x.status}}</span></td>

      </tr>

      </tbody>

    </table>
    <PageC v-model="page" @change="change"></PageC>
  </section>
</template>

<style scoped>
.table-container {
  padding: 20px;
  overflow-x: auto;
}

.table-header {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 10px;
}

.badge {
  padding: 5px 10px;
  border-radius: 5px;
  color: #fff;
}

.badge.success {
  background-color: #2ecc71;
}

.badge.warning {
  background-color: #e67e22;
}
body.dark .dark\:bg-gray-800 {
  background-color: #2d3748;
}

</style>