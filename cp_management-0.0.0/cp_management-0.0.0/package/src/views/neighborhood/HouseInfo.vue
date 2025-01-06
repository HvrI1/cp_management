<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus';
import {ElMessage} from 'element-plus';
//数据模型
const houseData = ref([
    {
        "id": "",
        "userId": "",
        "userName": "",
        "buildingId": "",
        "unitNumber": "",
        "floorNumber": "",
        "roomNumber": "",
        "liveTime": "",
        "createTime": "",
        "updateTime": ""
    }
])
import {getTotalService,getHouseInfoService,insertHouseInfoService,deleteHouseService,updateHouseService} from '@/api/house.js'
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
    let result = await getHouseInfoService(pageNum.value);
    houseData.value =result.data;
    
}
onCurrentChange(1);

const deleteHouse=(row)=>{
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
            let result=await deleteHouseService(row.id)
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            getHouse();
        })
       
}
//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const houseModel = ref({
        "id":"",
        "userName": "",
        "unitNumber": "",
        "floorNumber": "",
        "roomNumber": "",
        "liveTime": ""
})
// //调用添加接口函数

const insertHouse =async () =>{
    //调用接口
    let result=await insertHouseInfoService(houseModel.value)
    ElMessage.success(result.message?result.message:"添加成功")
    getHouse();
    dialogVisible.value=false
}
//定义变量，控制标题的展示
const title =ref('')
//展示编辑弹窗
const showDialog = (row) =>{
    dialogVisible.value=true
    title.value='编辑房屋信息'
    //数据拷贝
    houseModel.value.userName=row.userName;
    houseModel.value.unitNumber=row.unitNumber;
    houseModel.value.floorNumber=row.floorNumber;
    houseModel.value.roomNumber=row.roomNumber;
    houseModel.value.liveTime=row.liveTime;
    //扩展id，将来传递给后端
    houseModel.value.id=row.id;
}
//更新小区信息函数
const updateHouse =async()=>{
    let result=await updateHouseService(houseModel.value)
    ElMessage.success(result.message?result.message:"修改成功")
    
    getHouse();
    dialogVisible.value=false
}
//清空模型的数据
const clearData = ()=>{
    houseModel.value.userName='';
    houseModel.value.unitNumber='';
    houseModel.value.floorNumber='';
    houseModel.value.roomNumber='';
    houseModel.value.liveTime='';
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>房屋信息管理</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true;title='添加房屋信息';clearData()">添加房屋</el-button>
                </div>
            </div>
        </template>
        <el-table :data="houseData" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="用户名称" prop="userName"></el-table-column>
            <el-table-column label="单元号" prop="unitNumber"></el-table-column>
            <el-table-column label="楼层号" prop="floorNumber"></el-table-column>
            <el-table-column label="房间号" prop="roomNumber"></el-table-column>
            <el-table-column label="入住时间" prop="liveTime"></el-table-column>
            <el-table-column label="创建时间" prop="createTime"></el-table-column>
            <el-table-column label="更新时间" prop="updateTime"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary"  @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteHouse(row)"></el-button>
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
            <el-form :model="houseModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="用户名称" >
                    <el-input v-model="houseModel.userName" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="单元号" >
                    <el-input v-model="houseModel.unitNumber" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="楼层号" >
                    <el-input v-model="houseModel.floorNumber" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="房间号" >
                    <el-input v-model="houseModel.roomNumber" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="入住时间">
                            <el-date-picker
                            v-model="houseModel.liveTime"
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
                    <el-button type="primary" @click="title=='添加房屋信息'?insertHouse():updateHouse()"> 确认 </el-button>
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