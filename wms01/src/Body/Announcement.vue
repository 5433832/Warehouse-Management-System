<script>
export default {
  name: "Announcement",
  data() {
    return {
      size: 'medium',
      Announcement: [],
      loading: true,

    }
  },
  methods: {},
  mounted() {
    //通过后端接口获取数据
    this.$axios.get(this.$httpUrl + '/storage/list').then(res => res.data).then(res => {
      this.Announcement = res
    });
    setTimeout(() => {
      this.loading = false;
    }, 1000);
  }
}
</script>

<template>
  <div class="big-main" v-loading="loading">
    <div class="big-main-conten" v-for="(item, index) in Announcement" :key="index">
      <div class="big-main-conten-仓库标题" style="font-size: 3vh;text-align: center;margin-bottom: 1%;">
        <p>{{ item.仓库名称 }}</p>
      </div>
      <div class="big-main-conten-仓库图片">
        <el-carousel :interval="3000">
          <el-carousel-item>
            <img :src="require('../../static/'+item.img0)"
                 style="width: 100%;height: 100%;border-radius: 2%;">
          </el-carousel-item>
          <el-carousel-item>
            <img :src="require('../../static/'+item.img1)" alt="carousel-image"
                 style="width: 100%;height: 100%;border-radius: 2%;">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="big-main-conten-仓库信息">
        <el-descriptions class="margin-top" title="仓库信息" :column="3" :size="size" border>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              仓库编号
            </template>
            {{ item.仓库编号 }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location"></i>
              仓库所在地区
            </template>
            {{ item.仓库地区 }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user-solid"></i>
              仓库管理者Uid
            </template>
            {{ item.仓库管理者uid }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-box"></i>
              仓库类别
            </template>
            {{ item.仓库类别 }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-platform-eleme"></i>
              仓库优点
            </template>
            {{ item.仓库优点 }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-eleme"></i>
              仓库缺点
            </template>
            {{ item.仓库缺点 }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              租赁开始时间
            </template>
            {{ item.租赁开始时间 }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-mobile-phone"></i>
              租赁结束时间
            </template>
            {{ item.租赁结束时间 }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-info"></i>
              仓库状态
            </template>
            <el-tag
              :type="item.仓库状态 === '已租赁' ? 'primary' : item.仓库状态 === '未租赁' ? 'success' : 'warning'"
              disable-transitions>{{ item.仓库状态 }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              仓库备注
            </template>
            {{ item.备注 }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-message-solid"></i>
              仓库公告
            </template>
            {{ item.仓库公告 }}
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </div>
  </div>
</template>

<style scoped>
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  border: none;
}

.big-main {

//background-color: beige;
}

.big-main .big-main-conten {
  width: 100%;
  height: 100%;
//background-color: blue;
}

.big-main .big-main-conten .big-main-conten-仓库标题 {
  margin: 0 auto;
  width: 40%;
  height: 10%;
//background-color: yellow;
}

.big-main .big-main-conten .big-main-conten-仓库图片 {
  margin: 0 auto;
  width: 60%;
  height: 100%;
//background-color: pink;

}

.big-main .big-main-conten .big-main-conten-仓库信息 {
  margin-top: 2%;
  width: 100%;
  height: 30%;
  margin-bottom: 10%;
//background-color: green;
}

/*ui框架样式*/
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;

}

.el-carousel__item:nth-child(2n) {
  border-radius: 2%;
  background-color: #99a9bf;

}

.el-carousel__item:nth-child(2n+1) {
  border-radius: 2%;
  background-color: #d3dce6;

}
</style>
