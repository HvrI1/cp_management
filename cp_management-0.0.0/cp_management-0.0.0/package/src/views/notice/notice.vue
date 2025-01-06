<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus';
import {ElMessage} from 'element-plus';
//数据模型
const noticeData = ref([
    {
        "id": "",
        "date": "",
        "title": "",
        "content": "",
        "type": "",
        "createTime": "",
        "updateTime": ""
    }
])
import {getTotalService,getContService,insertNoticeService,updateNoticeService,deleteNoticeService} from '@/api/notice.js'
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
    let result = await getContService(pageNum.value);
    noticeData.value =result.data;
    
}
onCurrentChange(1);

const deleteNotice=(row)=>{
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
            let result=await deleteNoticeService(row.id)
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            getCont();
        })
       
}
//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const noticeModel = ref({
        "date": "",
        "title": "",
        "content": "",
        "type": ""
})
// //调用添加接口函数

const insertNotice =async () =>{
    //调用接口
    let result=await insertNoticeService(noticeModel.value)
    ElMessage.success(result.message?result.message:"添加成功")
    getCont();
    dialogVisible.value=false
}
//定义变量，控制标题的展示
const title =ref('')
//展示编辑弹窗
const showDialog = (row) =>{
    dialogVisible.value=true
    title.value='编辑通知信息'
    //数据拷贝
    noticeModel.value.date=row.date;
    noticeModel.value.title=row.title;
    noticeModel.value.content=row.content;
    noticeModel.value.type=row.type;
    //扩展id，将来传递给后端
    noticeModel.value.id=row.id;
}
//更新小区信息函数
const updateNotice =async()=>{
    let result=await updateNoticeService(noticeModel.value)
    ElMessage.success(result.message?result.message:"修改成功")
    getCont();
    dialogVisible.value=false
}
//清空模型的数据
const clearData = ()=>{
    noticeModel.value.date='';
    noticeModel.value.title='';
    noticeModel.value.content='';
    noticeModel.value.type='';
}
// const formatDate = (timestamp) => {
//     timestamp = noticeModel.value.date

//     timestamp.replace(/T/g,' ').replace(/Z/g,'')
//     noticeModel.value.date =timestamp
//   return  noticeModel.value.date
// }

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>通知信息管理</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true;title='添加通知信息';clearData()">添加通知</el-button>
                </div>
            </div>
        </template>
        <el-table :data="noticeData" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="时间" prop="date"></el-table-column>
            <el-table-column label="标题" prop="title"></el-table-column>
            <el-table-column label="内容" prop="content" :show-overflow-tooltip="true"></el-table-column>
            <el-table-column label="类型" prop="type"></el-table-column>
            <el-table-column label="创建时间" prop="createTime"></el-table-column>
            <el-table-column label="更新时间" prop="updateTime"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary"  @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteNotice(row)"></el-button>
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
       
        <!-- 添加通知弹窗 -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="noticeModel" label-width="100px" style="padding-right: 30px">
                    <el-form-item label="时间">
                            <el-date-picker
                            v-model="noticeModel.date"
                            type="date"
                            placeholder="请选择时间"
                            style="width: 100%"
                            format="YYYY-MM-DD"
                            value-format="YYYY-MM-DD"
                            />
                    </el-form-item>

                <el-form-item label="标题" >
                    <el-input v-model="noticeModel.title" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="内容" >
                    <el-input type="textarea" autosize  v-model="noticeModel.content" minlength="6" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="类型" >
                    <el-input v-model="noticeModel.type" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title=='添加通知信息'?insertNotice():updateNotice()"> 确认 </el-button>
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
.el-textarea__inner{
    min-height: 110px;
}


</style>