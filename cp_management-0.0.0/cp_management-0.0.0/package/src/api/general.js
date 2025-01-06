import request from '@/utils/request.js'
//显示公告
export const getInfoService=()=>{
    return request.get('/notice/getInfo')
}

//显示活动
export const getActivityService=()=>{
    return request.get('/notice/getActivity')
}

//柱状图数据
export const getOrderByMonthService=()=>{
    return request.get('/workorder/getOrderByMonth')
}

//卡片组数据
export const getGeneralDataService=()=>{
    return request.get('/admin/getGeneralData')
}

//饼状图数据
export const getFeeDataService=()=>{
    return request.get('/admin/getFeeData')
}

//折线图数据
export const getUserNumDataService=()=>{
    return request.get('/admin/getUserNumData')
}





