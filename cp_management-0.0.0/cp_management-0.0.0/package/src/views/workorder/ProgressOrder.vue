<script setup>
import { ref } from 'vue'
const progressOrder = ref([
    {
        "id": "",
        "project": "",
        "address": "",
        "userPhone": "",
        "description": "",
        "status": "",
        "createTime": "",
        "updateTime": ""
    }

])
//声明异步函数
import {getOrderInfoService,getUserLimitService,getTotalService} from '@/api/workorder.js'
// const getOrderInfo =async()=>{
//     let result = await getOrderInfoService();
//     progressOrder.value =result.data ;
// }


//活动信息分页条数据模型
const pageNum =ref(1)
const pageSize =ref(15)
const total =ref(30)
const getTotal=async()=>{
  let result =await getTotalService();
  total.value=result.data
}
getTotal();
const onCurrentChange =async (num)=>{
  pageNum.value=num
  let result=await getUserLimitService(pageNum.value);
  progressOrder.value=result.data
}
onCurrentChange(1)





// getOrderInfo();
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>工单进度</span>
            </div>
        </template>
        <el-table :data="progressOrder" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="项目名称" prop="project"></el-table-column>
            <el-table-column label="地址"  prop="address"></el-table-column>
            <el-table-column label="联系方式"  prop="userPhone"></el-table-column>
            <el-table-column label="描述"  prop="description"></el-table-column>
            <el-table-column label="状态" prop="status"></el-table-column>
            <el-table-column label="创建时间" width="200" prop="createTime"></el-table-column>
            <el-table-column label="更新时间" width="200" prop="updateTime"></el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
        <div class="pageLimit">
            <el-pagination  
            layout="prev, pager, next" 
            :total="total" 
            v-model:current-page="pageNum"
            v-model:page-size="pageSize"
            @current-change="onCurrentChange"
            style="margin-top: 20px;justify-content: flex-end;"
            />
        </div>
    </el-card>
</template>

<style lang="scss" scoped>
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