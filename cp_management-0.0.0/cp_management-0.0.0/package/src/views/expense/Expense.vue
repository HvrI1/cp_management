<script setup>
import { ref } from 'vue'
const expense = ref([
    {
        "id": "",
        "project": "",
        "amount": "",
        "paymentStatus": "",
        "dueDate": "",
        "createTime": "",
        "updateTime": ""
    }

])
//声明异步函数
const pageNum =ref(1)
const pageSize =ref(15)
const total =ref(30)
import {getUserExpenseService,updateStatusService,getTotalService} from '@/api/expense.js'
const getTotal=async()=>{
  let result =await getTotalService();
  total.value=result.data
}
getTotal();
const onCurrentChange =async(num)=>{
    pageNum.value=num
    let result = await getUserExpenseService(pageNum.value);
    expense.value =result.data ;
}
onCurrentChange(1);

//删除历史记录函数
import { ElMessageBox } from 'element-plus';
const ElMessage = ElMessageBox
const updateStatus=(row)=>{
    //提示用户 确认框
    ElMessageBox.confirm(
        '你确认提交缴费申请吗？',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            //用户点击了确认
            //调用删除接口 删除当前缴费记录
            let result=await updateStatusService(row.id)
            ElMessage({
                type: 'success',
                message: '费用已从账户余额中自动扣除',
            })
            getExpenseInfo()
        })
        .catch(() => {
            //用户点击了取消
            ElMessage({
                type: 'info',
                message: '取消缴费',
            })
        })
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>缴费管理</span>
            </div>
        </template>
        <el-table :data="expense" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="缴费项目"  prop="project"></el-table-column>
            <el-table-column label="费用金额"  prop="amount"></el-table-column>
            <el-table-column label="缴费状态"  prop="paymentStatus"></el-table-column>
            <el-table-column label="应缴费日期" prop="dueDate"></el-table-column>
            <el-table-column label="创建时间"  prop="createTime"></el-table-column>
            <el-table-column label="更新时间"  prop="updateTime"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button  type="primary" plain @click="updateStatus(row)">缴费</el-button>
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