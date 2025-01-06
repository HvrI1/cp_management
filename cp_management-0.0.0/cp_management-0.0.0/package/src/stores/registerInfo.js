import { defineStore } from 'pinia';
import {ref} from 'vue';
export  const useUserRegisterInfoStore = defineStore('userRegisterInfo',()=>{
    const registerInfo = ref('')

    //2.定义一个函数，修改token的值
    const setRegisterInfo= (newRegisterInfo)=>{
        registerInfo.value = newRegisterInfo
    }
    //3.函数，移除token的值
    const removeRegisterInfo = ()=>{
        info.value = ''
    }

    return {
        registerInfo,setRegisterInfo,removeRegisterInfo
    }



},{persist:true})

export default useUserRegisterInfoStore;