<template>
  <div style="padding: 10px">
    <div style="padding: 30px;background:#409EFF "  >
      <p style="font-size: 120%;font-weight: bolder;color: white">
        单样本预测
      </p>
    </div>

    <el-form ref="form" :model="form" label-width="80px" style="margin: 20px;font-weight: bold">
      <el-form-item label="模型选择" >
        <el-select v-model="form.model" placeholder="请选择">
          <el-option label="逻辑回归" value="logistic"></el-option>
          <el-option label="线性回归" value="linear"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="特征1">
        <el-input v-model="form.data1" style="width: 200px">
        </el-input>
      </el-form-item>

      <el-form-item label="特征2">
        <el-input v-model="form.data2" style="width: 200px">
        </el-input>
      </el-form-item>

      <el-form-item label="特征3">
        <el-input v-model="form.data3" style="width: 200px">
        </el-input>
      </el-form-item>

      <el-form-item label="特征4">
        <el-input v-model="form.data4" style="width: 200px">
        </el-input>
      </el-form-item>

<!--      <el-form-item label="上传附件">-->
<!--        <el-upload-->
<!--            class="upload-demo"-->
<!--            action="https://jsonplaceholder.typicode.com/posts/"-->
<!--            :on-preview="handlePreview"-->
<!--            :on-remove="handleRemove"-->
<!--            :before-remove="beforeRemove"-->
<!--            multiple-->
<!--            :limit="3"-->
<!--            :on-exceed="handleExceed"-->
<!--            :file-list="fileList">-->
<!--          <el-button size="small" type="primary">点击上传</el-button>-->
<!--          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
<!--        </el-upload>-->
<!--      </el-form-item>-->


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
        model:'',
        data1:'',
        data2:'',
        data3:'',
        data4:'',
      },
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
      if(!this.form.htname){
        this.$alert('请输入完整特征', '预测失败！', {
          type: 'warning',
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: '',
              message: `action: ${ action }`
            });
          }
        });
      }else if(!this.form.content){
        this.$alert('请输入完整特征。', '预测失败！', {
          type: 'warning',
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: '',
              message: `action: ${ action }`
            });
          }
        });
      }
      else{

        // //弹窗
        // this.$alert('已成功起草', '上传成功！', {
        //   type: 'success',
        //   confirmButtonText: '确定',
        //   callback: action => {
        //     this.$message({
        //       type: 'success',
        //       message: `action: ${ action }`
        //     });
        //   }
        // });
        // //上传
        // request.post("/api/user",this.form).then(res =>{
        // })
      }
    }
  }
}
</script>

}
<style scoped>
</style>