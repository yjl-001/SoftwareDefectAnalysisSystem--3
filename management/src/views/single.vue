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

        <el-form-item label="特征指标选择" >
          <el-select v-model="form.quota" placeholder="请选择">
            <el-option label="CNN" value="cnn"></el-option>
            <el-option label="BBC" value="bbc"></el-option>
            <el-option label="Times" value="times"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="特征1">
          <el-input v-model="form.numberOfNonTrivialBugsFoundUntil" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征2">
          <el-input v-model="form.cvsWEntropy" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征3">
          <el-input v-model="form.cvsEntropy" style="width: 200px">
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
          <el-button @click="">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="box2">
      <el-form class="box1" ref="form" :model="form" label-width="80px" style="margin: 20px;font-weight: bold">
        <el-form-item label="特征1">
          <el-input v-model="form.cvsLogEntropy" style="width: 200px">
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
          <el-input v-model="form.cvsLinEntropy" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征5">
          <el-input v-model="form.numberOfBugsFoundUntil" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="特征6">
          <el-input v-model="form.cvsExpEntropy" style="width: 200px">
          </el-input>
        </el-form-item>

        <el-form-item label="多出来的特征" v-if="form.quota=='cnn'" style="width: 200px">
          <el-input v-model="form.extra"  style="width: 200px">
          </el-input>
        </el-form-item>

      </el-form>

      <el-dialog
          title="预测结果"
          :visible.sync="dialogVisible"
          width="30%">
        <span>{{ this.result }}</span>
        <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">确 定 并 保 存</el-button>
  </span>
      </el-dialog>
    </div>


  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      dialogVisible: false,
      result:'',
      form: {
        model:'logistic',
        quota:'cnn',
      },
    }
  },
  mounted(){

  },
  methods: {

    onSubmit() {
      console.log('submit!');
      console.log(this.form);
        //上传
        request.post("/api/predict/single",this.form).then(res =>{
          console.log(res.data)
          if(res.data=='buggy'){
            this.result='恐怕是有点Bug..';
            this.dialogVisible=true;
          }else{
            this.result='牛,没Bug..';
            this.dialogVisible=true;
          }
        })
    },


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