import { createRouter, createWebHistory } from 'vue-router'
import Login from "../views/Login";
import Main from "../views/Main";
import MemberList from "../views/Member/MemberList";
import MemberLevel from "../views/Member/MemberLevel";
import NotFound from "../views/NotFound";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Login
  },
    // 登录页面
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
    // 后台管理主页面
  {
    path: '/main/:name',
    name: 'Main',
    component: Main,
    // 嵌套路由
    children:[
      { // 会员列表
        path: '/member/list',
        name: 'MemberList',
        component: MemberList
      },
      { // 会员等级
        path: '/member/level/:id',
        name: 'MemberLevel',
        component: MemberLevel,
        props:true
      }
    ]
  },
  {
    path: '/:catchAll(.*)',
    component: NotFound
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
