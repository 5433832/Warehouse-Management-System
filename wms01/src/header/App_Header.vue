<script>


export default {
  name: "app_header",
  components: {},
  data() {
    return {
      //默认隐藏退出按钮显示登录按钮
      退出按钮是否隐藏: true,
      login按钮是否隐藏: false,

      //默认菜单下横线位置
      activeIndex: '1',
      // JSON.parse将数据解析为js对象
      前台用户登录信息: JSON.parse(sessionStorage.getItem('前台用户登录信息'))

    };
  },
  methods: {
    //点击logo跳到主页
    Jump_Index() {
      this.$router.push("/")
    },
    Jump_Partner() {
      this.$router.push("/Partner")
    },
    Jump_Login() {
      this.$router.push("/Login")
    },
    Jump_Announcement() {
      this.$router.push("/Announcement")
    },
    logout() {
      this.$confirm('此操作将退出, 是否继续?', '提示', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        //跳转到首页
        this.$router.push("/")
        //清空sessionStorageAPI
        sessionStorage.clear()

        this.$message({
          type: 'success',
          message: '退出成功!'
        });
        //刷新页面
        window.location.reload();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      });
    }
  },
  // 在组件挂载开始之前被调用。此时模板编译已完成，但并未将组件挂载到页面上。
  beforeMount() {
    // 判断当前网址路径
    switch (this.$route.path) {
      case '/Partner':
        this.activeIndex = '3'
        break;
      case '/':
        this.activeIndex = '1'
        break;
      case '/Announcement':
        this.activeIndex = '2'
        break;
      default:
        this.activeIndex = '4'
        break
    }
  },
  // 实例创建完成后被调用，可以访问数据和操作方法
  created() {
    // 判读前台用户登录信息是否存在，存在则将登录或注册按钮禁用
    if (this.前台用户登录信息) {
      this.login按钮是否隐藏 = true
      this.退出按钮是否隐藏 = false
      // console.log(this.前台用户登录信息)
    }
  }

}
</script>

<template>
  <div style="width: 100%;height: 100%;line-height: 100%;">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" style="display: flex;
    justify-content: center;">
      <img src="./logo/1.svg"
           alt="Element_Logo"
           style="float: left;
           margin-right: 50%;
           cursor: pointer"
           @click="Jump_Index">
      <el-menu-item index="1" @click.native="Jump_Index">平台介绍</el-menu-item>
      <el-menu-item index="2" @click.native="Jump_Announcement">仓库公告</el-menu-item>
      <el-menu-item index="3" @click.native="Jump_Partner">合作伙伴</el-menu-item>

      <el-submenu index="4">
        <template slot="title">个人中心</template>
        <el-menu-item index="4-1">
          <el-button @click.native="Jump_Login" style="border: none" :disabled="login按钮是否隐藏">登录或注册
          </el-button>
        </el-menu-item>
        <el-menu-item index="4-3">
          <el-button style="border: none" :disabled="退出按钮是否隐藏" @click.native="logout">退出</el-button>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<style scoped>

</style>