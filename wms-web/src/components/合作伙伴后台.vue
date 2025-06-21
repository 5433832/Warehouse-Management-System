<script>

export default {
  name: "PartnerManage",
  data() {
    return {
      fileList: [{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }],
      tableData: [],
      每页数据: 9,
      当前页: 1,
      记录总和: 0,
      搜索: "",
      性别: '',
      src: "chaigpt.png",
      // 默认弹窗不显示
      centerDialogVisible: false,
      form: {
        name: '',
        logosrc: '',
        httpsrc: '',
      },
      rules: {
        name: [
          {required: true, message: '请输入企业名称', trigger: 'blur'},
        ],
        logosrc: [
          {required: true, message: '请输入logo地址', trigger: 'blur'},
        ]
      }
    }
  },
  methods: {
    //确认关闭的提示框
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    //查询或模糊查询数据
    loadPost() {
      //调用axios属性的post方法向url提交post请求体并请求返回数据
      this.$axios.post(this.$httpUrl + '/合作伙伴/模糊查询并分页进行数据封装', {
        页数: this.当前页,
        每页数据: this.每页数据,
        分页数据封装: {
          搜索: this.搜索
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
    //编辑方法
    mod(row) {
      console.log(row)
      this.centerDialogVisible = true
      this.$nextTick(() => {
        //赋值到表单
        this.form.id = row.id
        this.form.name = row.name
        this.form.logosrc = row.logosrc
        this.form.httpsrc = row.httpsrc
      })
    },
    doMod() {
      // 修改数据
      this.$axios.post(this.$httpUrl + '/合作伙伴/update', this.form).then(res => res.data).then(res => {
        if (res.状态码 === 0) {
          this.查询()
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
    del(id) {
      console.log(id)
      this.$axios.get(this.$httpUrl + '/合作伙伴/删除?id=' + id).then(res => res.data).then(res => {
        if (res.状态码 === 0) {
          const h = this.$createElement;
          this.$message({
            message: h('p', null, [
              h('span', null, '数据删除 '),
              h('i', {style: 'color: teal'}, '成功')
            ])
          });
          this.查询()
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
    add() {
      this.centerDialogVisible = true
      //每次点击新增数据按钮时将表单复位
      // this.$nextTick()为延迟调用
      this.$nextTick(() => {
        this.$refs.form.resetFields();
      })
    },
    //查询
    查询() {
      this.$axios.post(this.$httpUrl + '/合作伙伴/模糊查询并分页进行数据封装', {
        页数: this.当前页,
        每页数据: this.每页数据,
        分页数据封装: {
          搜索: this.搜索
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
      this.搜索 = '';
      this.loadPost();
    },
    save() {
      //进行表单验证
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id) {
            this.doMod()
          } else {
            this.$axios.post(this.$httpUrl + '/合作伙伴/插入', this.form).then(res => res.data).then(res => {
              if (res.状态码 === 0) {
                this.查询()
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
            })
          }
        } else {
          return false;
        }
      });
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
  },

  beforeMount() {
    this.查询()
  },
}
</script>

<template>
  <div>
    <div style="margin-bottom: 0.5%">
      <el-input v-model="搜索" placeholder="请输入企业名称" suffix-icon="el-icon-search" style="width: 200px"
                @keyup.enter.native="loadPost">
      </el-input>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" @click="re">重置</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="add">新增数据</el-button>

    </div>
    <el-table
      :data="tableData"
      stripe
      :header-cell-style="{background:'#D8D8D8'}"
      style="width: 100%;font-size: 100%;background-color: rgb(255,255,255,0);border-radius: 10px">

      <el-table-column
        prop="id"
        label="id"
        width="50%">
      </el-table-column>
      <el-table-column
        prop="name"
        label="企业名称"
        width="150%">
      </el-table-column>
      <el-table-column
        prop="logosrc"
        label="Logo地址">
      </el-table-column>
      <el-table-column
        label="Logo">
        <!--        logo展示为了确保每行只展示一个并且对应使用了插槽方法获取当前行的logosrc对象-->
        <template slot-scope="scope">
          <!--          <img :src="require('../../../wms01/src/品牌logo/' + scope.row.logosrc)" height="20%" width="20%">-->
          <el-image
            style="width: 20%; height: 20%"
            :src="require('../../../wms01/src/品牌logo/' + scope.row.logosrc)"
            fit="contain"></el-image>
        </template>
      </el-table-column>
      <el-table-column
        prop="httpsrc"
        label="网络地址">
      </el-table-column>
      <el-table-column prop="执行操作" label="执行操作" width="150%" align="center">
        <template slot-scope="scope">
          <el-button type="primary" round size="small" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除？" @confirm="del(scope.row.id)" style="margin-left: 2%">
            <el-button slot="reference" type="danger" round size="small">删除</el-button>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="当前页"
      :page-sizes="[9]"
      :page-size="每页数据"
      layout="total, sizes, prev, pager, next, jumper"
      :total="记录总和">
    </el-pagination>

    <!--    新增数据-->
    <el-dialog
      title="新增数据"
      :visible.sync="centerDialogVisible"
      width="28%"
      center
      :before-close="handleClose">
      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="企业名称" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="LogoSrc" prop="logosrc">
          <el-col :span="20">
            <el-input v-model="form.logosrc"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="网络地址" prop="httpsrc">
          <el-col :span="20">
            <el-input v-model="form.httpsrc"></el-input>
          </el-col>
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

</style>
