<script>
export default {
  name: "Aside",
  data() {
    return {
      // isCollapse:false
      // menu: [
      //   {
      //     menuClick: 'Admin',
      //     menuName: '管理员管理',
      //     menuIcon: 'el-icon-s-custom'
      //   }, {
      //     menuClick: 'User',
      //     menuName: '用户管理',
      //     menuIcon: 'el-icon-user'
      //   }
      // ]
    }

  },
  mounted() {
    console.log(this.$store.state.menu)
  },
  computed: {
    // 获取vuex store中的menu状态
    "menu": {
      // 通过get方法获取menu的值
      get() {
        return this.$store.state.menu
      },
    }
  },
  //props定义组件属性，通过在props中指定属性可以接收外部数据
  props: {
    // 声明isCollapse属性，类型为布尔型；用来接受父组件isCollapse属性的值,两个属性名称需一直
    isCollapse: Boolean
  }
}
</script>

<template>
  <!--            active-text-color定义元素处于激活是的文本颜色-->
  <!--  default-active标识默认激活的属性-->
  <!--  collapse为是否水平折叠收起菜单（仅在 mode 为 vertical 时可用）-->
  <!--  collapse-transition为是否开启折叠动画-->
  <el-menu background-color="#545c64"
           text-color="#fff"
           active-text-color="#ffd04b"
           style="height: 100%;border: 0px;"
           default-active="/Home"
           :collapse="isCollapse"
           :collapse-transition="false"
           router>
    <!--    在上面的标签添加router后会根据以下标签的每个index值取跳转-->
    <!--    index用来标识该菜单中唯一标识值-->
    <el-menu-item index="/Home">
      <i class="el-icon-house"></i>
      <span slot="title">首页</span>
    </el-menu-item>

    <el-menu-item :index="'/'+item.菜单函数" v-for="(item,i) in menu" :key="i">
      <i :class="item.菜单图标"></i>
      <span slot="title">{{ item.菜单名称 }}</span>
    </el-menu-item>


  </el-menu>
</template>

<style scoped>

</style>