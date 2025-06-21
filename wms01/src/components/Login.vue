<script>
export default {
  name: "Login",
  loading: true,
  data() {
    return {
      form: {
        性别: '男',
        用户名称: '',
        密码: '',
        年龄: '',
        电话: '',
        uid: '',
      },
      loginform: {
        name: '',
        password: '',
      },
      //默认注册弹窗不显示
      centerDialogVisible: false,
      rules: {
        用户名称: [
          {required: true, message: '请输入用户名称', trigger: 'blur'},
          {validator: this.用户名称验证, trigger: 'blur'}
        ],
        电话: [
          {required: true, message: '请输入电话', trigger: 'blur'},
          {min: 11, max: 11, message: '长度在 11 个字符', trigger: 'blur'},
        ],
        密码: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 2, max: 16, message: '长度在 2 到 16 个字符', trigger: 'blur'}
        ],
        年龄: [
          {required: true, message: '请输入年龄', trigger: 'blur'},
          {min: 1, max: 3, message: '长度在 1 到 3 个字符', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,3}$/, message: '不能为负数', trigger: 'blur'}

        ]
      }
    }
  },
  methods: {
    用户名称验证(rule, value, callback) {
      const chineseRegex = /^[\u4e00-\u9fa5]{2,4}$/; // 中文姓名正则表达式，限制长度为2到4个汉字
      if (chineseRegex.test(value)) {
        callback(); // 验证通过，调用 callback() 传递空参数
      } else {
        callback(new Error('请输入正确的中文姓名')); // 验证不通过，调用 callback() 传递错误信息
      }
    },
    login() {
      this.$axios.post(this.$httpUrl + '/用户数据/login', {
        uid: this.loginform.name,
        密码: this.loginform.password
      }).then(res => res.data).then(res => {
        if (res.状态码 === 0) {
          console.log("rr")
          //将登录信息存储到sessionStorage中用于共享
          sessionStorage.setItem("前台用户登录信息", JSON.stringify(res.数据.user));
          console.log("ss")
          this.$router.replace('/');
          this.登录成功提示()
        } else {
          this.登录失败提示()
        }
      })
    },
    //确认关闭的提示框
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    登录成功提示() {
      this.timer = setTimeout(() => {
        // 延迟执行的代码
        this.$message({
          message: '欢迎您登录 Element 仓库平台',
          type: 'success',
        });
      }, 800);
    },
    注册成功提示() {
      this.$notify.success({
        title: '欢迎',
        message: '注册成功',
        showClose: false
      });
    },
    登录失败提示() {
      this.$notify({
        title: '失败',
        dangerouslyUseHTMLString: true,
        message: '<strong>登录<i>失败</i> 请检查</strong>'
      });
    },
    注册失败提示() {
      this.$notify({
        title: '失败',
        dangerouslyUseHTMLString: true,
        message: '<strong>注册<i>失败</i> 请检查</strong>'
      });
    },
    save() {
      this.$axios.post(this.$httpUrl + '/用户数据/插入', this.form).then(res => res.data).then(res => {
        if (res.状态码 === 0) {
          this.注册成功提示()
          this.centerDialogVisible = false
        } else {
          this.注册失败提示()
        }
      })
    },
    Uidtishi() {
      // console.log("fff")
      this.$notify({
        title: '警告',
        message: 'Uid由服务器分配不可更改',
        type: 'warning'
      });
    },
    zhuce() {
      //先显示弹窗然后清空弹窗最后再分配uid
      this.centerDialogVisible = true
      //点击注册按钮后清空文本框并显示注册弹窗
      this.$nextTick(() => {
        this.$refs.注册form.resetFields();
        // console.log("ff")
      })

      this.$nextTick(() => {
        this.$axios.get(this.$httpUrl + '/用户数据/申请uid').then(res => res.data).then(res => {
          this.form.uid = res
        })
      })
    }
  },
  mounted() {
    setTimeout(() => {
      this.loading = false
    }, 200)
  }
}
</script>

<template>
  <div v-loading="loading">
    <section>
      <!-- 背景颜色 -->
      <div class="color"></div>
      <div class="color"></div>
      <div class="color"></div>
      <div class="box">
        <!-- 登录框 -->
        <div class="container">
          <div class="form">
            <h2>Element 仓库<h3>Login</h3></h2>
            <form action="#">
              <div class="inputBox">
                <input type="text" placeholder="Uid" v-model="loginform.name">
              </div>
              <div class="inputBox">
                <input type="password" placeholder="密码" v-model="loginform.password">
              </div>
              <div class="inputBox">
                <!--                //阻止默认按钮的的提交动作，并调用自定义的方法-->
                <input type="submit" value="登录" id="login" @click.prevent="login">
              </div>

              <!--              <p class="forget">忘记密码?<a href="#">-->
              <!--                点击这里-->
              <!--              </a></p>-->

              <p class="forget">没有账户?<a href="#" @click="zhuce">
                注册
              </a></p>
            </form>
          </div>
        </div>
      </div>
    </section>
    <!--    新增数据弹窗-->
    <el-dialog
      title="Element 仓库注册"
      :visible.sync="centerDialogVisible"
      width="28%"
      center
      :before-close="handleClose">
      <el-form ref="注册form" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="您的Uid:" prop="Uid" @click.native="Uidtishi">
          <el-col :span="20">
            <el-tooltip effect="dark" content="Uid由服务器分配无法更改" placement="top">
              <button style="border: none;width:50px;background-color: transparent;">
                {{ form.uid }}
              </button>
            </el-tooltip>
          </el-col>
        </el-form-item>
        <el-form-item label="用户名称" prop="用户名称">
          <el-col :span="20">
            <el-input v-model="form.用户名称" maxlength="5" show-word-limit></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="密码" prop="密码">
          <el-col :span="20">
            <el-input v-model="form.密码" show-password maxlength="16"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="年龄" prop="年龄">
          <el-col :span="20">
            <el-input v-model="form.年龄"></el-input>
          </el-col>
        </el-form-item>
        <!--        性别单选按钮-->
        <el-form-item label="性别" prop="性别">
          <el-radio-group v-model="form.性别">
            <el-radio label="男"></el-radio>
            <el-radio label="女" style="margin-left: 30px"></el-radio>
            <el-radio label="其他" style="margin-left: 30px"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="电话">
          <el-col :span="20">
            <el-input v-model="form.电话"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false" style="padding:9px 15px">取 消</el-button>
        <el-button type="primary" @click="save" style="padding:9px 15px">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<style scoped>

/* 清除浏览器默认边距，
使边框和内边距的值包含在元素的width和height内 */

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* 使用flex布局，让内容垂直和水平居中 */

section {
  /* 相对定位 */
  position: relative;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-image: url(../仓库图片/banner.jpg);
  background-size: cover;
  background-color: #b3d8ff;
}

/* 背景颜色 */

section .color {
  /* 绝对定位 */
  position: absolute;
  /* 使用filter(滤镜) 属性，给图像设置高斯模糊*/
  filter: blur(200px);
}


.box {
  position: relative;
}


/* 登录框样式 */

.container {
  position: relative;
  width: 400px;
  min-height: 400px;
  background: rgba(255, 255, 255, 0.1);
  display: flex;
  justify-content: center;
  align-items: center;
  backdrop-filter: blur(5px);
  box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.5);
  border-right: 1px solid rgba(255, 255, 255, 0.2);
  border-bottom: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: 5%;
}

.form {
  position: relative;
  width: 100%;
  height: 100%;
  padding: 50px;
}

/* 登录标题样式 */

.form h2 {
  position: relative;
  color: #fff;
  font-size: 24px;
  font-weight: 600;
  letter-spacing: 5px;
  margin-bottom: 30px;
  cursor: pointer;

}

/* 登录标题的下划线样式 */

.form h2::before {
  content: "";
  position: absolute;
  left: 0;
  bottom: -10px;
  width: 0px;
  height: 3px;
  background: #fff;
  transition: 0.5s;
}

.form h2:hover:before {
  width: 53px;
}

.form .inputBox {
  width: 100%;
  margin-top: 20px;
}

.form .inputBox #login:active {
  transform: scale(0.95); /* 设置按下时的缩放效果 */
}

/* 输入框样式 */

.form .inputBox input {
  width: 100%;
  padding: 10px 20px;
  background: rgba(255, 255, 255, 0.2);
  outline: none;
  border-radius: 30px;
  border: 1px solid rgba(255, 255, 255, 0.5);
  border-right: 1px solid rgba(255, 255, 255, 0.2);
  border-bottom: 1px solid rgba(255, 255, 255, 0.2);
  font-size: 16px;
  letter-spacing: 1px;
  color: black;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
}

.form .inputBox input::placeholder {
  color: #fff;
}

/* 登录按钮样式 */

.form .inputBox input[type="submit"] {
  background: #fff;
  color: #666;
  max-width: 100px;
  margin-bottom: 20px;
  font-weight: 600;
  cursor: pointer;
}

.forget {
  margin-top: 6px;
  color: #fff;
  letter-spacing: 1px;
}

.forget a {
  color: #fff;
  font-weight: 600;
  text-decoration: none;
}
</style>
