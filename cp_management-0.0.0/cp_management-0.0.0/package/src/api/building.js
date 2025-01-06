import request from '@/utils/request.js'

//显示楼栋信息
export const getBuildingInfoService=(num)=>{
    return request.get('/neighborhood/getBuilding?num='+num)
}

//添加楼栋信息
export const insertBuildingInfoService=(buildingData)=>{
    return request.post('/neighborhood/insertBuilding',buildingData)
}

//删除楼栋信息
export const deleteBuildingService=(id)=>{
    return request.delete('/neighborhood/deleteBuilding?id='+id)
}
//更新楼栋信息
export const updateBuildingService=(buildingData)=>{
    return request.put('/neighborhood/updateBuilding',buildingData)
}

export const getTotalService=()=>{
    return request.get('/neighborhood/getBuildingTotal')
}
