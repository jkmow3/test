import Vue from 'vue'
import App from './App.vue'
// 引入bootstrap
// import './assets/css/bootstrap.css'
// // 引入echarts
// import echarts from 'echarts'

// //	将echarts原型指向VUE,,如果不理解，这里不深究，这是js很高级部分的内容
// Vue.prototype.$echarts = echarts
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);


Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
