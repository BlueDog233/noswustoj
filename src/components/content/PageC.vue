<script setup>
import { ref, defineEmits,defineModel } from "vue";
const emit=defineEmits(['change'])
const page = defineModel()
const goToPage = () => {
  const num = parseInt(enteredPage.value);
  if (num >= 1) {
    page.value = num;
  }
  enteredPage.value = '1';
  emit('change')
};
const enteredPage = ref('1');
const next = () => {
  page.value += 1;
  emit('change')
};
const prev = () => {
  if (page.value > 1) {
    page.value -= 1;
  }
  emit('change')
};
</script>

<template>
  <div class="p-6 flex justify-center items-center dark:bg-gray-800 ">
    <button @click="prev" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-l">
      上一页
    </button>
    <div class="mx-2">
      当前页 {{ page }}
    </div>
    <button @click="next" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4">
      下一页
    </button>
    <div class="mx-2 flex items-center">
      <input type="number" v-model.number="enteredPage" class="w-12 bg-white shadow appearance-none border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" min="1">
      <button @click="goToPage" class="ml-2 bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-l rounded-r">
        跳转
      </button>
    </div>
  </div>
</template>

<style scoped>

</style>