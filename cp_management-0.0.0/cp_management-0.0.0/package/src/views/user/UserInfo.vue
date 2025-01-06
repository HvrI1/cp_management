<script setup>
import { ref } from 'vue'
import useUserInfoStore from '@/stores/userInfo.js'
const userInfoStore=useUserInfoStore();

const userInfo = ref({...userInfoStore.info})

//校验角色的函数
const checkRole =(rule,value,callback)=>{
    if(value===''){
        callback(new Error('请输入角色：业主或租客'))
    }else if(value!='业主'&&value!='租客'){
        callback(new Error('输入内容必须是业主或租客'))
    }else{
        callback()
    }
}
const rules = {
    name: [
        { required: true, message: '请输入真实姓名', trigger: 'blur' },
        {
            pattern: /^\S{2,5}$/,
            message: '昵称必须是2-10位的非空字符串',
            trigger: 'blur'
        }
    ],
    role:[
        { required: true, message: '请输入角色：业主或租客', trigger: 'blur' },
        {validator:checkRole,trigger:'blur'}
    ],
    email: [
        { required: true, message: '请输入用户邮箱', trigger: 'blur' },
        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
    ],
    phone: [
        { required: true, message: '请输入联系方式', trigger: 'blur' },
        {
            pattern: /^\S{6,11}$/,
            message: '联系方式必须是2-10位的非空字符串',
            trigger: 'blur'
        }
    ]
}
//修改个人信息
import { userInfoUpdateService } from '@/api/user.js';
import { ElMessage } from 'element-plus';
const updateUserInfo =async()=>{
    let result=await userInfoUpdateService(userInfo.value);
    ElMessage.success(result.message?result.message:'修改成功')

    //修改pinia中的个人信息
    userInfoStore.setInfo(userInfo.value)
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>基本资料</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="userInfo" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="登录账号">
                        <el-input v-model="userInfo.username" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="真实姓名" prop="name">
                        <el-input v-model="userInfo.name"></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio-group v-model="userInfo.sex">
                            <el-radio value="男">男</el-radio>
                            <el-radio value="女">女</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="用户角色" prop="role">
                        <el-input v-model="userInfo.role"></el-input>
                    </el-form-item>
                    <el-form-item label="联系方式" prop="phone">
                        <el-input v-model="userInfo.phone"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                        <el-input v-model="userInfo.email"></el-input>
                    </el-form-item>
                    <el-form-item label="备注" prop="remark">
                        <el-input v-model="userInfo.remark"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updateUserInfo()">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>