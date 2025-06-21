<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

//导入组件，并创建一个名为Index的变量来引用
import Index from "./components/Index.vue";
//导入全局样式
// import './assets/global.css';
//export default语句是用于将变量、函数、类或对象导出为模块的公共接口
export default {
  //定义name属性设置组件名称为App
  name: 'App',
  //利用components属性注册组件
  components: {},
  data() {
    return {
      user: JSON.parse(sessionStorage.getItem('登录信息'))
    }
  },
  watch: {
    '$store.state.menu': {
      handler(val, old) {
        if (!old && this.user && this.user.用户名称) {
          this.$store.commit("setRouter", val)
        }
      },
      //立即执行回调函数
      immediate: true
    }
  }
}
</script>

<style>
#app {
  height: 100%;
  width: 100%;
}

* {
  margin: 0;
  padding: 0;
  border: 0;
  box-sizing: border-box;
}
</style>
