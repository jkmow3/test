import Vue from 'vue'
import VueRouter from 'vue-router'
//  引入所有子组件
import Table from "../components/Table";
import Home from "../components/Home";
import echarts from "../components/Echart";
import Search from "../components/Search";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    // 在这里注册组件
    component: Home
  },
  {
    path: '/echarts',
    name: 'echarts',
    // 在这里注册组件
    component: echarts
  },
  {
    path: '/search',
    name: 'search',
    // 在这里注册组件
    component: Search
  },
  {
    path: '/table',
    name: 'table',
    // 在这里注册组件
    component: Table
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
