<script setup>
import { ref } from 'vue'
const rePwdInfo = ref({
    oldPwd: '',
    newPwd: '',
    rePwd: '',
})
const rules = {
    oldPwd: [
        { required: true, message: '请输入旧密码', trigger: 'blur' },
    ],
    newPwd: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
    ],
    rePwd: [
        { required: true, message: '请输入再次确认新密码', trigger: 'blur' },
    ]
}
//提供修改密码的函数
import {userRePwdService} from'@/api/user.js'
import { ElMessage } from 'element-plus';
import router from '@/router';
const route =router
const resetPwd =async ()=>{
    let result = await userRePwdService(rePwdInfo.value);
    ElMessage.success(result.message?result.message:'修改成功')

    router.push('/login')
}




</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>重置密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="rePwdInfo" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="旧密码" prop="oldPwd">
                        <el-input v-model="rePwdInfo.oldPwd" ></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="newPwd">
                        <el-input v-model="rePwdInfo.newPwd"></el-input>
                    </el-form-item>
                    <el-form-item label="再次确认" prop="rePwd">
                        <el-input v-model="rePwdInfo.rePwd"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="resetPwd()">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>