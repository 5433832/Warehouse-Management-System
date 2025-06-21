<script>
import App_Header from '../header/App_Header.vue';
import Partner from '../Body/Partner.vue';
import Footer from '../Body/Footer.vue';
import Introduce from '../Body/Introduce.vue'

export default {
  name: "Index",
  data() {
    return {}
  },
  components: {
    App_Header,
    Partner,
    Footer,
    Introduce,
  }
}
</script>

<template>
  <div id="index">
    <el-container style="height: 100%;width: 100%;">
      <!--      头部组件占据百分之10高度-->
      <el-header>
        <App_Header></App_Header>
      </el-header>
      <el-main>
        <router-view></router-view>
        <el-footer
          style="margin-top: 10%;width: 100%;height: 20%;">
          <Footer></Footer>
        </el-footer>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
html, body, #index {
  padding: 0;
  margin: 0;
  height: 100%;
  width: 100%;
}

.el-header {
  width: 100%;
  height: 20%;
  padding: 0;
  margin: 0;
  text-align: center;
}


.el-main {
  height: 80%;
  width: 100%;
  padding-right: 10%;
  padding-left: 10%;
  margin: 0;
}
</style>
