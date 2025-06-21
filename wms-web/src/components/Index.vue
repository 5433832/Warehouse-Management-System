<script>
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
// import Main from "@/components/Main.vue";
import aside from "@/components/Aside.vue";


export default {
  name: "Index",
  computed: {
    aside() {
      return aside
    }
  },
  components: {Header, Aside},
  //data用于定义组件初始数据
  data() {
    return {
      //定义侧边栏收缩初始状态为false
      isCollapse: false,
      aside_width: '200px',
      icon: 'el-icon-s-fold',
      loading: true,
    }
  },
  methods: {
    doCollapse() {
      // 获取到监听事件后执行此方法将侧边栏收缩状态取反
      this.isCollapse = !this.isCollapse
      //判断收缩状态（如果为真就取假值，反之）
      if (!this.isCollapse) {
        this.aside_width = '200px'
        this.icon = 'el-icon-s-fold'
      } else {
        this.aside_width = '60px'
        this.icon = 'el-icon-s-unfold'
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.loading = false;
    });
  },

}
</script>

<template>
  <div class="indexBody">
    <el-container v-loading="loading"
                  element-loading-text="拼命加载中"
                  element-loading-spinner="el-icon-loading"
                  element-loading-background="rgba(0, 0, 0, 0.8)" style="height: 100%;width: 100%;">
      <!--  侧边栏部分-->
      <el-aside :width="aside_width" style="background-color: rgb(238, 241, 246);height: 100vh">
        <!--      :isCollapse="isCollapse"用于将父组件中的数据传递给子组件-->
        <!--      isCollapse是父组件属性-->
        <!--      :isCollapse将isCollapse属性的值绑定到子组件的prop中-->
        <!--      将isCollapse中的值传递给Aside组件的isCollapse prop中-->
        <Aside :isCollapse="isCollapse"></Aside>
      </el-aside>
      <!--    头部部分-->
      <el-container style="height: 100%">
        <el-header style="text-align: right; font-size: 12px;height: 100%;border-bottom: #a1a0a0 1px solid">
          <!--        设置监听@doCollapse用来监听doCollapse事件-->
          <Header @doCollapse="doCollapse" :icon="icon"></Header>
        </el-header>
        <!--      Main部分-->
        <el-main style="height: 100%">
          <!--          <Main></Main>-->
          <!--          匹配组件的占位符。<router-view></router-view> 会根据当前 URL 的路径匹配到相应的路由组件，并将其渲染在此处-->
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>

.indexBody {
  margin: 0;
  background-image: url(./img/bg.png);
  background-size: 180%;
  background-repeat: no-repeat;
  background-position: center center; /* 将背景图片居中 */
  width: 100%;
  height: 100%;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-main {
  padding: 5px;
}

.el-aside {
  color: #333;
}
</style>

