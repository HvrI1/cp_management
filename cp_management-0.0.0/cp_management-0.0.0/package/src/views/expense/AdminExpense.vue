<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus';
import {ElMessage} from 'element-plus';
//数据模型
const expenseData = ref([
    {
        "id": "",
        "userId":"",
        "project": "",
        "amount": "",
        "paymentStatus": "",
        "dueDate": "",
        "createTime": "",
        "updateTime": ""
    }
])
import {getCPTotalService,getAllExpenseService,deleteExpenseService,updateExpenseService,insertExpenseService} from '@/api/expense.js'
const pageNum =ref(1)
const pageSize =ref(15)
const total =ref(30)
const getCPTotal=async()=>{
  let result =await getCPTotalService();
  total.value=result.data
}
getCPTotal();
const onCurrentChange =async(num)=>{
    pageNum.value=num
    let result = await getAllExpenseService(pageNum.value);
    expenseData.value =result.data;
    
}
onCurrentChange(1);

const deleteExpense=(row)=>{
    //提示用户 确认框
    ElMessageBox.confirm(
        '你确认删除这条信息码？',
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
            let result=await deleteExpenseService(row.id)
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            getExpense();
        })
       
}
//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const expenseModel = ref({
        "userId":"",
        "project": "",
        "amount": "",
        "paymentStatus": "",
        "dueDate": ""
})
//调用添加接口函数

const insertExpense =async () =>{
    //调用接口
    let result=await insertExpenseService(expenseModel.value)
    ElMessage.success(result.message?result.message:"添加成功")
    getExpense();
    dialogVisible.value=false
}
//定义变量，控制标题的展示
const title =ref('')
//展示编辑弹窗
const showDialog = (row) =>{
    dialogVisible.value=true
    title.value='编辑缴费信息'
    //数据拷贝
    expenseModel.value.userId=row.userId;
    expenseModel.value.project=row.project;
    expenseModel.value.amount=row.amount;
    expenseModel.value.paymentStatus=row.paymentStatus;
    expenseModel.value.dueDate=row.dueDate;
    //扩展id，将来传递给后端
    expenseModel.value.id=row.id;
}
//更新小区信息函数
const updateExpense =async()=>{
    let result=await updateExpenseService(expenseModel.value)
    ElMessage.success(result.message?result.message:"修改成功")
    
    getExpense();
    dialogVisible.value=false
}
//清空模型的数据
const clearData = ()=>{
    expenseModel.value.userId='';
    expenseModel.value.project='';
    expenseModel.value.amount='';
    expenseModel.value.paymentStatus='';
    expenseModel.value.dueDate='';
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>费用记录管理</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true;title='添加缴费信息';clearData()">添加缴费信息</el-button>
                </div>
            </div>
        </template>
        <el-table :data="expenseData" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="用户ID" prop="userId"></el-table-column>
            <el-table-column label="缴费项目" prop="project"></el-table-column>
            <el-table-column label="费用金额" prop="amount"></el-table-column>
            <el-table-column label="缴费状态" prop="paymentStatus"></el-table-column>
            <el-table-column label="应缴费日期" prop="dueDate"></el-table-column>
            <el-table-column label="创建时间" prop="createTime"></el-table-column>
            <el-table-column label="更新时间" prop="updateTime"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary"  @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteExpense(row)"></el-button>
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
        <!-- 添加分类弹窗 -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="expenseModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="缴费项目" >
                    <el-input v-model="expenseModel.project" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="用户ID" >
                    <el-input v-model="expenseModel.userId" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="费用金额" >
                    <el-input v-model="expenseModel.amount" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="缴费状态" >
                    <el-input v-model="expenseModel.paymentStatus" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="应缴费日期">
                            <el-date-picker
                            v-model="expenseModel.dueDate"
                            type="date"
                            placeholder="请选择时间"
                            style="width: 100%"
                            format="YYYY-MM-DD"
                            value-format="YYYY-MM-DD"
                            />
                    </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title=='添加缴费信息'?insertExpense():updateExpense()"> 确认 </el-button>
                </span>
            </template>
        </el-dialog>
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