<script>

export default {
  name: "Footer",
  computed: {},
  data() {
    return {
      input: '',

      labelPosition: 'top',

      formLabelAlign: {
        name: '',
        phone: '',
        LeaseTime: '',
        AskQuestions: '',
      },

      formRules: {
        name: [
          {required: true, message: '请输入称呼', trigger: 'blur'},
        ],
        phone: [
          {required: true, message: '请输入电话号码', trigger: 'blur'},
          {min: 11, max: 11, message: '长度在 11 个字符', trigger: "blur"},
          {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: '请输入正确的格式', trigger: "blur"},

        ]
      },

      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      options: [{
        value: '裕华区',
        label: '裕华区'
      }, {
        value: '正定县',
        label: '正定县'
      }, {
        value: '藁城区',
        label: '藁城区'
      }],
    };
  },
  methods: {
    save() {
      this.$refs.form.validate(valid => {
        if (valid) {
          this.$axios.post(this.$httpUrl + '/潜在用户信息/插入', {
            姓名: this.formLabelAlign.name,
            电话: this.formLabelAlign.phong,
            // 租赁开始时间: this.formLabelAlign.LeaseTime.at(0),
            租赁开始时间: this.formLabelAlign.LeaseTime[0],
            // 租赁结束时间: this.formLabelAlign.LeaseTime.at(1),
            租赁结束时间: this.formLabelAlign.LeaseTime[1],
            问题: this.formLabelAlign.AskQuestions,
          }).then(res => res.data).then(res => {
            if (res.状态码 === 0) {
              this.$nextTick(() => {
                this.$refs.form.resetFields();
                // console.log("ff")
              })
              this.提交信息成功();
            } else {
              this.提交信息失败()
            }
          })
        } else {
          this.提交信息失败()
        }
      });
    },
    提交信息成功() {
      this.$message({
        showClose: true,
        message: '提交中...',
        type: 'info'
      });
      this.timer = setTimeout(() => {
        // 延迟执行的代码
        this.$message({
          message: '提交成功！我们稍后会与您的联系',
          type: 'success',
        });
      }, 1000);
    },
    提交信息失败() {
      this.$message({
        showClose: true,
        message: '提交失败',
        type: 'error'
      });
    },
  }

}
</script>

<template>
  <div class="footer">
    <div class="minfooter">
      <div class="留下信息">
        <p>留下您的信息我们会马上联系您</p>
      </div>
      <div class="minminfooter">
        <div style="margin: 20px;"></div>
        <el-form ref="form" :rules="formRules" :label-position="labelPosition" label-width="80px"
                 :model="formLabelAlign">
          <el-form-item label="称呼" class="留下d1信息" prop="name">
            <el-input v-model="formLabelAlign.name" placeholder="请输入您的称呼"></el-input>
          </el-form-item>
          <el-form-item label="联系方式" class="留下d1信息" prop="phone">
            <el-input v-model="formLabelAlign.phone" placeholder="请输入您的联系方式"></el-input>
          </el-form-item>
          <el-form-item label="租赁时间" class="留下d1信息">
            <div class="block">
              <el-date-picker
                v-model="formLabelAlign.LeaseTime"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"
                :picker-options="pickerOptions">
              </el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="咨询问题" class="留下d1d信息">
            <el-input
              type="textarea"
              :rows="4"
              placeholder="请输入咨询的问题"
              v-model="formLabelAlign.AskQuestions">
            </el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary"
                   style="position: absolute;bottom: -50%;padding: 20px 40px;font-size: 22px"
                   @click.native="save">
          提交
        </el-button>
      </div>
    </div>

    <img src="./pic_form.png" alt="">

  </div>
</template>

<style scoped>
.footer {
  position: relative;
  width: 100%;

}

.footer img {
  width: 50%;
  height: 50%;
  right: 1%;
  bottom: 10%;

  margin-left: 50%;
}

.minfooter {
  position: absolute;
  bottom: 60%;
}

.minfooter .留下信息 {
  padding-top: 55px;
  width: 1090px;
  text-align: left;
  font-size: 20px;
  font-weight: 500;
  color: #4f4f4f;
}

.minfooter .留下d1信息 {
  display: inline-block;
  margin-bottom: 25px;
  margin-right: 60px;
  box-sizing: border-box;
  opacity: 0.8;
}


.minfooter .留下d1d信息 {
  width: 540px;
  height: 84px;


  border-radius: 2px;
  color: rgba(0, 0, 0, .25);
  padding: 8px 16px 12px;

}

</style>
