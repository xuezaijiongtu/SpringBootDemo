import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import Ads from './components/detail/home_ads.vue'
import Users from './components/detail/users.vue'
import Agents from './components/detail/home_agent.vue'
import Configs from './components/detail/cfg_ads.vue'


import './assets/css/bootstrap.css'
import './assets/css/custom.css'
import './assets/css/main.css'
import './assets/js/jquery_2.1.0.min.js'
import './assets/js/bootstrap.min.js'
import Layout from './assets/js/layout.debug.js'
// import  './assets/js/config.js'

Vue.config.debug = true;

Vue.use(VueRouter)
Vue.use(VueResource)

var router = new VueRouter({
	routes:[
		{path:'/ads',component:Ads},
		{path:'/users',component:Users},
		{path:'/agents',component:Agents},
		{path:'/configs',component:Configs},
	]

})
/* eslint-disable no-new */
var APP = new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  render:h =>h(App)
});

var exports_data = require('./assets/js/config.js')
Layout(exports_data);