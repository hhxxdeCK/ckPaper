import Vue from 'vue'
import 'normalize.css/normalize.css'// A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN'
import App from './App'
import router from './router'
import store from './store'
import '@/icons' // icon
import '@/permission' // 权限
import {default as api} from './utils/api'
import {hasPermission} from "./utils/hasPermission";
import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.prototype.$axios = axios
Vue.use(VueAxios, axios);
axios.defaults.withCredentials = true
axios.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  return response.data;
});
Vue.use(ElementUI)
Vue.prototype.api = api
//全局的常量
Vue.prototype.hasPerm = hasPermission
//生产环境时自动设置为 false 以阻止 vue 在启动时生成生产提示。
Vue.config.productionTip = (process.env.NODE_ENV != 'production')
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
