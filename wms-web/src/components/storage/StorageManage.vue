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
      状态: '',
      地区: '',
      状态数组: [{
        value: '已租赁',
        label: '已租赁'
      }, {
        value: '未租赁',
        label: '未租赁'
      }, {
        value: '已过期',
        label: '已过期'
      },],
      地区数据: [{
        value: '藁城区',
        label: '藁城区'
      }, {
        value: '正定县',
        label: '正定县'
      }, {
        value: '裕华区',
        label: '裕华区'
      }],
      // 默认弹窗不显示
      centerDialogVisible: false,
      form: {
        id: "",
        仓库编号: "",
        仓库名称: "",
        仓库地区: "",
        仓库管理者uid: "",
        仓库类别: "",
        仓库状态: "",
        备注: "",
        仓库公告: "",
        仓库优点: "",
        仓库缺点: "",
        租赁开始时间: "",
        租赁结束时间: "",
        img0: "",
        img1: "",
      },
      rules: {}
    }
  },
  methods: {
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

    save() {
      //进行表单验证
      if (this.form.id) {
        this.doMod()
      } else {
        this.doSave()
      }
    },
    doMod() {
      // 修改数据
      this.$axios.post(this.$httpUrl + '/storage/update', this.form).then(res => res.data).then(res => {
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
      this.$axios.post(this.$httpUrl + '/storage/插入', this.form).then(res => res.data).then(res => {
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
      this.$axios.get(this.$httpUrl + '/storage/删除?uid=' + uid).then(res => res.data).then(res => {
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
        this.form.id = row.id
        this.form.仓库名称 = row.仓库名称
        this.form.仓库编号 = row.仓库编号
        this.form.仓库地区 = row.仓库地区
        this.form.仓库管理者uid = row.仓库管理者uid
        this.form.仓库类别 = row.仓库类别
        this.form.仓库状态 = row.仓库状态
        this.form.仓库公告 = row.仓库公告
        this.form.仓库优点 = row.仓库优点
        this.form.仓库缺点 = row.仓库缺点
        this.form.备注 = row.备注
        this.form.租赁开始时间 = row.租赁开始时间
        this.form.租赁结束时间 = row.租赁结束时间
        this.form.img0 = row.img0
        this.form.img1 = row.img1
      })
    },
    //查询或模糊查询数据
    loadPost() {
      //调用axios属性的post方法向url提交post请求体并请求返回数据
      this.$axios.post(this.$httpUrl + '/storage/模糊查询并分页进行数据封装', {
        页数: this.当前页,
        每页数据: this.每页数据,
        分页数据封装: {
          搜索: this.搜索,
          地区: this.地区,
          状态: this.状态,
        },
      }).then(res => res.data).then(res => {
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
      this.地区 = '';
      this.状态 = '';
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
      <el-input v-model="搜索" placeholder="请输入Id/名称/管理者Uid" suffix-icon="el-icon-search" style="width: 200px"
                @keyup.enter.native="loadPost">
      </el-input>
      <el-select v-model="状态" filterable placeholder="状态" style="margin-left: 0.3%;width: 85px">
        <el-option
          v-for="item in 状态数组"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <el-select v-model="地区" filterable placeholder="地区" style="margin-left: 0.3%;width: 85px">
        <el-option
          v-for="item in 地区数据"
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
      <el-table-column prop="id" label="Id" width="50%">
      </el-table-column>
      <el-table-column prop="仓库编号" label="仓库编号" width="90%">
      </el-table-column>
      <el-table-column prop="仓库名称" label="仓库名称" width="100%">
      </el-table-column>
      <el-table-column prop="仓库管理者uid" label="仓库管理者uid" width="130%" align="center">
      </el-table-column>
      <el-table-column prop="仓库类别" label="仓库类别" width="120%">
      </el-table-column>
      <el-table-column prop="租赁开始时间" label="租赁开始时间" width="120%">
      </el-table-column>
      <el-table-column prop="租赁结束时间" label="租赁结束时间" width="120%">
      </el-table-column>
      <el-table-column prop="仓库地区" label="仓库地区" width="90%">
      </el-table-column>
      <el-table-column prop="仓库状态" label="仓库状态" width="85%">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.仓库状态 === '已租赁' ? 'primary' : scope.row.仓库状态 === '未租赁' ? 'success' : 'warning'"
            disable-transitions>{{ scope.row.仓库状态 }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="仓库优点" label="仓库优点">
      </el-table-column>
      <el-table-column prop="仓库缺点" label="仓库缺点">
      </el-table-column>
      <el-table-column prop="仓库公告" label="仓库公告">
      </el-table-column>
      <el-table-column prop="备注" label="备注">
      </el-table-column>
      <el-table-column prop="img0" label="img0">
      </el-table-column>
      <el-table-column prop="img1" label="img1">
      </el-table-column>

      <el-table-column prop="执行操作" label="执行操作" width="140%" align="center">
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


    <!--    新增数据弹窗-->
    <el-dialog
      title="新增数据"
      :visible.sync="centerDialogVisible"
      width="28%"
      center
      :before-close="handleClose">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="仓库编号" prop="仓库编号">
          <el-col :span="20">
            <el-input v-model="form.仓库编号"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="仓库名称" prop="仓库名称">
          <el-col :span="20">
            <el-input v-model="form.仓库名称"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="地区" prop="地区">
          <el-col :span="20">
            <el-select v-model="form.仓库地区" placeholder="仓库地区">
              <el-option label="裕华区" value="裕华区"></el-option>
              <el-option label="正定县" value="正定县"></el-option>
              <el-option label="藁城区" value="藁城区"></el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="管理者Uid" prop="仓库管理者uid">
          <el-col :span="10">
            <el-input v-model="form.仓库管理者uid"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="仓库类别" prop="仓库类别">
          <el-col :span="20">
            <el-input v-model="form.仓库类别"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="租赁开始时间" prop="租赁开始时间" label-width="80">
          <el-col :span="16">
            <el-input v-model="form.租赁开始时间"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="租赁结束时间" prop="租赁结束时间" label-width="80">
          <el-col :span="16">
            <el-input v-model="form.租赁结束时间"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="状态" prop="状态">
          <el-radio-group v-model="form.仓库状态">
            <el-radio label="未租赁"></el-radio>
            <el-radio label="已租赁"></el-radio>
            <el-radio label="已过期"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="仓库备注" prop="仓库备注">
          <el-col :span="20">
            <el-input v-model="form.备注"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="仓库公告" prop="仓库公告">
          <el-col :span="20">
            <el-input v-model="form.仓库公告"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="仓库优点" prop="仓库优点">
          <el-col :span="20">
            <el-input v-model="form.仓库优点"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="仓库缺点" prop="仓库缺点">
          <el-col :span="20">
            <el-input v-model="form.仓库缺点"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="img0" prop="img0">
          <el-col :span="20">
            <el-input v-model="form.img0"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="img1" prop="img1">
          <el-col :span="20">
            <el-input v-model="form.img1"></el-input>
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
}
</style>
