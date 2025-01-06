import request from '@/utils/request.js'

//显示小区信息
export const getCommunityInfoService=(num)=>{
    return request.get('/neighborhood/getCommunity?num='+num)
}

export const getCommunityNameAndIdService=()=>{
    return request.get('/neighborhood/getCommunityNameAndId')
}

//添加小区信息
export const insertCommunityInfoService=(communityData)=>{
    return request.post('/neighborhood/insertCommunity',communityData)
}

//删除小区信息
export const deleteCommunityService=(id)=>{
    return request.delete('/neighborhood/deleteCommunity?id='+id)
}
//更新小区信息
export const updateCommunityService=(communityData)=>{
    return request.put('/neighborhood/updateCommunity',communityData)
}

export const getTotalService=()=>{
    return request.get('/neighborhood/getCommunityTotal')
}

// <el-form-item label="所在小区">
// <el-select v-model="PorvideInfoDate.communityId" placeholder="请选择所在小区">
//     <el-option v-for="region in regions" :label="region.label" :key="region.id" :value="region.id"></el-option>
// </el-select>
// </el-form-item>

// //获取小区名字和id函数
// const regions =ref([])
// import {getCommunityNameAndIdService} from '@/api/community.js'
// const getCommunityNameAndId = async () => {
//   let result = await getCommunityNameAndIdService();
  
//   // 清空 regions 数组
//   regions.value = [];

//   // 遍历获取的数据，为每个小区创建一个对象并添加到 regions 数组中
//   for (let i = 0; i < result.data.length; i++) {
//     regions.value.push({
//       label: result.data[i].name,
//       id: result.data[i].id
//     });
//   }
// }
// getCommunityNameAndId();