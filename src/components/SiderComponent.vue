<template>
  <div class="sidebar">

    <ul class="menu">
      <li class="menu-item" :class="{ active: activeMenuItem === 'home' }" @click="select('home',HomeInfo)"><i class="icon not-italic">🏠</i> 首页</li>
  <template v-if="storea">
      <li class="menu-item" :class="{ active: activeMenuItem === 'library' }" @click="select('library',ProLists)"><i class="icon not-italic">📚</i> 题库</li>
      <li class="menu-item" :class="{ active: activeMenuItem === 'status' }" @click="select('status',StateList)"><i class="icon not-italic">🔍</i> 状态</li>
  </template>

      <li class="menu-item" :class="{ active: activeMenuItem === 'about' }" @click="select('about')"><i class="icon not-italic">ℹ️</i> 关于</li>
    </ul>

  </div>
</template>

<script>
import {ref, watch} from "vue";
import HomeInfo from "./content/HomeInfo.vue";
import ProLists from "./content/ProLists.vue";
import ProblemsList from "./content/ProblemsList.vue";
import StateList from "./content/StateList.vue";
import store from "../store.js";

export default {
  computed: {
    store() {
      return store
    },
    StateList() {
      return StateList
    },
    ProblemsList() {
      return ProblemsList
    },
    ProLists() {
      return ProLists
    },
    HomeInfo() {
      return HomeInfo
    }
  },
  setup() {
    const activeMenuItem = ref('home');
    const storea = ref(localStorage.getItem('token'));

    const select = (menuItem,x) => {
      activeMenuItem.value = menuItem;
      store.view=x
    };

    watch(() => localStorage.getItem('token'), (newValue) => {
      storea.value = newValue;
    });

    return { activeMenuItem, select, storea }
  }
}
</script>

<style setup>
.sidebar {
  width: 80px;
  background-color: white;
  padding: 10px 0;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.logo {
  margin-bottom: 20px;
}

.menu {
  list-style: none;
  padding: 0;
  margin: 0;
  width: 100%;
}

.menu-item {
  width: 100%;
  padding: 10px 0;
  font-weight: 800;
  text-align: center;
  color: rgba(36, 37, 38, 0.81);
  cursor: pointer;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.menu-item i.icon {
  font-size: 20px;
  margin-bottom: 5px;
}

.menu-item.active {
  background-color: #4A90E2;
  color: white;
}
.sidebar{
  height: 100vh;
}
</style>