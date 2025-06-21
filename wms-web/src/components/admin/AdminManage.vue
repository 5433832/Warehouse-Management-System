<script>
export default {
  name: "AdminManage",
  data() {
    return {
      tableData: [],
      每页数据: 14,
      当前页: 1,
      记录总和: 0,
      搜索: "",
      性别: '',
      性别数组: [{
        value: '男',
        label: '男'
      }, {
        value: '女',
        label: '女'
      }, {
        value: '其他',
        label: '其他'
      },],
      // 默认弹窗不显示
      centerDialogVisible: false,
      form: {
        账户是否有效: false,
        性别: '男',
        级别: '普通账户',
        用户名称: '',
        密码: '',
        年龄: '',
        电话: '',
        uid: '',
      },
      rules: {
        用户名称: [
          {required: true, message: '请输入用户名称', trigger: 'blur'},
          {validator: this.用户名称验证, trigger: 'blur'}
        ],
        电话: [
          {required: true, message: '请输入电话', trigger: 'blur'},
          {min: 11, max: 11, message: '长度在 11 个字符', trigger: 'blur'},
          {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: '请输入正确的格式', trigger: 'blur'}

        ],
        密码: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur'}
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
    resetForm() {
      this.$refs.form.resetFields();
    },
    add() {
      this.centerDialogVisible = true
      //每次点击新增数据按钮时将表单复位
      // this.$nextTick()为延迟调用
      this.$nextTick(() => {
        this.resetForm();
      })

    },
    // form: {
    //   用户名称: '',
    //   密码: '',
    //   年龄: '',
    //   性别: '',
    //   电话: '',
    //   级别: '',
    //   账户是否有效: false
    // },
    save() {
      //进行表单验证
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.uid) {
            if (this.form.账户是否有效 === false) {
              this.form.账户是否有效 = '否'
            } else {
              this.form.账户是否有效 = '是'
            }
            this.doMod()
          } else {
            if (this.form.账户是否有效 === false) {
              this.form.账户是否有效 = '否'
            } else {
              this.form.账户是否有效 = '是'
            }
            this.doSave()
          }
        } else {
          return false;
        }
      });
    },
    doMod() {
      // 修改数据
      this.$axios.post(this.$httpUrl + '/用户数据/update', this.form).then(res => res.data).then(res => {
        if (res.状态码 === 0) {
          this.loadPost()
          this.centerDialogVisible = false
          const h = this.$createElement;
          this.$message({
            message: h('p', null, [
              h('span', null, '数据编辑 '),
              h('i', {style: 'color: teal'}, '成功')
            ])
          });
        } else {
          const h = this.$createElement;
          this.$message({
            message: h('p', null, [
              h('span', null, '数据编辑 '),
              h('i', {style: 'color: teal'}, '失败')
            ])
          });

        }
      })
    },
    //提交数据
    doSave() {
      this.$axios.post(this.$httpUrl + '/用户数据/插入', this.form).then(res => res.data).then(res => {
        if (res.状态码 === 0) {
          this.loadPost()
          this.centerDialogVisible = false
          const h = this.$createElement;
          this.$message({
            message: h('p', null, [
              h('span', null, '数据提交 '),
              h('i', {style: 'color: teal'}, '成功')
            ])
          });
        } else {
          const h = this.$createElement;
          this.$message({
            message: h('p', null, [
              h('span', null, '数据提交 '),
              h('i', {style: 'color: teal'}, '失败')
            ])
          });

        }
      });
    },
    // 删除方法
    del(uid) {
      console.log(uid)
      this.$axios.get(this.$httpUrl + '/用户数据/删除?uid=' + uid).then(res => res.data).then(res => {
        if (res.状态码 === 0) {
          const h = this.$createElement;
          this.$message({
            message: h('p', null, [
              h('span', null, '数据删除 '),
              h('i', {style: 'color: teal'}, '成功')
            ])
          });
          this.loadPost()
        } else {
          const h = this.$createElement;
          this.$message({
            message: h('p', null, [
              h('span', null, '数据删除 '),
              h('i', {style: 'color: teal'}, '失败')
            ])
          });

        }
      });
    },
    //编辑方法
    mod(row) {
      console.log(row)
      this.centerDialogVisible = true

      this.$nextTick(() => {
        //赋值到表单
        this.form.uid = row.uid
        this.form.用户名称 = row.用户名称
        this.form.性别 = row.性别
        this.form.密码 = ''
        this.form.电话 = row.电话
        this.form.年龄 = row.年龄
        this.form.级别 = row.级别
        if (row.账户是否有效 === '否') {
          this.form.账户是否有效 = false
        } else {
          this.form.账户是否有效 = true
        }
      })
    },
    loadGet() {
      //通过调用全局属性axios中的get方法向url发送获取请求，利用then方法处理返回的数据，返回的数据会赋值到第一个then方法的res对象，然后通过res.data提取res里面data的数据，第二个是将第一个then的方法进行处理打印在控制台
      this.$axios.get(this.$httpUrl + '/用户数据/list').then(res => res.data).then(res => {
        console.log(res)

      })
    },
    //查询或模糊查询数据
    loadPost() {
      //调用axios属性的post方法向url提交post请求体并请求返回数据
      this.$axios.post(this.$httpUrl + '/用户数据/模糊查询并分页进行数据封装', {
        页数: this.当前页,
        每页数据: this.每页数据,
        分页数据封装: {
          搜索: this.搜索,
          级别: '管理员'
        },
      }).then(res => res.data).then(res => {
        console.log(res)
        if (res.状态码 == 0) {
          this.tableData = res.数据
          this.记录总和 = res.数据总和
        } else {
          alert("数据请求获取失败")
        }
      })
    },
    re() {
      this.用户名称 = '';
      this.性别 = '';
      this.loadPost();
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.当前页 = 1;
      this.每页数据 = val;
      this.loadPost();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.当前页 = val
      this.loadPost()
    },
    //级别筛选显示方法,value为筛选值，row为当前数据对象即当前行数据
    filterTag(value, row) {
      return row.级别 === value;
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
  },
  //生命周期函数beforeMount在组件被挂在之前会被调用执行
  beforeMount() {
    // this.loadGet();
    this.loadPost();
  },
}
</script>

<template>
  <div class="MainBody">
    <div style="margin-bottom: 5px">
      <!--      v-model用于实现表单元素与数据之间的双向绑定-->
      <el-input v-model="搜索" placeholder="请输入uid/名称/年龄/电话" suffix-icon="el-icon-search" style="width: 200px"
                @keyup.enter.native="loadPost">
      </el-input>
      <el-select v-model="性别" filterable placeholder="性别" style="margin-left: 0.3%;width: 85px">
        <el-option
            v-for="item in 性别数组"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        >
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" @click="re">重置</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="add">新增数据</el-button>
    </div>
    <el-table :data="tableData"
              :header-cell-style="{background:'#D8D8D8'}"
              style="font-size: 100%;background-color: rgb(255,255,255,0);"
    >
      <el-table-column prop="uid" label="Uid" width="60%">
      </el-table-column>
      <el-table-column prop="用户名称" label="用户名称" width="120%">
      </el-table-column>
      <el-table-column prop="密码" label="密码" align="center">
      </el-table-column>
      <el-table-column prop="年龄" label="年龄">
      </el-table-column>
      <el-table-column prop="性别" label="性别">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.性别 === '男' ? 'primary' : scope.row.性别 === '女' ? 'success' : 'warning'"
              disable-transitions>{{ scope.row.性别 }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="电话" label="电话">
      </el-table-column>
      <el-table-column prop="级别" label="级别" width="80%"
                       :filters="[{ text: 'Root', value: 'Root' },
                      { text: '管理员', value: '管理员' }]"
                       :filter-method="filterTag"
                       filter-placement="bottom-end">>
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.级别 === '管理员' ? 'primary' : scope.row.级别 === 'Root' ? 'danger' : 'success'"
              disable-transitions>{{ scope.row.级别 }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="账户是否有效" label="账户是否有效" width="120%">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.账户是否有效 === '是' ? 'primary' : 'warning'"
              disable-transitions>
            {{ scope.row.账户是否有效 }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="执行操作" label="执行操作" width="140%" align="center">
        <template slot-scope="scope">
          <el-button type="primary" round size="small" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除？" @confirm="del(scope.row.uid)" style="margin-left: 2%">
            <el-button slot="reference" type="danger" round size="small">删除</el-button>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="当前页"
        :page-sizes="[14, 28, 42, 60]"
        :page-size="每页数据"
        layout="total, sizes, prev, pager, next, jumper"
        :total="记录总和">
    </el-pagination>


    <!--    新增数据弹窗-->
    <el-dialog
        title="新增数据"
        :visible.sync="centerDialogVisible"
        width="28%"
        center
        :before-close="handleClose">
      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
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
            <el-radio label="女"></el-radio>
            <el-radio label="其他"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="电话">
          <el-col :span="20">
            <el-input v-model="form.电话"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="级别" prop="级别">
          <el-col :span="20">
            <el-select v-model="form.级别" placeholder="请选择级别">
              <el-option label="Root" value="Root"></el-option>
              <el-option label="管理员" value="管理员"></el-option>
              <el-option label="普通账户" value="普通账户"></el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="是否有效" prop="账户是否有效">
          <el-switch v-model="form.账户是否有效"></el-switch>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<style scoped>
.MainBody {
  height: 100%;
  width: 100%;
}
</style>