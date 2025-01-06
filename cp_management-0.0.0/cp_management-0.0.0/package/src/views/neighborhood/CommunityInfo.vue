<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus';
import {ElMessage} from 'element-plus';
//数据模型
const communityData = ref([
    {
        "id": "",
        "name": "",
        "address": "",
        "area": "",
        "totalBuildings": "",
        "totalUnits": "",
        "developer": "",
        "createTime": "",
        "updateTime": ""
    }
])
import {getTotalService,getCommunityInfoService,deleteCommunityService,insertCommunityInfoService,updateCommunityService} from '@/api/community.js'
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
    let result = await getCommunityInfoService(pageNum.value);
    communityData.value =result.data;
    
}
onCurrentChange(1);

const deleteCommunity=(row)=>{
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
            let result=await deleteCommunityService(row.id)
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            getCommunity();
        })
       
}



//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const communityModel = ref({
        "name": "",
        "address": "",
        "area": "",
        "totalBuildings": "",
        "totalUnits": "",
        "developer": ""
})
//调用添加接口函数

const insertCommunity =async () =>{
    //调用接口
    let result=await insertCommunityInfoService(communityModel.value)
    ElMessage.success(result.message?result.message:"添加成功")
    getCommunity();
    dialogVisible.value=false
}
//定义变量，控制标题的展示
const title =ref('')
//展示编辑弹窗
const showDialog = (row) =>{
    dialogVisible.value=true
    title.value='编辑小区信息'
    //数据拷贝
    communityModel.value.name=row.name;
    communityModel.value.address=row.address;
    communityModel.value.area=row.area;
    communityModel.value.totalBuildings=row.totalBuildings;
    communityModel.value.totalUnits=row.totalUnits;
    communityModel.value.developer=row.developer;
    //扩展id，将来传递给后端
    communityModel.value.id=row.id;
}
//更新小区信息函数
const updateCommunity =async()=>{
    let result=await updateCommunityService(communityModel.value)
    ElMessage.success(result.message?result.message:"修改成功")
    getCommunity();
    dialogVisible.value=false
}
//清空模型的数据
const clearData = ()=>{
    communityModel.value.name='';
    communityModel.value.address='';
    communityModel.value.area='';
    communityModel.value.totalBuildings='';
    communityModel.value.totalUnits='';
    communityModel.value.developer='';
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>小区信息管理</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true;title='添加小区信息';clearData()">添加小区</el-button>
                </div>
            </div>
        </template>
        <el-table :data="communityData" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="小区名称" prop="name"></el-table-column>
            <el-table-column label="小区地址" prop="address"></el-table-column>
            <el-table-column label="占地面积" prop="area"></el-table-column>
            <el-table-column label="总楼栋数" prop="totalBuildings"></el-table-column>
            <el-table-column label="总户数" prop="totalUnits"></el-table-column>
            <el-table-column label="开发商名称" prop="developer"></el-table-column>
            <el-table-column label="创建时间" prop="createTime"></el-table-column>
            <el-table-column label="更新时间" prop="updateTime"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary"  @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteCommunity(row)"></el-button>
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
            <el-form :model="communityModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="小区名称" >
                    <el-input v-model="communityModel.name" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="小区地址" >
                    <el-input v-model="communityModel.address" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="占地面积" >
                    <el-input v-model="communityModel.area" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="总楼栋数" >
                    <el-input v-model="communityModel.totalBuildings" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="总户数" >
                    <el-input v-model="communityModel.totalUnits" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="开发商名称">
                    <el-input v-model="communityModel.developer" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title=='添加小区信息'?insertCommunity():updateCommunity()"> 确认 </el-button>
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