<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus';
import {ElMessage} from 'element-plus';
//数据模型
const buildingData = ref([
    {
        "id": "",
        "communityId": "",
        "communityName": "",
        "buildingNumber": "",
        "totalFloors": "",
        "totalHouseholds": "",
        "createTime": "",
        "updateTime": ""
    }
])
import {getTotalService,getBuildingInfoService,insertBuildingInfoService,deleteBuildingService,updateBuildingService} from '@/api/building.js'
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
    let result = await getBuildingInfoService(pageNum.value);
    buildingData.value =result.data;
    
}
onCurrentChange(1);

const deleteBuilding=(row)=>{
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
            let result=await deleteBuildingService(row.id)
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            getBuilding();
        })
       
}
//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const buildingModel = ref({
        "communityId": "",
        "buildingNumber": "",
        "totalFloors": "",
        "totalHouseholds": ""
})
// //调用添加接口函数

const insertBuilding =async () =>{
    //调用接口
    let result=await insertBuildingInfoService(buildingModel.value)
    ElMessage.success(result.message?result.message:"添加成功")
    getBuilding();
    dialogVisible.value=false
}
//定义变量，控制标题的展示
const title =ref('')
//展示编辑弹窗
const showDialog = (row) =>{
    dialogVisible.value=true
    title.value='编辑楼栋信息'
    //数据拷贝
    buildingModel.value.communityName=row.communityName;
    buildingModel.value.buildingNumber=row.buildingNumber;
    buildingModel.value.totalFloors=row.totalFloors;
    buildingModel.value.totalHouseholds=row.totalHouseholds;
    //扩展id，将来传递给后端
    buildingModel.value.id=row.id;
}
//更新小区信息函数
const updateBuilding =async()=>{
    let result=await updateBuildingService(buildingModel.value)
    ElMessage.success(result.message?result.message:"修改成功")
    getBuilding();
    dialogVisible.value=false
}
//清空模型的数据
const clearData = ()=>{
    buildingModel.value.communityId='';
    buildingModel.value.buildingNumber='';
    buildingModel.value.totalFloors='';
    buildingModel.value.totalHouseholds='';
}

//获取小区名字和id函数
const regions =ref([])
import {getCommunityNameAndIdService} from '@/api/community.js'
const getCommunityNameAndId = async () => {
  let result = await getCommunityNameAndIdService();
  
  // 清空 regions 数组
  regions.value = [];

  // 遍历获取的数据，为每个小区创建一个对象并添加到 regions 数组中
  for (let i = 0; i < result.data.length; i++) {
    regions.value.push({
      label: result.data[i].name,
      id: result.data[i].id
    });
  }
}
getCommunityNameAndId();
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>楼栋信息管理</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true;title='添加楼栋信息';clearData()">添加楼栋</el-button>
                </div>
            </div>
        </template>
        <el-table :data="buildingData" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="小区名称" prop="communityName"></el-table-column>
            <el-table-column label="楼栋号" prop="buildingNumber"></el-table-column>
            <el-table-column label="总楼层数" prop="totalFloors"></el-table-column>
            <el-table-column label="楼栋总户数" prop="totalHouseholds"></el-table-column>
            <el-table-column label="创建时间" prop="createTime"></el-table-column>
            <el-table-column label="更新时间" prop="updateTime"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary"  @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteBuilding(row)"></el-button>
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
            <el-form :model="buildingModel" label-width="100px" style="padding-right: 30px">
                <!-- <el-form-item label="小区名称" >
                    <el-input v-model="buildingModel.communityName" minlength="1" maxlength="10"></el-input>
                </el-form-item> -->       
                <el-form-item label="所在小区">
                <el-select v-model="buildingModel.communityId" placeholder="请选择所在小区">
                    <el-option v-for="region in regions" :label="region.label" :key="region.id" :value="region.id"></el-option>
                </el-select>
                </el-form-item>
                <el-form-item label="楼栋号" >
                    <el-input v-model="buildingModel.buildingNumber" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="总楼层数" >
                    <el-input v-model="buildingModel.totalFloors" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="楼栋总户数" >
                    <el-input v-model="buildingModel.totalHouseholds" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title=='添加楼栋信息'?insertBuilding():updateBuilding()"> 确认 </el-button>
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