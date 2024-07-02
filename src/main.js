import { createApp } from 'vue'
import './style/tinywind.css'
import './style.css'
import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router'
const routes = [
    {
        path: '/',
        component: () => import('./View/MainView.vue')
    },
]

// create router instance
const router = createRouter({
    history: createWebHistory(),
    routes
})
const app=createApp(App)
app.use(router)
app.mount('#app')

