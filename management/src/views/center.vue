<template>
  <div class="container">
    <div class="left_">
      <div class="brief_info">
        <h3>快速开始</h3>
        <p>用我们的系统预测Bug，就是你程序最大的Bug...</p>
        <img src="../assets/back1.png" alt="">
      </div>
      <div class="customer">
        <p>客户信息</p>
        <CustomerInfo :info="info"></CustomerInfo>
      </div>
      <div class="my_data">
        <p>我的数据</p>
        <div class="nav_">
          <Nav_ class='nav_item' icon_src='icon.png' info="我的数据集" num=10></Nav_>
          <Nav_ class='nav_item' icon_src='icon.png' info="我的预测报告" num=87></Nav_>
          <Nav_ class='nav_item' icon_src='icon.png' info="我的模型" num=5></Nav_>
        </div>
      </div>
    </div>
    <div class="right_">
      <div class="banner">
        <p>系统管理员您好，今天也是元气满满的一天~</p>
        <p>登录时间：2022-10-06 10:20</p>
      </div>
      <div class="announcement">
        <p>公告</p>
        <Announcement class="announc_item" icon_src='document_.png' title="更新了新的预测模型" date="2022-10-06 10:20">
        </Announcement>
        <Announcement class="announc_item" icon_src='document_.png' title="xxxxxxxxxxxxxxxxxxxxxxxx" date="2022-10-06 10:20">
        </Announcement>
        <Announcement class="announc_item" icon_src='document_.png' title="xxxxxxx" date="2022-10-06 10:20">
        </Announcement>
        <Announcement class="announc_item" icon_src='document_.png' title="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" date="2022-10-06 10:20">
        </Announcement>
        <Announcement class="announc_item" icon_src='document_.png' title="xxxxxxxxx" date="2022-10-06 10:20">
        </Announcement>
      </div>
      <p class="settings_p">我的设置</p>
      <div class="settings">
        <SettingButton class="s_btn" icon_src='icon1.png' title="修改邮箱"></SettingButton>
        <SettingButton class="s_btn" icon_src='icon2.png' title="重置密码"></SettingButton>
        <SettingButton class="s_btn" icon_src='icon3.png' title="重置邮箱"></SettingButton>
        <SettingButton class="s_btn" icon_src='icon4.png' title="修改手机"></SettingButton>
        <SettingButton class="s_btn" icon_src='icon5.png' title="修改信息"></SettingButton>
        <SettingButton class="s_btn" icon_src='icon6.png' title="注销"></SettingButton>
      </div>
    </div>
  </div>
</template>

<script>
import CustomerInfo from '@/components/CustomerInfo.vue';
import Nav_ from "@/components/Nav_.vue";
import SettingButton from '@/components/SettingButton.vue';
import Announcement from '@/components/Announcement.vue';

export default {
  name: "center.vue",
  components: {
    CustomerInfo,
    Nav_,
    SettingButton,
    Announcement,
  },
  data(){
    return{
      info:{
        account: '1145147979812',
        name: '是小熏',
        gender: '女',
        number: '13888881888',
        id_type: '身份证',
        id_: '440113198907180037',
        birth_date: '1998-07-18',
        verified: '通过',
        risk_level: '进取型',
        risk_date: '2023-01-01',
        customer_level:'高级会员',
        net_verified: '身份证号码与姓名一致',
      }
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
}

.container .left_ {
  padding: 0 0 0 15px;
}

.container .left_ .brief_info {
  margin: 20px 0 130px 0;
  position: relative;
}

.container .left_ .brief_info h3 {
  color: #1FC7ED;
  margin: 0 0 10px 0;
}

.container .left_ .brief_info p {
  font-size: 14px;
  color: #303133;
  font-weight: 400;
}

.container .left_ .brief_info img{
  position:absolute;
  width:200px;
  top:0;
  right:-40px;
}

.container .left_ .customer {}

.container .left_ .customer p {
  font-size: 14px;
  color: #303133;
  font-weight: 600;
  margin: 0 0 15px 0;
}

.container .left_ .my_data {
  margin: 60px 0 0 0;
}

.container .left_ .my_data p {
  margin: 0 0 26px 0;
  font-size: 14px;
  color: #303133;
  font-weight: 600;
}

.container .left_ .my_data .nav_ {
  display: flex;
}

.container .left_ .my_data .nav_ .nav_item {
  margin: 0 100px 0 0;
}

.container .left_ .my_data .nav_ .nav_item:nth-of-type(1) {
  margin: 0 100px 0 40px;
}

.container .left_ .my_data .nav_ .nav_item:nth-of-type(3) {
  margin: 0;
}

.container .right_ {
  padding: 0 0 0 30px;
}

.container .right_ .banner {
  border-radius: 15px;
  background-color: #1FC7ED;
  height: 110px;
  margin: 20px 0 20px 0;
  color: white;
  padding: 28px 0 0 100px;
}

.container .right_ .banner p:nth-of-type(1) {
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 15px;
}

.container .right_ .banner p:nth-of-type(2) {
  font-size: 12px;
  font-weight: 600;
}

.container .right_ .announcement {}

.container .right_ .announcement p {
  margin: 0 0 26px 0;
  font-size: 14px;
  color: #303133;
  font-weight: 600;
  position: relative;
}

.container .right_ .announcement p:after {
  display: block;
  content: '更多';
  position: absolute;
  top: 0;
  right: 0;
  color:#1FC7ED;
}

.container .right_ .announcement .announc_item {
  margin: 0 0 15px 0;
}

.container .right_ .settings {
  display: grid;
  grid-template-columns: 1fr 1fr;
}

.container .right_ p.settings_p {
  margin: 30px 0 26px 0;
  font-size: 14px;
  color: #303133;
  font-weight: 600;
  position: relative;
}

.container .right_ p.settings_p:after {
  display: block;
  content: '更多';
  position: absolute;
  top: 0;
  right: 0;
  color:#1FC7ED;
}

.container .right_ .settings .s_btn {
  margin-bottom: 15px;
}

.container .right_ .settings .s_btn:nth-of-type(odd) {
  margin-right: 15px;
}
</style>
