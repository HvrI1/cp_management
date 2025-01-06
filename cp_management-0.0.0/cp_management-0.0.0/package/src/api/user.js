//导入request.js请求工具
import request from '@/utils/request.js'

//提供调用注册接口的函数
export const userRegisterService=(registerDate)=>{
    //借助于UrlSerachParams完成传递
    const params=new URLSearchParams()
    for(let key in registerDate){
        params.append(key,registerDate[key]);
    }
    return request.post('/user/register',params);
}

//提供调用登录接口的函数
export const userLoginService=(adminLoginDate)=>{
    //借助于UrlSerachParams完成传递
    const params=new URLSearchParams()
    for(let key in adminLoginDate){
        params.append(key,adminLoginDate[key]);
    }

    return request.post('/user/login',params)
}

//提供调用管理员登录接口的函数
export const adminLoginService=(loginDate)=>{
    //借助于UrlSerachParams完成传递
    const params=new URLSearchParams()
    for(let key in loginDate){
        params.append(key,loginDate[key]);
    }

    return request.post('/admin/login',params)
}

//获取用户详细信息
export const userInfoService=()=>{
    return request.get('/user/getUserInfo')
}

//修改个人信息
export const userInfoUpdateService=(userInfoData)=>{
    return request.put('/user/update',userInfoData)
}

//修改密码
export const userRePwdService=(rePwdData)=>{
    //借助于UrlSerachParams完成传递
    const params=new URLSearchParams()
    for(let key in rePwdData){
        params.append(key,rePwdData[key]);
    }
    return request.patch('/user/updatePwd',params)
}

//获取全部的用户信息
export const getAllUserInfoService=(num)=>{
    return request.get('/user/getAllUserInfo?num='+num)
}
//获取全部的用户信息的条数
export const getTotalService=()=>{
    return request.get('/user/getTotal')
}
//修改当前这条信息
export const updateUserService=(userData)=>{
    return request.put('/user/updateUser',userData)
}

//删除当前这条信息
export const deleteUserService=(id)=>{
    return request.delete('/user/deleteUser?id='+id)
}

//修改头像
export const userAvatarUpdateService=(avatarUrl)=>{
    const params =new URLSearchParams();
    params.append('avatarUrl',avatarUrl)
    return request.patch('/user/updateAvatar',params)
}

//注册后修改用户基本信息
export const updateUserInfoService=(PorvideInfoDate)=>{
    return request.put('/user/updateUserInfo',PorvideInfoDate)
}

