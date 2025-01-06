import {createRouter,createWebHistory} from 'vue-router'

//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import UserInfoVue from '@/views/user/UserInfo.vue'
import UserAvatarVue from '@/views/user/UserAvatar.vue'
import UserResetPasswordVue from '@/views/user/UserResetPassword.vue'
import ExpenseVue from '@/views/expense/Expense.vue'
import GeneralVue from '@/views/general/General.vue'
import CommunityInfoVue from '@/views/neighborhood/CommunityInfo.vue'
import BuildingInfoVue from '@/views/neighborhood/BuildingInfo.vue'
import HouseInfoVue from '@/views/neighborhood/HouseInfo.vue'
import ProvideOrderVue from '@/views/workorder/ProvideOrder.vue'
import ProgressOrderVue from '@/views/workorder/ProgressOrder.vue'
import HistoryOrderVue from '@/views/workorder/HistoryOrder.vue'

import AdminLayoutVue from '@/views/AdminLayout.vue'
import AdminExpenseVue from '@/views/expense/AdminExpense.vue'
import AdminGeneralVue from '@/views/general/AdminGeneral.vue'
import userInfoCPVue from '@/views/user/UserInfoCP.vue'
import HistoryCPVue from '@/views/workorder/HistoryCP.vue'
import OrderCPVue from '@/views/workorder/OrderCP.vue'
import NoticeVue from '@/views/notice/notice.vue'
//定义路由关系

const routes = [
    {path:'/login',component:LoginVue},
    {path:'/',
    redirect:'/general',
    component:LayoutVue,
        children:[
            {path:'/user/info',component:UserInfoVue},
            {path:'/user/avatar',component:UserAvatarVue},
            {path:'/user/resetpassword',component:UserResetPasswordVue},
            {path:'/expense',component:ExpenseVue},
            {path:'/general',component:GeneralVue},
            {path:'/workorder/provide',component:ProvideOrderVue},
            {path:'/workorder/progress',component:ProgressOrderVue},
            {path:'/workorder/history',component:HistoryOrderVue}
        ]
    },
    {path:'/a',
    redirect:'/adminGeneral',
    component:AdminLayoutVue,
        children:[
            {path:'/neighborhood/community',component:CommunityInfoVue},
            {path:'/neighborhood/building',component:BuildingInfoVue},
            {path:'/neighborhood/house',component:HouseInfoVue},
            {path:'/notice',component:NoticeVue},
            {path:'/adminExpense',component:AdminExpenseVue},
            {path:'/adminGeneral',component:AdminGeneralVue},
            {path:'/userInfoCP',component:userInfoCPVue},
            {path:'/workorder/historyCP',component:HistoryCPVue},
            {path:'/workorder/orderCP',component:OrderCPVue},
        ]
    }


]

//创建路由器
const router =createRouter({
    history:createWebHistory(),
    routes:routes
})

//导出路由
export default router