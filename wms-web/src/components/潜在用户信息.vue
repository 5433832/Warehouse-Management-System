<script>
export default {
  name: "潜在用户信息",
  data() {
    return {
      // 默认弹窗不显示
      centerDialogVisible: false,
      tableData: [],
      搜索: "",
      每页数据: 14,
      当前页: 1,
      记录总和: 0,
      form: {
        id: '',
        姓名: '',
        电话: '',
        租赁开始时间: '',
        租赁结束时间: '',
        问题: '',
      },

    }
  },
  methods: {
    //查询或模糊查询数据
    loadPost() {
      //调用axios属性的post方法向url提交post请求体并请求返回数据
      this.$axios.post(this.$httpUrl + '/潜在用户信息/模糊查询并分页进行数据封装', {
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
    re() {
      this.搜索 = '';
      this.loadPost();
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
    //编辑方法
    mod(row) {
      console.log(row)
      this.centerDialogVisible = true

      this.$nextTick(() => {
        //赋值到表单
        this.form.id = row.id
        this.form.姓名 = row.姓名
        this.form.电话 = row.电话
        this.form.问题 = row.问题
        this.form.租赁开始时间 = row.租赁开始时间
        this.form.租赁结束时间 = row.租赁结束时间
      })
    },
    // 删除方法
    del(id) {
      console.log(id)
      this.$axios.get(this.$httpUrl + '/潜在用户信息/删除?id=' + id).then(res => res.data).then(res => {
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
    save() {
      //进行表单验证
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id) {
            this.doMod()
          } else {
            this.doSave()
          }
        } else {
          return false;
        }
      });
    },
    //提交数据
    doSave() {
      this.$axios.post(this.$httpUrl + '/潜在用户信息/插入', this.form).then(res => res.data).then(res => {
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
    doMod() {
      // 修改数据
      this.$axios.post(this.$httpUrl + '/潜在用户信息/update', this.form).then(res => res.data).then(res => {
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
      <el-input v-model="搜索" placeholder="请输入姓名/电话" suffix-icon="el-icon-search" style="width: 200px"
                @keyup.enter.native="loadPost">
      </el-input>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" @click="re">重置</el-button>
    </div>

    <el-table :data="tableData"
              :header-cell-style="{background:'#D8D8D8'}"
              style="font-size: 100%;background-color: rgb(255,255,255,0);"
    >
      <el-table-column prop="id" label="Id" width="70%">
      </el-table-column>
      <el-table-column prop="姓名" label="姓名" width="120%">
      </el-table-column>
      <el-table-column prop="电话" label="电话" width="150%">
      </el-table-column>
      <el-table-column prop="租赁开始时间" label="租赁开始时间" width="130%">
      </el-table-column>
      <el-table-column prop="租赁结束时间" label="租赁结束时间" width="130%">
      </el-table-column>
      <el-table-column prop="问题" label="问题">
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
        :page-sizes="[14, 28, 42, 60]"
        :page-size="每页数据"
        layout="total, sizes, prev, pager, next, jumper"
        :total="记录总和">
    </el-pagination>

    <!--    修改数据弹窗-->
    <el-dialog
        title="新增数据"
        :visible.sync="centerDialogVisible"
        width="28%"
        center
        :before-close="handleClose">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="姓名" prop="姓名">
          <el-col :span="20">
            <el-input v-model="form.姓名" maxlength="5" show-word-limit></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="电话" prop="电话">
          <el-col :span="20">
            <el-input v-model="form.电话"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="租赁开始时间" prop="租赁开始时间" label-width="5">
          <el-col :span="15">
            <el-input v-model="form.租赁开始时间"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="租赁结束时间" prop="租赁结束时间" label-width="5">
          <el-col :span="15">
            <el-input v-model="form.租赁结束时间"></el-input>
          </el-col>
        </el-form-item>


        <el-form-item label="问题" prop="问题">
          <el-col :span="20">
            <el-input type="textarea"
                      :rows="4" v-model="form.问题"></el-input>
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
.MainBody {
  height: 100%;
  width: 100%;
}
</style>