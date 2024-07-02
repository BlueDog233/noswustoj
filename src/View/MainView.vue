<template>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <div class="mainview" >
    <aside class="sidebar">
      <div class="logo flex align-middle ">
        <span class="material-symbols-outlined text-5xl" style="font-size: 5rem">
key
</span>
      </div>
      <nav>
        <SiderComponent></SiderComponent>
      </nav>
    </aside>
    <main class="content flex-grow " >
        <HeaderCompoent></HeaderCompoent>
        <div class="flex">
          <ContentCompoent class="flex-grow "></ContentCompoent>
           <PipeLine ></PipeLine>
        </div>
    </main>
  </div>
</template>
<style >
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  display: flex;
  height: 100vh;
  background-color: #f5f5f5;
}

.mainview {
  display: flex;
  width: 100vw !important;
}

.sidebar {
  width: 200px;
  background-color: #2c3e50;
  color: #ecf0f1;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.logo img {
  margin: 20px 0;
}

nav ul {
  list-style: none;
  padding: 0;
  width: 100%;
}

nav ul li {
  padding: 15px;
  text-align: center;
  cursor: pointer;
}

nav ul li.active, nav ul li:hover {
  background-color: #3498db;
}

.content {
  flex: 1;
  display: flex;
  width: 100vw ;
  flex-direction: column;
  margin-left: .1rem;
}

.header {
  display: flex;

  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background-color: #ecf0f1;
  border-bottom: 1px solid #bdc3c7;
}



.table-header button {
  margin-left: 10px;
  padding: 5px 10px;
  cursor: pointer;
}

table {
  width: 100%;
  border-collapse: collapse;
  background-color: #fff;
}

th, td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #ecf0f1;
}


@media (prefers-color-scheme: dark) {
  body {
    background-color: #2c3e50;
    color: #ecf0f1;
  }

  .sidebar {
    background-color: #34495e;
  }

  .content {
    background-color: #34495e;
  }

  .header {
    background-color: #2c3e50;
    border-bottom: 1px solid #7f8c8d;
  }

  table {
    background-color: #34495e;
  }

  th {
    background-color: #2c3e50;
  }

  th, td {
    border-bottom: 1px solid #7f8c8d;
  }
}
</style>
<script setup>
import SiderComponent from "../components/SiderComponent.vue";
import ContentCompoent from "../components/ContentCompoent.vue";
import HeaderCompoent from "../components/HeaderCompoent.vue";
import PipeLine from "../components/content/PipeLine.vue";
</script>