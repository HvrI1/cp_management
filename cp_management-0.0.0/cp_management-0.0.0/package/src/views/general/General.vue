<script setup>
import { ref } from 'vue'
const infoData = ref([
  {
    "date": "",
    "title": "",
    "content": ""
  }
])
const activityData = ref([
  {
    "date": "",
    "title": "",
    "content": ""
  }
])
import {getInfoService,getActivityService} from '@/api/general'
const getInfo =async()=>{
  let result =await getInfoService();

  infoData.value=result.data
}
const getActivity =async()=>{
  let result =await getActivityService();

  activityData.value=result.data
}

import {getLimitInfoService,getLimitActivityService,getInfoTotalService,getActivityTotalService} from '@/api/notice.js'
//公告信息分页条数据模型
const InfopageNum =ref(1)
const InfopageSize =ref(7)
const Infototal =ref(30)
const getInfoTotal=async()=>{
  let result =await getInfoTotalService();
  Infototal.value=result.data
}
getInfoTotal();
const InfoonCurrentChange =async (num)=>{
  InfopageNum.value=num
  let result=await getLimitInfoService(InfopageNum.value);
  infoData.value=result.data
  
}
InfoonCurrentChange(1);
//活动信息分页条数据模型
const ActivitypageNum =ref(1)
const ActivitypageSize =ref(7)
const Activitytotal =ref(30)
const getActivityTotal=async()=>{
  let result =await getActivityTotalService();
  Activitytotal.value=result.data
}
getActivityTotal();
const ActivityonCurrentChange =async (num)=>{
  ActivitypageNum.value=num
  let result=await getLimitActivityService(ActivitypageNum.value);
  activityData.value=result.data
}
ActivityonCurrentChange(1)


// getInfo();
// getActivity();
</script>
<template>


  <div class="pmd">
    <el-carousel :interval="5000" arrow="always" height=" 300px">
      <el-carousel-item v-for="item in 4" :key="item">
      </el-carousel-item>
    </el-carousel>
  </div>

  <div class="box">
    <el-card class="t1 page-container">
      <template #header>
        <div class="header">
          <span>小区通知</span>
        </div>

      </template>

      <el-table :data="infoData" style="width: 100%">
        <el-table-column prop="date" label="时间" width="180" />
        <el-table-column prop="title" label="标题" width="180" :show-overflow-tooltip="true" />
        <el-table-column prop="content" label="内容" :show-overflow-tooltip="true" />


      </el-table>

      <!-- 分页 -->
      <div class="pageLimit">
        <el-pagination  
        layout="prev, pager, next" 
        :total="Infototal" 
        v-model:current-page="InfopageNum"
        v-model:page-size="InfopageSize"
        @current-change="InfoonCurrentChange"
        style="margin-top: 20px;justify-content: flex-end;"
         />
      </div>
    </el-card>


    <el-card class="t2 page-container">
      <template #header>
        <div class="header">
          <span>小区活动</span>
        </div>
      </template>
      <el-table :data="activityData" style="width: 100%">
        <el-table-column prop="date" label="时间" width="180" />
        <el-table-column prop="title" label="标题" width="180" :show-overflow-tooltip="true" />
        <el-table-column prop="content" label="内容" :show-overflow-tooltip="true" />
      </el-table>
       <!-- 分页 -->
       <div class="pageLimit">
        <el-pagination  
        layout="prev, pager, next" 
        :total="Activitytotal" 
        v-model:current-page="ActivitypageNum"
        v-model:page-size="ActivitypageSize"
        @current-change="ActivityonCurrentChange"
        style="margin-top: 20px;justify-content: flex-end;"
         />
      </div>
    </el-card>
  </div>


</template>
  
  <style scoped>
  .el-carousel__item:nth-child(1) {
    background-image: url('@/assets/pmd_01.jpg') ;
    background-position: center center;
    background-size: cover;
    background-color: #464646;
  }
  
  .el-carousel__item:nth-child(2) {
    background-image: url('@/assets/pmd_02.jpg') ;
    background-position: center center;
    background-size: cover;
    background-color: #464646;
  }
  .el-carousel__item:nth-child(3) {
    background-image: url('@/assets/pmd_03.jpg') ;
    background-position: center center;
    background-size: cover;
    background-color: #464646;
  }
  .el-carousel__item:nth-child(4) {
    background-image: url('@/assets/pmd_04.jpg') ;
    background-position: center center;
    background-size: cover;
  }
.pmd{
  /* width: 1660px; */
  width: 100%;
}
.box{
  /* width: 1660px; */
  width: 100%;
  /* height: 400px; */
}
.t1{
  float: left;
  /* width: 825px; */
  width: 50%;
  /* height: 450px; */
}
.t2{
  float: left;
  /* width: 825px; */
  width: 50%;
  /* height: 450px; */
}
.fl{
  float: left;
}
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
  </style>