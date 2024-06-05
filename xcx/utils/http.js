import WxRequest from 'mina-request'

// 对 WxRequest 进行实例化
const instance = new WxRequest({
  baseURL: 'http://localhost:8080/api/',
  timeout: 15000,
  isLoading: false // 不使用默认 loading
})

// 添加请求拦截器
instance.interceptors.request = (config) => {
  // 在请求发送之前做点什么……

  // 新增请求头
  let token = wx.getStorageSync('token');
  config.header['Authorization'] = 'Bearer ' + token
  return config
}

// 添加响应拦截器
instance.interceptors.response = (response) => {
  // 对响应数据做点什么

  // 从 response 中解构 isSuccess
  const { isSuccess, data } = response

  // 如果 isSuccess 为 false，说明执行了 fail 回调函数
  // 这时候就说明网络异常，需要给用户提示网络异常
  if (!isSuccess) {
    wx.showToast({
      title: '网络异常，请稍后重试 !'
    })
    return response
  }
    
  // 对业务状态码进行
  // ...
  if(data.code === 408){
    console.log("未登录")
    wx.navigateTo({
      url: '/pages/login/login',
    })
  }
  // 将服务器响应的数据返回
  return data
}


export default instance
