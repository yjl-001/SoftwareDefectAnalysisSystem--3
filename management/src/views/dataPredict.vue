<template>
  <div class="body">
    <div style="padding: 30px;background:#409EFF;border-radius: 15px 15px 15px 15px ;
  box-shadow: 2px 2px 10px #475669;"   >
      <p style="font-size: 120%;font-weight: bolder;color: white">
        数据集预测
      </p>
    </div>

    <el-form ref="form" :model="form" label-width="80px" style="margin: 20px;font-weight: bold">
      <el-form-item label="模型选择" >
        <el-select v-model="value" placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
            <el-form-item label="上传附件">
              <el-upload
                  size="small"
                  class="upload-demo"
                  drag
                  :on-change="handleChange"
                  :file-list="fileList"
                  action=''
                  :auto-upload='true'
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
      fileType: ["csv", "txt","xls","xlxs","jpg", "jpeg"],
      fileList:[],
      user:{},
      form: {
        where:1,
        hq1:"",
        hq2:"",
        dg1:"",
        dg2:"",
        sp1:"",
        sp2:"",
        qd1:"",
        qd2:"",
      },
      options: [{
        value: 'logistic',
        label: '逻辑回归'
      }, {
        value: 'svm',
        label: '支撑向量机'
      }],
      value: ''
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
      if(this.fileList.length <= 0){
        this.$message.error('请选择文件');
        return
      }
      const formData = new FormData();
      formData.append('file', this.fileList[0].raw)
      // post地址
      //上传
      request.get("/api/predict/dataset",{
        params:{
          file:formData,
          userid:this.user.userid,
        }
      }).then(res =>{

      })


      console.log(this.fileList)
      this.showResult = true;
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