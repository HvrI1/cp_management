<script setup>
import { ref } from 'vue'
const orderInfo = ref([
    {
        "project": "",
        "address": "",
        "description": ""
    }

])

//修改个人信息
import { provideOrderService } from '@/api/workorder.js';
import { ElMessage } from 'element-plus';
const provideOrderInfo =async()=>{
    let result=await provideOrderService(orderInfo.value);
    ElMessage.success(result.message?result.message:'提交成功')
}
const clearData = ()=>{
    orderInfo.value.project='';
    orderInfo.value.address='';
    orderInfo.value.description='';
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>提交工单</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :data="orderInfo"   label-width="100px" size="large">

                    <el-form-item label="项目名称" >
                        <el-input v-model="orderInfo.project"></el-input>
                    </el-form-item>
                    
                    <el-form-item label="上门地址" >
                        <el-input v-model="orderInfo.address"></el-input>
                    </el-form-item>
                    <el-form-item label="问题描述" >
                        <el-input v-model="orderInfo.description"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="provideOrderInfo();clearData()">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>