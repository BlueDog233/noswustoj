<script setup>
const item=defineProps(['item'])
</script>

<template>
  <div class="flex min-h-7  p-0 justify-center font-black text-xl text-gray-400">
    <div class="title max-w-[90%] overflow-hidden text-nowrap">{{item.item.title}}</div>
  </div>
</template>

<style scoped>

</style>