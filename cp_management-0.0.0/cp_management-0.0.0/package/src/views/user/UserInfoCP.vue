<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus';
import {ElMessage} from 'element-plus';
//数据模型
const userData = ref([
    {
        "id": "",
        "username":"",
        "role": "",
        "communityId": "",
        "buildingId": "",
        "houseId": "",
        "phone": "",
        "sex": "",
        "name": "",
        "remark": "",
        "email": "",
        "createTime": "",
        "updateTime": ""
    }
])
import {getTotalService,getAllUserInfoService,updateUserService,deleteUserService} from '@/api/user.js'
const pageNum =ref(1)
const pageSize =ref(15)
const total =ref(30)
const getTotal=async()=>{
  let result =await getTotalService();
  total.value=result.data
}
getTotal();
const onCurrentChange =async(num)=>{
    pageNum.value=num
    let result = await getAllUserInfoService(pageNum.value);
    userData.value =result.data;
}
onCurrentChange(1);

const deleteUser=(row)=>{
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
            let result=await deleteUserService(row.id)
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            getAllUserInfo();
        })
       
}
//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const userModel = ref({
        "username":"",
        "role": "",
        "communityId": "",
        "buildingId": "",
        "houseId": "",
        "phone": "",
        "sex": "",
        "name": "",
        "remark": "",
        "email": ""
})
//定义变量，控制标题的展示
const title =ref('')
//展示编辑弹窗
const showDialog = (row) =>{
    dialogVisible.value=true
    title.value='编辑用户信息'
    //数据拷贝
    userModel.value.username=row.username;
    userModel.value.role=row.role;
    userModel.value.communityId=row.communityId;
    userModel.value.buildingId=row.buildingId;
    userModel.value.houseId=row.houseId;
    userModel.value.phone=row.phone;
    userModel.value.sex=row.sex;
    userModel.value.name=row.name;
    userModel.value.remark=row.remark;
    userModel.value.email=row.email;
    //扩展id，将来传递给后端
    userModel.value.id=row.id;
}
//更新小区信息函数
const updateUser =async()=>{
    let result=await updateUserService(userModel.value)
    ElMessage.success(result.message?result.message:"修改成功")
    
    getAllUserInfo();
    dialogVisible.value=false
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>用户信息管理</span>
            </div>
        </template>
        <el-table :data="userData" style="width: 100%">
            <el-table-column label="序号" width="70" type="index"> </el-table-column>
            <el-table-column label="姓名" prop="name"></el-table-column>
            <el-table-column label="用户ID" prop="id"></el-table-column>
            <el-table-column label="性别" prop="sex"></el-table-column>
            <el-table-column label="账号" prop="username"></el-table-column>
            <el-table-column label="角色" prop="role"></el-table-column>
            <el-table-column label="所属小区" prop="communityId"></el-table-column>
            <el-table-column label="所属楼栋" prop="buildingId"></el-table-column>
            <el-table-column label="所属房屋" prop="houseId"></el-table-column>
            <el-table-column label="联系方式" prop="phone"></el-table-column>
            <el-table-column label="邮箱" prop="email"></el-table-column>
            <el-table-column label="备注" prop="remark"></el-table-column>
            <el-table-column label="创建时间" prop="createTime"></el-table-column>
            <el-table-column label="更新时间" prop="updateTime"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary"  @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteUser(row)"></el-button>
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
                <el-form-item label="姓名" >
                    <el-input v-model="userModel.name" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="性别" >
                    <el-input v-model="userModel.sex" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="账号" >
                    <el-input v-model="userModel.username" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="角色" >
                    <el-input v-model="userModel.role" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="所属小区" >
                    <el-input v-model="userModel.communityId" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="所属楼栋" >
                    <el-input v-model="userModel.buildingId" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="所属房屋" >
                    <el-input v-model="userModel.houseId" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="联系方式" >
                    <el-input v-model="userModel.phone" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" >
                    <el-input v-model="userModel.email" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="备注" >
                    <el-input v-model="userModel.remark" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="updateUser()"> 确认 </el-button>
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