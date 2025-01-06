<script setup>
import { User, Lock ,Avatar} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus';
//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false)
const isAdmin = ref(false)
const isProvide = ref(false)
const isLogin = ref(true)

//定义数据模型
const registerDate = ref({
    username:'',
    password:'',
    rePassword:'',
    role:''
})

const adminDate = ref({
    username:'',
    password:''
})
//校验确认密码的函数
const checkRePassword =(rule,value,callback)=>{
    if(value===''){
        callback(new Error('请再次确认密码'))
    }else if(value!==registerDate.value.password){
        callback(new Error('请确保两次密码输入一致'))
    }else{
        callback()
    }
}
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
//定义表单校验规则
const rules ={
    username:[
        {required:true,message:'请输入用户名',trigger:'blur'},
        {min:5,max:16,message:'长度为5~16位',trigger:'blur'}
    ],
    password:[
        {required:true,message:'请输入密码',trigger:'blur'},
        {min:5,max:16,message:'长度为5~16位',trigger:'blur'}
    ],
    rePassword:[
        {validator:checkRePassword,trigger:'blur'}
    ],
    role:[
        {validator:checkRole,trigger:'blur'}
    ]
}
//调用后台接口，完成注册
import {userRegisterService,userLoginService,adminLoginService,updateUserInfoService} from '@/api/user.js'
import {useUserRegisterInfoStore} from '@/stores/registerInfo.js'
const RegisterInfo =useUserRegisterInfoStore();
const register = async()=>{
    //registerDate是一个响应式对象，如果要获取值，必须.value才能获取到
    let result =await userRegisterService(registerDate.value);//能走完这个语句说明业务状态码一定是0，所以下面只需alart成功即可
    ElMessage.success(result.msg?result.msg:'注册成功')
    RegisterInfo.setRegisterInfo(result.data)
}
//绑定数据，复用注册表单的数据模型
//表单数据校验
//登陆函数
import {useTokenStore} from '@/stores/token.js' 
import {useRouter} from 'vue-router'
const router=useRouter()
const tokenStore=useTokenStore()
const login =async()=>{
    let result= await userLoginService(registerDate.value);//能走完这个语句说明业务状态码一定是0，所以下面只需alart成功即可
    ElMessage.success(result.msg?result.msg:'登录成功')
    //把得到的token存储到pinia中
    tokenStore.setToken(result.data)
    //跳转到首页 借助路由完成跳转
    router.push('/')
}
//定义函数清空数据模型的数据
const clearRegisterDate=()=>{
    registerDate.value={
        username:'',
        password:'',
        rePassword:'',
        role:''
    }
}
//管理员登录函数
const adminLogin=async()=>{
    let result=await adminLoginService(adminDate.value);
    ElMessage.success(result.msg?result.msg:'登录成功')
    //把得到的token存储到pinia中
    tokenStore.setToken(result.data)
    //跳转到首页 借助路由完成跳转
    router.push('/a')
}
//定义用户填写基本资料的数据模型
const PorvideInfoDate = ref({
    name:'',
    communityId:'',
    houseNum:'',
    buildingNum:'',
    phone:'',
    sex:'',
    username:'',
    id:'',
    role:''
})

//获取小区名字和id函数
const regions =ref([])
import {getCommunityNameAndIdService} from '@/api/community.js'
const getCommunityNameAndId = async () => {
  let result = await getCommunityNameAndIdService();
  
  // 清空 regions 数组
  regions.value = [];

  // 遍历获取的数据，为每个小区创建一个对象并添加到 regions 数组中
  for (let i = 0; i < result.data.length; i++) {
    regions.value.push({
      label: result.data[i].name,
      id: result.data[i].id
    });
  }
}

getCommunityNameAndId();
//用户点击所在楼栋函数
const buildingNum =ref(1);
const buildingChange =(value)=>{
    PorvideInfoDate.value.buildingNum = value.toString();
    // console.log("楼栋号"+PorvideInfoDate.value.buildingId)
}
//用户点击所在房屋函数
const houseNum =ref(1);
const houseChange =(value)=>{
    PorvideInfoDate.value.houseNum = value.toString();
    // console.log("房屋号"+PorvideInfoDate.value.houseId)
}
//用户注册后填写基本信息点击提交按钮
const  PorvideInfo =async()=>{

    PorvideInfoDate.value.id=RegisterInfo.registerInfo.id
    PorvideInfoDate.value.username=RegisterInfo.registerInfo.username
    PorvideInfoDate.value.role=RegisterInfo.registerInfo.role
    // console.log(registerInfoData)
    let result = await updateUserInfoService(PorvideInfoDate.value);
    ElMessage.success(result.message?ElMessage.result:'提交成功');
    // console.log(PorvideInfoDate)
}

</script>

<template>
    <el-row class="login-page">
        <el-col :span="12" class="bg"></el-col>
        <el-col :span="6" :offset="3" class="form">
            <!-- 注册表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerDate" :rules="rules">
                <el-form-item>
                    <h1>注册</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerDate.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码"
                        v-model="registerDate.password"></el-input>
                </el-form-item>
                <el-form-item prop="rePassword">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码"
                        v-model="registerDate.rePassword"></el-input>
                </el-form-item>
                <el-form-item prop="role">
                    <el-input :prefix-icon="Avatar" placeholder="请输入角色:业主或租客" v-model="registerDate.role"></el-input>
                </el-form-item>
                <!-- 注册按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="isRegister=false;isProvide = true;register()">
                        注册
                    </el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isLogin=true;isRegister = false;clearRegisterDate()">
                        ← 返回
                    </el-link>
                </el-form-item>
            </el-form>


            <!-- 注册后填写信息表单 -->
            <el-form ref="form" size="large" autocomplete="off"  v-if="isProvide" :model="PorvideInfoDate"  :rules="rules">
                <el-form-item>
                    <h1>填写基本资料</h1>
                </el-form-item>
                <el-form-item prop="name">
                    <el-input :prefix-icon="User" placeholder="请输入姓名" v-model="PorvideInfoDate.name"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="PorvideInfoDate.sex">
                        <el-radio value="男">男</el-radio>
                        <el-radio value="女">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="phone">
                    <el-input :prefix-icon="User" placeholder="请输入联系方式" v-model="PorvideInfoDate.phone"></el-input>
                </el-form-item>

                <el-form-item label="所在小区">
                    <el-select v-model="PorvideInfoDate.communityId" placeholder="请选择所在小区">
                        <el-option v-for="region in regions" :label="region.label" :key="region.id" :value="region.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所在楼栋号">
                    <el-input-number v-model="buildingNum" :min="1" :max="10" @change="buildingChange(buildingNum)" />
                </el-form-item>
                <el-form-item label="所在房屋号">
                    <el-input-number v-model="houseNum" :min="1" :max="10" @change="houseChange(houseNum)" />
                </el-form-item>
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="isLogin=true;isProvide = false;PorvideInfo();clearRegisterDate()">
                        提交
                    </el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isProvide = false;isRegister=true;clearRegisterDate()">
                        ← 返回
                    </el-link>
                </el-form-item>
            </el-form>





            <!-- 管理员登录 -->
            <el-form ref="form" size="large" autocomplete="off" v-if="isAdmin" :model="adminDate">
                <el-form-item>
                    <h1>管理员登录</h1>
                </el-form-item>
                <el-form-item>
                    <el-input :prefix-icon="User" placeholder="请输入管理员账户" v-model="adminDate.username"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码"
                        v-model="adminDate.password"></el-input>
                </el-form-item>
                <!-- 登录按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="adminLogin()">登录</el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isLogin=true;;isAdmin = false;clearRegisterDate()">
                        ← 返回
                    </el-link>
                </el-form-item>
            </el-form>


            

            <!-- 登录表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-if="isLogin" :model="registerDate" :rules="rules">
                <el-form-item>
                    <h1>登录</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerDate.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码"
                        v-model="registerDate.password"></el-input>
                </el-form-item>
                <el-form-item class="flex">
                    <div class="flex">
                        <el-checkbox>记住我</el-checkbox>
                        <el-link type="primary" :underline="false">忘记密码？</el-link>
                    </div>
                </el-form-item>
                <!-- 登录按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <div class="zhuce fl">
                        <el-link type="info" :underline="false" @click="isLogin=false;isRegister = true;clearRegisterDate()">
                            注册 →
                        </el-link>
                    </div>
                    <div class="admindenglu fr">
                        <el-link type="info" :underline="false" @click="isLogin=false;isAdmin = true;clearRegisterDate()">
                            管理员登录 →
                        </el-link>
                    </div>


                </el-form-item>
            </el-form>




            
        </el-col>
    </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
    height: 100vh;
    background-color: #fff;

    .bg {
        background:url('@/assets/login_bg.jpg') no-repeat center / cover;
        border-radius: 0 10px 10px 0;
    }

    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;

        .title {
            margin: 0 auto;
        }

        .button {
            width: 100%;
        }

        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
}
.fr{
float:right;
}
.fl{
float:left;
}
.zhuce{
    width: 390px;
    height: 40px;
}
.admindenglu{
    width: 90px;
    height: 40px;
}
</style>