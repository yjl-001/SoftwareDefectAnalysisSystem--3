<template>
  <div class="body">
    <div style="padding: 30px;background:#409EFF;border-radius: 15px 15px 15px 15px ;
  box-shadow: 2px 2px 10px #475669;" >
      <p style="font-size: 120%;font-weight: bolder;color: white">
        单样本预测
      </p>
    </div>
    <div class="box1">
      <el-form class="box1" ref="form" :model="form" label-width="80px" style="margin: 20px;font-weight: bold">
        <el-form-item label="模型选择" >
          <el-select v-model="form.model" placeholder="请选择">
            <el-option label="逻辑回归" value="logistic"></el-option>
            <el-option label="支撑向量机" value="svm"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="特征1">
          <el-input v-model="form.numberOfNonTrivialBugsFoundUntil" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征2">
          <el-input v-model="form.CvsWEntropy" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征3">
          <el-input v-model="form.CvsEntropy" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征4">
          <el-input v-model="form.numberOfCriticalBugsFoundUntil" style="width: 200px">
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
    <div class="box2">
      <el-form class="box1" ref="form" :model="form" label-width="80px" style="margin: 20px;font-weight: bold">
        <el-form-item label="特征1">
          <el-input v-model="form.CvsLogEntropy" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征2">
          <el-input v-model="form.numberOfHighPriorityBugsFoundUntil" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征3">
          <el-input v-model="form.numberOfMajorBugsFoundUntil" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征4">
          <el-input v-model="form.CvsLinEntropy" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征5">
          <el-input v-model="form.numberOfBugsFoundUntil" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征6">
          <el-input v-model="form.CvsExpEntropy" style="width: 200px">
          </el-input>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      form: {
        model:'',
      },
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
      console.log(this.form);
        //上传
        request.post("/api/single_predict",this.form).then(res =>{
          console.log(res.data)
        })
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

.box1{
  position: absolute;
  left: 20%  ;
  top:25%;
}

.box2{
  position: absolute;
  left: 40%  ;
  top:25%;
}


</style>