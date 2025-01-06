import { defineStore } from 'pinia';
import {ref} from 'vue';
const useUserInfoStore = defineStore('userInfo',()=>{
    const info = ref('')

    //2.定义一个函数，修改token的值
    const setInfo = (newInfo)=>{
        info.value = newInfo
    }
    //3.函数，移除token的值
    const removeInfo = ()=>{
        info.value = ''
    }

    return {
        info,setInfo,removeInfo
    }



},{persist:true})

export default useUserInfoStore;