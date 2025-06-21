<template style="height: 100vh;">
  <div class="loginBody">
    <div class="loginDiv">
      <div class="login-content">
        <div class="form">
          <h1 class="login-title">用户登录</h1>
          <el-form :model="loginForm" label-width="100px"
                   :rules="rules" ref="loginForm">
            <el-form-item label="uid" prop="uid">
              <el-input style="width: 200px" type="text" v-model="loginForm.uid"
                        autocomplete="off" size="small"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="密码">
              <el-input style="width: 200px" type="password" v-model="loginForm.密码"
                        show-password autocomplete="off" size="small" @keyup.enter.native="confirm"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="confirm" :disabled="confirm_disabled"
                         class="btn">确 定
              </el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "Login",
  data() {
    return {
      confirm_disabled: false,
      loginForm: {
        uid: '',
        密码: ''
      },
      rules: {
        uid: [
          {required: true, message: '请输入uid', trigger: 'blur'},
          {pattern: /^\d+$/, message: '请输入正确Uid', trigger: 'blur'}
        ],
        密码: [
          {required: true, message: '请输密码', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    登录成功提示(用户名称, 级别) {
      this.$message({
        message: '登录成功！欢迎您  ' + 用户名称 + "  级别：" + 级别,
        type: 'success'
      });
    },
    登录失败提示() {
      this.$message.error('登录失败，请检查');
    },
    confirm() {
      this.confirm_disabled = true;
      // console.log(this.loginForm)
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          //valid成功为true，失败为false
          //去后台验证用户名密码
          this.$axios.post(this.$httpUrl + '/用户数据/Managelogin', this.loginForm).then(res => res.data).then(res => {
            if (res.状态码 === 0) {
              this.登录成功提示(res.数据.user.用户名称, res.数据.user.级别)
              //将登录信息存储到sessionStorage中用于共享
              sessionStorage.setItem("登录信息", JSON.stringify(res.数据.user))

              this.$store.commit("setMenu", res.数据.menu)
              //跳转到主页
              this.$router.replace('/Index');
            } else {
              this.confirm_disabled = false;
              this.登录失败提示()
              return false;
            }
          });
        } else {
          this.confirm_disabled = false;
          // console.log('校验失败');
          return false;
        }
      });

    }
  }
}
</script>

<style scoped>


.loginBody {
  margin: 0;
  box-sizing: border-box;
  background-image: url(./img/bg.png);
  background-size: 180%;
  background-repeat: no-repeat;
  background-position: center center; /* 将背景图片居中 */
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #B3C0D1;
}

.loginDiv:before {
  content: "";
  position: absolute;
  top: 33%;
  left: 35%;
  width: 25%;
  height: 28%;
  border-radius: 5%;
  opacity: 0.85;
  background-color: rgba(255, 255, 255, 0.3); /* 设置半透明的背景颜色 */
  filter: blur(1px);
  border: 1px solid #B3C0D1;
}

.login-title {
  margin: 20px 0;
  text-align: center;
}

.login-content {
  width: 400px;
  height: 250px;
  position: absolute;
  margin-top: 19%;
  margin-left: 37%;
}

</style>