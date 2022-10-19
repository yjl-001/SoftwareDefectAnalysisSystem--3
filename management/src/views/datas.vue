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
            <el-option label="不指定" value=""></el-option>
            <el-option label="单条数据" value="single"></el-option>
            <el-option label=".txt" value="txt"></el-option>
            <el-option label=".csv" value="csv"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="使用模型" style="font-weight: bolder">
          <el-select v-model="form.model" placeholder="使用模型">
            <el-option label="不指定" value=""></el-option>
            <el-option label="逻辑回归" value="logistic"></el-option>
            <el-option label="支持向量机" value="svm"></el-option>
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
              @click="del(scope.$index,scope.row)"
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

    <el-dialog title="数据预测详情" :visible.sync="isvisible">
      <el-table
          :data="predictData"
          border
          style="width: 100%">
        <el-table-column
            fixed
            prop="dataid"
            label="数据标号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="numberOfNonTrivialBugsFoundUntil"
            label="特征1"
            width="120">
        </el-table-column>
        <el-table-column
            prop="province"
            label="特征2"
            width="120">
        </el-table-column>
        <el-table-column
            prop="city"
            label="特征3"
            width="120">
        </el-table-column>
        <el-table-column
            prop="address"
            label="特征4"
            width="120">
        </el-table-column>
        <el-table-column
            prop="zip"
            label="特征5"
            width="120">
        </el-table-column>
        <el-table-column
            fixed="right"
            prop="predictresult"
            label="结果"
            :filters="[{ text: '通过', value: 'clean' }, { text: '异常', value: 'buggy' }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
                :type="scope.row.predictresult === 'clean' ? 'success' : 'danger'"
                disable-transitions>{{scope.row.predictresult}}</el-tag>
          </template>
            width="100">
        </el-table-column>
      </el-table>
    </el-dialog>


  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  data(){
    return{
      isvisible:false,
      user:{},
      total:0,
      pageNum:1,
      PageSize:10,
      predictData:[],
      form:{
        model:''
      },
      tableData:[
      ]
    }
  },
  created(){
    let userStr = sessionStorage.getItem("user")||"{}"
    this.user=JSON.parse(userStr)
    this.load()
  },
  methods: {
    filterTag(value, row) {
      return row.predictresult === value;
    },
    del(index,row){
      this.$confirm("永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
          .then(() => {
            // 移除对应索引位置的数据，可以对row进行设置向后台请求删除数据
            this.tableData.splice(index, 1);
            request.get("/api/datasets_center/deleteHistory",{
              params:{
                datasetid:row.datasetid
              }}).then(res =>{
              console.log(res)
            })
            this.$message({
              type: "success",
              message: "删除成功!"
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
    },
    load(){
      request.get("/api/datasets_center/none",{
        params:{
          pageNum:1,
          pageSize: 100,
          username:this.user.username}
      }).then(res =>{
        console.log(res)
        console.log("request")
        this.tableData = res.data.records
        this.total=res.data.total
      })
    },
    sign_contract(row) {
      console.log(row.datasetid)
      console.log(row.datasetname)
      this.isvisible=true
      request.get("/api/datasets_center/datasetResult",{
        params:{
          datasetid:row.datasetid
        }}).then(res =>{
        console.log(res)
        this.predictData=res.data
      })
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
.box-card {
  width: 480px;
}

</style>