import request from '@/utils/request.js'

//显示房屋信息
export const getHouseInfoService=(num)=>{
    return request.get('/neighborhood/getHouse?num='+num)
}

//添加房屋信息
export const insertHouseInfoService=(houseData)=>{
    return request.post('/neighborhood/insertHouse',houseData)
}

//删除房屋信息
export const deleteHouseService=(id)=>{
    return request.delete('/neighborhood/deleteHouse?id='+id)
}
//更新房屋信息
export const updateHouseService=(houseData)=>{
    return request.put('/neighborhood/updateHouse',houseData)
}

export const getTotalService=()=>{
    return request.get('/neighborhood/getHouseTotal')
}