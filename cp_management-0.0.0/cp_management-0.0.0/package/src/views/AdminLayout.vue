<script setup>
import {
    Management,
    Promotion,
    UserFilled,
    User,
    Crop,
    EditPen,
    SwitchButton,
    CaretBottom
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'

//调用函数，获取用户详细信息
// import { userInfoService } from '@/api/user';
// import useUserInfoStore from '@/stores/userInfo.js'
// const userInfoStore=useUserInfoStore();
// const getUserInfo = async ()=>{
//     let result=await userInfoService();
//     userInfoStore.setInfo(result.data);
// }
// getUserInfo();
import router from '@/router';
const clickQuit=()=>{
    router.push('/login')
}
</script>

<template>
    <el-container class="layout-container">
        <!-- 左侧菜单 -->
        <el-aside width="200px">
            <div class="el-aside__logo"></div>
            <el-menu active-text-color="#ffd04b" background-color="#232323"  text-color="#fff"
                router >
                <!-- 概况 -->
                <el-menu-item index="/adminGeneral">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>概况</span>
                </el-menu-item>
                <!-- 居民服务-->
                <el-sub-menu index="1">
                    <template #title>
                        <el-icon>
                            <UserFilled />
                        </el-icon>
                        <span>居民服务管理</span>
                    </template>
                    <el-menu-item index="/neighborhood/community">
                        <el-icon>
                            <User />
                        </el-icon>
                        <span>小区信息管理</span>
                    </el-menu-item>
                    <el-menu-item index="/neighborhood/building">
                        <el-icon>
                            <Crop />
                        </el-icon>
                        <span>楼栋信息管理</span>
                    </el-menu-item>
                    <el-menu-item index="/neighborhood/house">
                        <el-icon>
                            <EditPen />
                        </el-icon>
                        <span>房屋详情管理</span>
                    </el-menu-item>
                </el-sub-menu> 
                <!-- 维修工单 -->
                <el-sub-menu index="2">
                    <template #title>
                        <el-icon>
                            <UserFilled />
                        </el-icon>
                        <span>工单管理</span>
                    </template>
                    <el-menu-item index="/workorder/orderCP">
                        <el-icon>
                            <EditPen />
                        </el-icon>
                        <span>维修工单处理管理</span>
                    </el-menu-item>
                    <el-menu-item index="/workorder/historyCP">
                        <el-icon>
                            <EditPen />
                        </el-icon>
                        <span>工单历史记录管理</span>
                    </el-menu-item>
                </el-sub-menu>
                <!-- 费用管理 -->
                <el-menu-item index="/adminExpense">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>费用记录管理</span>
                </el-menu-item>
                <!-- 通知管理 -->
                <el-menu-item index="/notice">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>通知管理</span>
                </el-menu-item>
                <!-- 用户信息管理 -->
                <el-menu-item index="/userInfoCP">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>用户信息管理</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <!-- 右侧主区域 -->
        <el-container>
            <!-- 头部区域 -->
            <el-header>
                <div>管理员：<strong>admin</strong></div>
                <el-dropdown placement="bottom-end">
                    <span class="el-dropdown__box">
                        <el-avatar :src="avatar" />
                        <el-icon>
                            <CaretBottom />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="logout" :icon="SwitchButton"@click="clickQuit()">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-header>
            <!-- 中间区域 -->
            <el-main>
                <router-view></router-view>
            </el-main>
            <!-- 底部区域 -->
            <el-footer>基于springboot的小区物业管理系统 ©2024 Created by 王少楠 2022204010118</el-footer>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100vh;

    .el-aside {
        background-color: #232323;

        &__logo {
            height: 120px;
            background: url('@/assets/logo.png') no-repeat center / 180px auto;
        }

        .el-menu {
            border-right: none;
        }
    }

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
    }

    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
</style>