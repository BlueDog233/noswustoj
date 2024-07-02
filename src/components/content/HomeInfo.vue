<script setup>
import {computed, onMounted, reactive, ref} from "vue";
import axios from "axios";
import * as base64 from "js-base64";
function encodePS(password) {
  for (let i = 0; i < 10; i++) {
    password = base64.encode(password);
  }
  return password;
}
const login=()=>{

    axios.get("https://acm.swust.edu.cn/api/user/signin.do",{
      params: {
        ...form,
        password: encodePS(form.password)
      },
      headers: {
        Referer: "https://acm.swust.edu.cn/",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36 Edg/125.0.0.0"
      }
    }).then((res)=>{
      if(res.data.data){
          token.value=res.data.access_token
          localStorage.setItem("token",token.value)
        console.log("登录成功,获得token!!"+token.value)

      }else{
        alert("错误错误！！")
      }
    })
}



const form=reactive({
  username:undefined,
  password:undefined,
  verifyCode:undefined,
  uuid:undefined
})
let token = ref('token')
const photo = ref('')
const uuid = ref('')

const refrshC = () => {
  token.value = localStorage.getItem('token') ?? 'notoken'
  axios.get("https://acm.swust.edu.cn/api/pic.do?w=100&h=40").then((res) => {
    photo.value = res.data.data.img
    form.uuid = res.data.data.uuid
  })
}
onMounted(() => {
  refrshC()
})
</script>

<template>
  <div class="flex justify-center  ">
    <div class="w-[30rem] bg-[#2c3e50] p-7 " style="border:2rem solid #2c3e50;border-radius: 3rem">
      <div class="bg-gray-800 p-6 rounded-md overflow-hidden shadow-lg text-white mb-4">
        <pre class="text-red-400 text-xl">TOKEN</pre>
        <pre class="text-green-400 text-sm text-wrap h-auto block"><code>{{ token }}</code></pre>
      </div>
      <div>
        <h2 class="mt-6 text-center text-3xl font-extrabold text-white">
          Token修改
        </h2>
      </div>
      <form class="mt-8 space-y-6 ">
        <div class="rounded-md shadow-sm -space-y-px flex flex-col gap-1">
          <div>
            <label for="email-address" class="sr-only">账号</label>
            <input id="email-address" name="email" type="email" autocomplete="email" required
                   v-model="form.username"
                   class="appearance-none rounded-none relative block w-full px-3 py-2
                          border border-gray-300 placeholder-gray-500 text-gray-900
                          rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500
                          focus:z-10 sm:text-sm" placeholder="账号">
          </div>
          <div>
            <label for="password" class="sr-only">密码</label>
            <input id="password" name="password" type="password" autocomplete="current-password" required
                   v-model="form.password"
                   class="appearance-none rounded-none relative block w-full px-3 py-2
                          border border-gray-300 placeholder-gray-500 text-gray-900
                          rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500
                          focus:z-10 sm:text-sm" placeholder="密码">
          </div>
          <div class="flex gap-5">
            <label for="verification" class="sr-only">验证码</label>
            <input id="verification" name="verification" type="text" autocomplete="off" required
                   v-model="form.verifyCode"
                   class="appearance-none rounded-none relative block w-full px-3 py-2
                          border border-gray-300 placeholder-gray-500 text-gray-900
                          rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500
                          focus:z-10 sm:text-sm" placeholder="验证码">
            <img class="w-[200px] h-[40px]"  :src="'data:image/png;base64,'+photo" @click="refrshC"/>
          </div>
        </div>
        <div>
          <button type="submit"
                  @click="login"
                  class="group relative w-full flex justify-center py-2 px-4 border border-transparent
                         text-sm font-medium rounded-md text-white bg-indigo-600
                         hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
            修改Token
          </button>

        </div>
      </form>

    </div>

  </div>
</template>

<style scoped>

</style>