<script setup>
import {
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const dealOrderData = ref([
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
const pageNum =ref(1)
const pageSize =ref(15)
const total =ref(30)
import {updateStatusService,getDealOrderService,getAdminTotalService} from '@/api/workorder.js'
const getTotal=async()=>{
  let result =await getAdminTotalService();
  total.value=result.data
}
getTotal();
const onCurrentChange =async(num)=>{
    pageNum.value=num
    let result = await getDealOrderService(pageNum.value);
    dealOrderData.value =result.data ;
}
onCurrentChange(1);

//删除历史记录函数
import { ElMessageBox } from 'element-plus';
const ElMessage = ElMessageBox
const updateOrderStatus=(row)=>{
    //提示用户 确认框
    ElMessageBox.confirm(
        '你确认处理该记录吗？',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            //用户点击了确认
            //调用接口
            let result=await updateStatusService(row.id)
            ElMessage({
                type: 'success',
                message: '处理成功',
            })
            //刷新列表
            // await deleteOrderHistoryService(row.id)
            getDealOrder();
        })
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>工单处理</span>
            </div>
        </template>
        <el-table :data="dealOrderData" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="项目名称"  prop="project"></el-table-column>
            <el-table-column label="地址"  prop="address"></el-table-column>
            <el-table-column label="联系方式"  prop="userPhone"></el-table-column>
            <el-table-column label="描述" prop="description"></el-table-column>
            <el-table-column label="状态"  prop="status"></el-table-column>
            <el-table-column label="创建时间" width="200" prop="createTime"></el-table-column>
            <el-table-column label="更新时间" width="200" prop="updateTime"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button type="primary" @click="updateOrderStatus(row)">处理</el-button>
                </template>
            </el-table-column>
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