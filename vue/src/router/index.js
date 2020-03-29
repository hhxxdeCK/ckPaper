import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'
import FindThingsPage from "../views/find/FindThingsPage"
import RecruitmentPage from "../views/find/RecruitmentPage"
import FindSearchPage from "../views/find/FindSearchPage"
import RecruitmentSearchPage from "../views/find/RecruitmentSearchPage"

import Homepage from "../views/find/Homepage"
import Home from "./../views/find/Home.vue"
//去除懒加载，获取环境
const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  { path: '/home', component: Home, hidden: true },
  { path: '/login', component: _import('login/index'), hidden: true },
  { path: '/404', component: _import('404'), hidden: true },
  /*{
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
    }]
  }*/
]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  {
    path: '/system',
    component: Layout,
    redirect: '/system/article',
    name: '功能模块',
    meta: { title: '功能模块', icon: 'tree' },
    children: [
      {
        path: 'article',
        name: '启事',
        component: _import('article/article'),
        meta: { title: '启事', icon: 'example' },
        menu: 'article'
      },
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: { title: '用户权限', icon: 'table' },
    children: [
      {
        path: '', name: '用户列表', component: _import('user/user'), meta: { title: '用户列表', icon: 'user' }, menu: 'user'
      },
      {
        path: 'role',
        name: '权限管理',
        component: _import('user/role'),
        meta: { title: '权限管理', icon: 'password' },
        menu: 'role'
      },
    ]
  },
  { path: '*', redirect: '/404', hidden: true },

  {
    path: '/',
    name: 'home',
    component: Home,
    redirect: '/hihome',
    children: [
      {
        path: '/hihome',
        name: 'homeIndex',
        component: Homepage
      },
      {
        path: '/findThings',
        name: 'findThings',
        component: FindThingsPage
      },
      {
        path: '/recruitment',
        name: 'recruitment',
        component: RecruitmentPage
      },
      {
        path: '/findSearch',
        name: 'findSearch',
        component: FindSearchPage
      },
      {
        path: '/recruitmentSearch',
        name: 'recruitmentSearch',
        component: RecruitmentSearchPage
      },
      {
        path: '/userPage', name: '用户列表', component: _import('user/user'), meta: { title: '用户列表', icon: 'user' }, menu: 'user'
      },
    ]
  }
]
