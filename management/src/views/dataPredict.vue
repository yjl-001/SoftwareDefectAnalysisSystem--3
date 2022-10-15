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
                  class="upload-demo"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  multiple
                  :limit="3"
                  :on-exceed="handleExceed"
                  :file-list="fileList">
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">选择上传txt/data/csv文件</div>
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
  methods: {
    onSubmit() {
      console.log('submit!');
      if(this.form.time1>this.form.time2){
        this.$alert('起止时间错误。', '起草失败！', {
          type: 'warning',
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: '',
              message: `action: ${ action }`
            });
          }
        });
      }else{
        //弹窗
        this.$alert('已成功预测', '上传成功！', {
          type: 'success',
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'success',
              message: `action: ${ action }`
            });
          }
        });
        //上传
        request.post("/api/dataset_predict",this.form).then(res =>{
          console.log(res.data)
        })
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