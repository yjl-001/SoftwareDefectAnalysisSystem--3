<template>
  <div class="body">
    <div style="padding: 30px;background:#409EFF;border-radius: 15px 15px 15px 15px ;
  box-shadow: 2px 2px 10px #475669;"   >
      <p style="font-size: 120%;font-weight: bolder;color: white">
        数据集预测
      </p>
    </div>

    <el-form ref="form" :model="form" label-width="130px" style="margin: 20px;font-weight: bold">
      <el-form-item label="模型选择" >
        <el-select v-model="form.model" placeholder="请选择">
          <el-option label="逻辑回归" value="logistic"></el-option>
          <el-option label="支撑向量机" value="svm"></el-option>
          <el-option label="朴素贝叶斯" value="beysi"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="特征指标选择" >
        <el-select v-model="form.isdataset" placeholder="请选择">
          <el-option label="cvs" value="0"></el-option>
          <el-option label="LDHH" value="1"></el-option>
          <el-option label="WchU" value="2"></el-option>
        </el-select>
      </el-form-item>

            <el-form-item label="上传附件">
              <el-upload
                  size="small"
                  class="upload-demo"
                  headers="Content-Type': 'multipart/form-data"
                  drag
                  :on-change="handleChange"
                  :file-list="fileList"
                  action='/api/predict/dataset'
                  :auto-upload='false'
                  :before-upload="beforeAvatarUpload"
              >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <template v-slot:tip>
                  <div class="el-upload-tip el-upload-tip--small" style="margin-bottom: 5px">
                  </div>
                </template>
              </el-upload>
            </el-form-item>

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
                  v-if="scope.row.predictresult === 'clean'"
                  :type="scope.row.predictresult === 'clean' ? 'success' : 'danger'"
                  disable-transitions>通过</el-tag>
              <el-tag
                  v-if="scope.row.predictresult === 'buggy'"
                  :type="scope.row.predictresult === 'clean' ? 'success' : 'danger'"
                  disable-transitions>异常</el-tag>
            </template>
            width="100">
          </el-table-column>
        </el-table>
      </el-dialog>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">预测</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>


  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      isvisible:false,
      fileType: ["csv", "txt","xls","xlxs"],
      fileList:[],
      predictData:[],
      user:{},
      form: {
        isdataset:'',
      },
    }
  },
  created(){
    let userStr = sessionStorage.getItem("user")||"{}"
    this.user=JSON.parse(userStr)
  },
  methods: {
    beforeAvatarUpload(file) {
      if (file.type != "" || file.type != null || file.type != undefined){
        //截取文件的后缀，判断文件类型
        const FileExt = file.name.replace(/.+\./, "").toLowerCase();
        //计算文件的大小
        const isLt5M = file.size / 1024 / 1024 < 50; //这里做文件大小限制
        //如果大于50M
        if (!isLt5M) {
          this.$message.error('上传文件大小不能超过 50MB!');
          return false;
        }
        //如果文件类型不在允许上传的范围内
        if(this.fileType.includes(FileExt)){
          return true;
        }
        else {
          this.$message.error("上传文件格式不正确!");
          return false;
        }
      }
    },
    UploadFile () {
      if(this.fileList.length <= 0){
        this.$message.error('请选择文件');
        return
      }
      const formData = new FormData();
      formData.append('file', this.fileList[0].raw)
      // post地址
      this.showResult = true;
    },
    handleChange(file) {
      this.fileList = [file]
    },
    onSubmit() {
      if(this.beforeAvatarUpload(this.fileList[0])==true&&this.form.model){
            this.$message({
              type:"success",
              message:"上传成功",
            })
        if(this.fileList.length <= 0){
          this.$message.error('请选择文件');
          return
        }
        const formData = new FormData();
        formData.append('file', this.fileList[0].raw)
        formData.append('userid', this.user.userid)
        formData.append('model', this.form.model)
        console.log(formData)
        // post地址
        //上传
        if(this.form.isdataset=='0'){
          request.post("/api/predict/dataset",formData,{header:{'Content-Type':'multipart/form-data'}}).then(res =>{
            this.isvisible=true
            this.predictData=res.data
          })
        }
        if(this.form.isdataset=='1'){
          request.post("/api/ldhh/dataset",formData,{header:{'Content-Type':'multipart/form-data'}}).then(res =>{
            this.isvisible=true
            this.predictData=res.data
          })
        }
        if(this.form.isdataset=='2'){
          request.post("/api/wchu/dataset",formData,{header:{'Content-Type':'multipart/form-data'}}).then(res =>{
            this.isvisible=true
            this.predictData=res.data
          })
        }

      }else{
        if(!this.form.model){
          this.$message({
            type:"error",
            message:"请选择模型!",
          })}
        }
      }
    }
}
</script>

}
<style scoped>

.body{
  padding: 10px;height: auto;margin-right: 15px;
  border-radius: 25px 25px 25px 25px ;
  box-shadow: 2px 2px 10px #06C;
}
</style>