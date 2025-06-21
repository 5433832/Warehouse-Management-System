<script>
export default {
  name: "Partner",
  data() {
    return {
      Partner: [],
      loading: true,
    }
  },
  methods: {},
  mounted() {
    this.$axios.get(this.$httpUrl + '/合作伙伴/list').then(res => res.data).then(res => {
      this.Partner = res
    });
    setTimeout(() => {
      this.loading = false;
    }, 1000);
  },
  computed: {},
}
</script>

<template>
  <div v-loading="loading" style="margin-left: 4%">
    <div class="合作伙伴">
      合作伙伴
    </div>
    <div class="伙伴介绍">
      我们秉承专注、开放、共赢的理念。以资源共享、技术支持、可持续发展为目标，携手为世界交付千行百业的物联网技术服务。为您提供多样化的商业生态合作模式，成就企业无限可能！
    </div>
    <el-row :gutter="20">
      <el-col :span="6" v-for="(j,index) in Partner" :key="index">
        <el-tooltip :content="j.name" placement="top">
          <div class="grid-content bg-purple" style="margin-bottom: 5%">
            <a :href="j.httpsrc" target="_blank"><img :src="require('../品牌logo/'+j.logosrc)" :alt="j.name"></a>
          </div>
        </el-tooltip>
        <!--        <el-divider></el-divider>-->
      </el-col>
    </el-row>

  </div>
</template>

<style scoped>
.el-row {
  margin-bottom: 80px;

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

.grid-content {
  border-radius: 4px;
  min-height: 250px;
  width: 250px;
  height: 250px;
}


.row-bg {
  padding: 10px 10px;
  background-color: #f9fafc;
}

img {

  width: 250px;
  height: 250px;
  border-radius: 10%;
  display: inline-block;
  vertical-align: middle;
  transition: transform 0.2s ease-in-out;
  box-shadow: 10px 10px 5px 3px #7a7878;
  transform: scale(1);
}

img:hover {
  transform: scale(1.1);
}

.伙伴介绍 {
  display: inline-block;
  width: 600px;
  line-height: 2em;
  font-size: 14px;
  color: #4f4f4f;
  margin: 0 50px 40px 0;
}

.合作伙伴 {
  font-size: 20px;
  padding: 56px 0 10px;
  font-weight: 500;
  color: #4f4f4f;
}
</style>
