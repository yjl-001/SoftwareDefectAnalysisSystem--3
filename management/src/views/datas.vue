<template>
  <div style="padding: 10px" class="body">
<!--    <div style="padding: 30px;background:#409EFF "  >-->
<!--      <p class="sample" style="font-size: 120%;font-weight: bolder;color: white">-->
<!--        预测记录查询-->
<!--      </p>-->
<!--    </div>-->
    <!--      搜索区域-->
    <div style="padding: 10px">
      <el-form :inline="true"  :model="form" class="demo-form-inline">
        <el-form-item label="数据集名称" style="font-weight: bolder">
          <el-input v-model="form.datasetname" placeholder="数据集名称"></el-input>
        </el-form-item>
        <el-form-item label="数据格式" style="font-weight: bolder">
          <el-select v-model="form.datasetKind" placeholder="数据格式">
            <el-option label="单条数据" value="single"></el-option>
            <el-option label=".txt" value="txt"></el-option>
            <el-option label=".csv" value="csv"></el-option>
            <el-option label=".data" value="data"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="使用模型" style="font-weight: bolder">
          <el-select v-model="form.model" placeholder="使用模型">
            <el-option label="逻辑回归" value="logistic"></el-option>
            <el-option label="线性回归" value="linear"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!--    表单显示区-->
    <el-table
        :data="tableData"
        style="width: 100%"
        max-height="250">
      <el-table-column
          fixed
          prop="datasetname"
          label="数据名称"
          width="250"
      >
      </el-table-column>
      <el-table-column
          prop="uploadtime"
          label="上传时间"
          width="200">
      </el-table-column>
      <el-table-column
          prop="datasetKind"
          label="类型"
          width="120">
      </el-table-column>
      <el-table-column
          prop="model"
          label="使用模型"
          width="120">
      </el-table-column>


      <el-table-column
          label="操作"
          width="180">
        <template slot-scope="scope">
          <el-button
              @click="sign_contract(scope.row)"
              type="text"
              size="small">
            查看预测结果
          </el-button>
          <el-button
              @click="del(scope.row)"
              type="text"
              size="small">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--    翻页-->
    <div class="block">
      <span class="demonstration">显示总数</span>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage1"
          :page-size="100"
          layout="total, prev, pager, next"
          :total=total>
      </el-pagination>
    </div>



  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      user:{},
      total:0,
      pageNum:1,
      PageSize:10,
      form:{
        model:''
      },
      tableData:[
        {
          datasetName:'测试数据（单条数据）',
          updateTime:'2020-12-06',
          Model:'线性回归',
          datasetKind:'单条数据',
        },
        {
          datasetName:'测试数据（.csv）',
          updateTime:'2021-12-06',
          Model:'逻辑回归',
          datasetKind:'.csv',
        }
        ,
      ]
    }
  },
  created(){
    let userStr = sessionStorage.getItem("user")||"{}"
    this.user=JSON.parse(userStr)
    this.load()
  },
  methods: {
    del(row){
    },
    load(){
          request.get("/api/datasets_center/none",{
            params:{
              pageNum:1,
              PageSize: 100,
              user:this.user.username}
          }).then(res =>{
            console.log(res)
            console.log("request")
            this.tableData = res.data.records
            this.total=res.data.total
          })
    },
    sign_contract(name) {
      // rows.splice(index, 1);
      this.dialogFormVisible = true
      this.signdata=JSON.parse(JSON.stringify(name))
    },
    onSubmit(){
      console.log(this.form),
      request.get("/api/datasets_center/search",
          {
        params:{
          pageNum:1,
          pageSize: 10,
          username:this.user.username,
          datasetname:this.form.datasetname,
          datasetKind:this.form.datasetKind,
          model:this.form.model
        }
      }).then(res =>{
        console.log(res)
        console.log("request")
        this.tableData = res.data.records
        this.total=res.data.total
      })
    },
  },

}
</script>

<style scoped>
  .block{
    position: absolute;
    top:80%;

  }
  .body{
    padding: 10px;height: auto;margin-right: 15px;
    border-radius: 25px 25px 25px 25px ;
    box-shadow: 2px 2px 10px #06C;
  }
</style>