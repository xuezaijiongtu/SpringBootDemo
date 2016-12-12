<template>
  <div id="app">
    <header-tool></header-tool>
    <sidebar-tool></sidebar-tool>
    <main-container></main-container>
    <footer-tool></footer-tool>
  </div>
</template>

<script>
import HeaderTool from './components/Header'
import SidebarTool from './components/Sidebar'
import MainContainer from './components/MainContainer.vue'
import FooterTool from './components/Footer.vue'

export default {
  components: {
    HeaderTool,SidebarTool,MainContainer,FooterTool

  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
