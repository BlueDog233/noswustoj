<script setup>

import {onMounted, ref} from "vue";
import {VAceEditor} from "vue3-ace-editor"
import store from "../../store.js";
import axios from "axios";

const codeInput = ref('aaa')
const languages = ref(['C++'])
const selectedLanguage = ref(languages.value[0])

const submit = () => {
  console.log('Submit clicked')
  axios.post('https://acm.swust.edu.cn/api/submit.do', {
    compilerId: 2,
    contestId: String(store.psid),
    problemId: String(store.nowdo.id),
    source: codeInput.value
  }, {
    headers: {
      'Authorization': 'Bearer '+localStorage.getItem('token')
    }
  })
}
onMounted(()=>{
  console.log(store.nowdo.id)
})
const next = () => {
  console.log('Next clicked')
}
</script>

<template>
  <div class="rounded-b" style="display: grid;grid-template-columns: 1fr 1fr">
    <div class="max-w-4xl mx-auto p-6 bg-white dark:bg-zinc-800  shadow-md overflow-y-auto max-h-screen"
         style="overflow: scroll">
      <div class="mb-6">
        <h1 class="text-2xl font-bold text-zinc-900 dark:text-zinc-100">{{ store.nowdo.id }}:
          {{ store.nowdo.title }}</h1>
        <div class="mt-2 flex gap-2">
          <span class="bg-zinc-200 dark:bg-zinc-700 text-zinc-800 dark:text-zinc-200 px-3 py-1 rounded">标签:</span>
          <span
              class="bg-zinc-200 dark:bg-zinc-700 text-zinc-800 dark:text-zinc-200 px-3 py-1 rounded">难度: 一般</span>
          <span
              class="bg-zinc-200 dark:bg-zinc-700 text-zinc-800 dark:text-zinc-200 px-3 py-1 rounded">作者: 艺术家</span>
        </div>
      </div>
      <div class="mb-6">
        <h2 class="text-xl font-semibold text-zinc-900 dark:text-zinc-100">题目描述</h2>
        <p class="mt-2 text-zinc-800 dark:text-zinc-200" v-html="store.nowdo.description"></p>
        <p class="mt-2 text-zinc-800 dark:text-zinc-200" v-html="store.nowdo.input"></p>
      </div>
      <div class="mb-6">
        <h2 class="text-xl font-semibold text-zinc-900 dark:text-zinc-100">输入</h2>
        <input type="text"
               class="w-full px-4 py-2 mt-2 border border-zinc-300 dark:border-zinc-600 rounded bg-zinc-100 dark:bg-zinc-700 text-zinc-900 dark:text-zinc-100"
               placeholder="输入为按键盘输入的由大写英文字母和“#”字符构成的一个字符串（用于创建对应的二叉树）。">
      </div>
      <div class="mb-6">
        <h2 class="text-xl font-semibold text-zinc-900 dark:text-zinc-100">输出</h2>
        <p class="mt-2 text-zinc-800 dark:text-zinc-200" v-html="store.nowdo.output"></p>
      </div>
      <div class="mb-6 flex flex-wrap gap-2">
        <div class="w-full md:w-1/2 p-2">
          <h3 class="text-lg font-semibold text-zinc-900 dark:text-zinc-100">样例输入</h3>
          <div v-for="(line, index) in String(store.nowdo.sampleInput).split('\n\r')" :key="index">
            <pre class="bg-zinc-100 dark:bg-zinc-700 p-4 rounded text-zinc-900 dark:text-zinc-100">{{ line }}</pre>
          </div>
        </div>
        <div class="w-full md:w-1/2 p-2">
          <h3 class="text-lg font-semibold text-zinc-900 dark:text-zinc-100">样例输出</h3>
          <div v-for="(line, index) in String(store.nowdo.sampleOutput).split('\n\r')" :key="index">
            <pre class="bg-zinc-100 dark:bg-zinc-700 p-4 rounded text-zinc-900 dark:text-zinc-100">{{ line }}</pre>
          </div>
        </div>
      </div>
      <div class="mb-6">
        <p class="mt-2 text-zinc-800 dark:text-zinc-200">题目来源: 数据结构课程组</p>
        <p class="mt-2 text-zinc-800 dark:text-zinc-200">时间限制: 1000ms/10000kb</p>
        <p class="mt-2 text-zinc-800 dark:text-zinc-200">通过次数: 6613</p>
        <p class="mt-2 text-zinc-800 dark:text-zinc-200">提交次数: 17433</p>
      </div>
    </div>
    <div class="bg-gray-950 text-white p-4">
      <h2 class="text-2xl font-bold mb-4">代码输入:</h2>
      <VAceEditor

          v-model="codeInput"
          lang="C++"
          theme="monokai"
          class="w-full h-64 rounded bg-gray-800 text-white"
          :options="{ wrap: true }"
      />
      <div class="bg-gray-900 text-white  p-4">
        <button

            class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded m-2" @click="submit">
          提交
        </button>

        <select class="bg-gray-300 text-black py-2 px-4 rounded m-2" v-model="selectedLanguage">
          <option v-for="language in languages" :key="language">{{ language }}</option>
        </select>
      </div>
    </div>


  </div>


</template>

<style scoped>
::-webkit-scrollbar {
  width: 0px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background: #888;
  border-radius: 6px;
}

::-webkit-scrollbar-thumb:hover {
  background: #555;
}

.dark ::-webkit-scrollbar-track {
  background: #2d2d2d;
}

.dark ::-webkit-scrollbar-thumb {
  background: #444;
}

.dark ::-webkit-scrollbar-thumb:hover {
  background: #222;
}
</style>