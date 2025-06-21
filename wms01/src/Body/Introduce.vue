<script>
export default {
  name: "Introduce",
  data() {
    return {
      //定义解决方案数组用来获取后端解决方案数据
      Solutions: [],
      //服务展示数据
      Service_presentation_data: [],
      loading: true,
    }
  },
  mounted() {
    // 获取解决方案后端数据
    this.$axios.get(this.$httpUrl + '/解决方案/查询所有').then(res => res.data).then(res => {
      this.Solutions = res
    })

    this.$axios.get(this.$httpUrl + '/服务展示数据/list').then(res => res.data).then(res => {
      this.Service_presentation_data = res
    })

    setTimeout(() => {
      this.loading = false
    }, 1000)
  },

}
</script>

<template>
  <div v-loading="loading">
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple-dark">
          <div class="jieshao">
            <div id="jieshao1">
              以数字孪生和AI技术改变物流仓储
            </div>
            <div id="jieshao2">
              3D数字孪生 · AI智能盯货 · 仓储智能规划
            </div>
          </div>
          <img src="../仓库图片/banner.jpg" alt="banner">
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="">
        <div class="仓库服务">
          仓库全面使用5G时代 AI 服务
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <img src="../仓库图片/architecture.jpg" alt="">
        </div>
      </el-col>
      <el-col :span="12">
        <div class="redirct-item">
          <el-row type="flex" class="row-bg" v-for="(k,index) in Service_presentation_data" :key="index">
            <el-col :span="16">
              <div class="grid-content bg-purple">
                <div class="title">{{ k.title }}</div>
                <div class="desc">
                  {{ k.dis }}
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="仓库服务">
          解决方案
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="解决方案">
      <!--      遍历Solutions数组-->
      <el-col :span="6" v-for="(k,index) in Solutions" :key="index">
        <transition name="el-zoom-in-top" class="tran">
          <div class="grid-content">
            <div class="方案">
              <p class="方案title" v-html="k.title">{{ k.title }}</p>
              <div class="方案dis">
                {{ k.dis }}
              </div>
            </div>
            <img :src="require('../仓库图片/'+k.img)" alt="">
          </div>
        </transition>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
//background: #99a9bf;
}

.bg-purple {
//background: #d3dce6;
}

.bg-purple-light {
//background: #e5e9f2;
}


.jieshao {
  position: absolute;
  top: 160px;
  left: 7%;
}

.jieshao #jieshao1 {
  line-height: 50px;
  font-size: 36px;
  text-shadow: 0 0 12px rgba(255, 255, 255, .9);
}

.jieshao #jieshao2 {
  margin-top: 26px;
  line-height: 28px;
  font-size: 20px;
  text-shadow: 0 0 8px #fff;
}

.row-bg {
  padding: 10px 0;
}

.redirct-item {
  position: relative;
  cursor: pointer;
  font-family: PingFang SC, sans-serif;
  color: #4f4f4f;
  margin-left: 5%;
}

.redirct-item .title {
  line-height: 24px;
  font-size: 22px;
  font-weight: 500;
  margin-bottom: 10px;
  margin-left: 20px;
}

.redirct-item .desc {
  margin-top: 4px;
  max-width: 450px;
  font-size: 16px;
  font-weight: 400;
  margin-left: 20px;
}

.仓库服务 {
  line-height: 50px;
  font-size: 36px;
  text-shadow: 0 0 12px rgba(255, 255, 255, .9);
  text-align: center;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  transform: scale(1);
  transition: transform 0.2s ease-in-out;

}


img {
  width: 100%;
  height: 100%;
  border-radius: 5%;
}


.grid-content:hover {
  transform: scale(1.05);
}

.方案 {
  padding-top: 7%;
  padding-left: 7%;
  padding-right: 7%;
}

.方案 .方案title {
  font-size: 16px;
  font-weight: 500;
  color: #4f4f4f;
  margin-bottom: 10px;
  text-align: center;
  line-height: 1.5em;
}

.方案 .方案dis {
  font-size: 14px;
  color: #575757;
  line-height: 2em;
}
</style>
