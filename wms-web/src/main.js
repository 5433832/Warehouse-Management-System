import Vue, {useAttrs} from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import VueRouter from 'vue-router';
import router from './router/index';
import store from "@/store/index";
//将 Axios（一个用于进行 HTTP 请求的 JS库）设置为 Vue 的全局属性 $axios。这样可以在 Vue 组件中通过 this.$axios 访问 Axios 实例，方便进行 HTTP 请求
Vue.prototype.$axios = axios;
//将url地址赋值给全局属性￥httpUrl，方便全局使用相同的地址
Vue.prototype.$httpUrl = 'http://localhost:8090'
Vue.config.productionTip = false
Vue.use(ElementUI, {size: "small"});
Vue.use(VueRouter)
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
