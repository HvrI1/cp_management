import request from '@/utils/request.js'
//显示公告
export const getContService=(num)=>{
    return request.get('/notice/getCont?num='+num)
}

//增
export const insertNoticeService=(noticeDate)=>{
    return request.post('/notice/insertNotice',noticeDate)
}

//改
export const updateNoticeService=(noticeDate)=>{
    return request.put('/notice/updateNotice',noticeDate)
}

//删
export const deleteNoticeService=(id)=>{
    return request.delete('/notice/deleteNotice?id='+id)
}

//公告信息分页
export const getLimitInfoService=(num)=>{
    return request.get('/notice/getLimitInfo?num='+num)
}
//活动信息分页
export const getLimitActivityService=(num)=>{
    return request.get('/notice/getLimitActivity?num='+num)
}
//公告信息总条数
export const getInfoTotalService=()=>{
    return request.get('/notice/getInfoTotal')
}
//活动信息总条数
export const getActivityTotalService=()=>{
    return request.get('/notice/getActivityTotal')
}

//通知总条数
export const getTotalService=()=>{
    return request.get('/notice/getTotal')
}





// <div class="pageLimit">
//     <el-pagination  
//         layout="prev, pager, next" 
//         :total="total" 
//         v-model:current-page="pageNum"
//         v-model:page-size="pageSize"
//         @current-change="onCurrentChange"
//         style="margin-top: 20px;justify-content: flex-end;"
//     />
// </div>


// 活动信息分页条数据模型
// const pageNum =ref(1)
// const pageSize =ref(15)
// const total =ref(30)
// const getTotal=async()=>{
//   let result =await getTotalService();
//   total.value=result.data
// }
// getTotal();
// const onCurrentChange =async (num)=>{
//   pageNum.value=num
//   let result=await getLimitService(pageNum.value);
//   activityData.value=result.data
// }
// onCurrentChange(1)






