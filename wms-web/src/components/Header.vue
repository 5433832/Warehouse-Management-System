<script>
export default {
  name: "Header",
  data() {
    return {
      // JSON.parse将数据解析为js对象
      用户名称: JSON.parse(sessionStorage.getItem('登录信息'))
    }
  },
  props: {
    icon: String,
  },
  // methods用于定义组件中可用的方法。这些方法可以被组件内部的其他方法、计算属性以及模板中的事件处理函数调用
  methods: {
    toUser() {
      console.log("跳转到个人中心页面");
      this.$router.push("/Home")
    },
    collapse() {
      // 触发自定义事件doCollapse（监听器与自定义事件名称需一致
      this.$emit('doCollapse')
    },
    logout() {
      this.$confirm('此操作将退出, 是否继续?', '提示', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        //跳转到登录页
        this.$router.push("/")
        //清空sessionStorageAPI
        sessionStorage.clear()
        this.$message({
          type: 'success',
          message: '退出成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      });
    },
  },
  // 实例被创建之后立即调用，通常用来进行一些初始化操作
  created() {
    this.$router.push("/Home")
  }
}
</script>

<template>
  <!--  使用flex布局将子元素水平排列-->
  <div style="display: flex;line-height: 60px;">
    <div style="line-height: 60px;margin-top: 8px;">
      <!--      设置点击后引用collapse方法-->
      <i :class="icon" style="font-size: 23px;cursor: pointer" @click="collapse"></i>
    </div>
    <!--    flex: 1; 使得这个子元素占据剩余空间，使得它和其他子元素之间平均分配可用空间。-->
    <div style="flex: 1;text-align: center;font-size: 30px;">
      <span>欢迎</span>
    </div>
    <el-dropdown>
      <i class="el-icon-arrow-down" style="margin-right: 5px"></i>
      <span>{{ 用户名称.用户名称 }}</span>
      <el-dropdown-menu slot="dropdown" trigger="click">
        <!--        .native将组件绑定原生事件，如果是普通html元素不需要加，但这里是饿了么组件所以要加-->
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>

<style scoped>

</style>