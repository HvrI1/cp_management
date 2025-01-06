import request from '@/utils/request.js'
//当前用户提交工单
export const provideOrderService=(orderInfoData)=>{
    const params=new URLSearchParams()
    for(let key in orderInfoData){
        params.append(key,orderInfoData[key]);
    }
    return request.post('/workorder/provideOrder',params)
}


//显示当前用户工单进度
export const getOrderInfoService=()=>{
    //const tokenStore = useTokenStore();
    //在pinia中定义的响应式数据，都不需要.value
    //return request.get('/workorder/getOrderInfo',{headers:{'Authorization':tokenStore.token}})
    return request.get('/workorder/getOrderInfo')
}

//当前用户工单历史记录
export const getOrderHistoryService=(num)=>{
    return request.get('/workorder/getOrderHistory?num='+num)
}

//工单历史记录删除
export const deleteOrderHistoryService=(id)=>{
    return request.delete('/workorder/deleteOrderHistory?id='+id)
}

//显示待处理的工单
export const getDealOrderService=(num)=>{
    return request.get('/workorder/dealOrder?num='+num)
}

//处理工单
export const updateStatusService=(id)=>{
    return request.put('/workorder/updateStatus?id='+id)
}

//查询所有的工单记录
export const getALLLimitService=(num)=>{
    return request.get('/workorder/getAllOrderInfo?num='+num)
}

export const getUserLimitService=(num)=>{
    return request.get('/workorder/getUserLimit?num='+num)
}

export const getTotalService=()=>{
    return request.get('/workorder/getTotal')
}

export const getUserTotalService=()=>{
    return request.get('/workorder/getUserTotal')
}

export const getAdminTotalService=()=>{
    return request.get('/workorder/getAdminTotal')
}

