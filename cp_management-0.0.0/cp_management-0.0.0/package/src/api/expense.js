import request from '@/utils/request.js'



//删除当前缴费信息
export const deleteExpenseService=(id)=>{
    return request.delete('/expense/deleteExpense?id='+id)
}

//更新缴费状态为已缴费
export const updateStatusService=(id)=>{
    return request.put('/expense/updateStatus?id='+id)
}

//显示当前用户的费用记录
export const getUserExpenseService=(num)=>{
    return request.get('/expense/getUserExpense?num='+num)
}

//更新缴费信息
export const updateExpenseService=(expenseData)=>{
    return request.put('/expense/updateExpense',expenseData)
}

//查询所有缴费信息
export const getAllExpenseService=(num)=>{
    return request.get('/expense/getAllExpense?num='+num)
}

//插入新的更新信息
export const insertExpenseService=(expenseData)=>{
    return request.post('/expense/insertExpense',expenseData)
}

//用户端总条数
export const getTotalService=()=>{
    return request.get('/expense/getTotal')
}

//管理员端总条数
export const getCPTotalService=()=>{
    return request.get('/expense/getCPTotal')
}
