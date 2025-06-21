<template>
  <div style="text-align: center;background-color: #f1f1f3;height: 100%;padding: 0px;margin: 0px;">
    <h1 style="font-size: 50px;">{{ '欢迎你！' + user.用户名称 }}</h1>
    <el-descriptions title="个人中心" :column="2" size="40" border>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-s-custom"></i>
          uid
        </template>
        {{ user.uid }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          电话
        </template>
        {{ user.电话 }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location-outline"></i>
          性别
        </template>
        <el-tag
            :type="user.性别 === '男' ? 'primary' : user.性别 === '女' ? 'success' : 'warning'"
            disable-transitions>{{ user.性别 }}
        </el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          级别
        </template>
        <el-tag
            :type="user.级别 === '管理员' ? 'primary' : user.级别 === 'Root' ? 'danger' : 'success'"
            disable-transitions>{{ user.级别 }}
        </el-tag>

      </el-descriptions-item>
    </el-descriptions>

    <DateUtils></DateUtils>
  </div>
</template>

<script>
import DateUtils from "./DateUtils";

export default {
  name: "Home",
  components: {DateUtils},
  data() {

    return {
      user: {}
    }
  },
  computed: {},
  methods: {
    init() {
      this.user = JSON.parse(sessionStorage.getItem('登录信息'))
    }
  },
  created() {
    this.init()
  }
}
</script>

<style scoped>
.el-descriptions {
  width: 90%;

  margin: 0 auto;
  text-align: center;
}
</style>