
<template style="background-color: royalblue" >

  <div class="body">
    <div><p style="font-size: 200%;color: #409EFF;font-weight: bolder;margin-top: 20px;margin-left: 60px">
      基于机器学习的缺陷预测系统
    </p>
      <p style="font-size: 100%;color: darkgray;font-weight: bolder;margin-left: 60px;margin-top: 20px">
        使用我们的系统预测Bug，就是你们最大的Bug~
      </p></div>
    <div class="hello">
      <p>系统管理员您好，今天也是元气满满的一天~</p>
      <p>登录时间：2022-10-06 10:20</p>
    </div>

    <div>
<!--      <el-image style="margin-left: 60px;height: 400px;margin-top:20px " :src="require('D:\\JetBrainsCode\\WebStormProjects\\Vue\\management\\resources\\back1.png')" ></el-image>-->

    </div>
    <div class="div_abs">
      <el-button type="primary" icon="el-icon-document" round class="bt_main"style="margin-left: 10px;font-size: 150%;font-weight: bolder" @click="$router.push('/write')">单样本预测</el-button>
      <el-button  type="danger" icon="el-icon-document-copy" round class="bt_main" style="margin: 10px;font-size: 150%;font-weight: bolder" @click="$router.push('/sign')">数据集预测</el-button>
      <el-button  type="success" icon="el-icon-coordinate" round class="bt_main" style="font-size: 150%;font-weight: bolder">模型展示</el-button>
      <el-button  type="warning" icon="el-icon-refresh-left" round class="bt_main" style="font-size: 150%;font-weight: bolder" @click="$router.push('/datas')" >预测记录</el-button>
    </div>
  </div>


</template>
<script type="text/javascript">

export default {
  data() {
    return {
      map: null,
    }
  },
  mounted() {
  },
  methods: {}
}
</script>
<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  width: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.div_abs{
  width:700px;
  height:120px;
  right:0%;
  top:35%;
  float:right;
  position:absolute;
}
.bt_main{
  box-shadow: 3px 3px 3px #888888;
  height: 200px;
  width:300px;
}

.body{
  padding: 10px;height: auto;margin-right: 15px;
  border-radius: 25px 25px 25px 25px ;
  box-shadow: 2px 2px 10px #06C;
}

.hello {
  position: absolute;
  top:15%;
  right: 5%;
  width: 600px;
  border-radius: 15px;
  background-color: #1FC7ED;
  height: 110px;
  margin: 20px 0 20px 0;
  color: white;
  padding: 28px 0 0 100px;
}
</style>