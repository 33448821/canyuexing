// 导入封装的 网络请求模块实例
import instance from '../../utils/http'
Page({
  /**
   * 页面的初始数据
   */
  data: {
    username:'',
    password:'',
    phone:'',
    re_password:'',
    re_en_password:'',
    current:1,
    codeText:'获取验证码',
    counting:false,
    registerData:{
      username:'',
      password:''
    }
  },

  async login(){
    let loginData = await instance.get('login/' + this.data.username + '/' + this.data.password);
    let code = loginData.code;
    if(code === 401){
      wx.showToast({
        title: '登录失败',
        icon:'error'
      })
    }else if( code === 200){
      wx.setStorageSync('token', loginData.data);
      wx.setStorageSync('username', this.data.username);
      wx.showToast({
        title: '登录成功~',
        icon:'success'
      })

      wx.reLaunch({
        url: '/pages/details/details'
      })
    }
  },
async register(){
  if(this.data.phone.length !== 11){
    console.log(this.data.phone)
    wx.showToast({
      title: '手机号输入错误',
      icon:'error',
      duration:1000
    })
    return
  }
  if(this.data.re_password.length < 6 ){
    console.log(this.data.phone)
    wx.showToast({
      title: '密码6位',
      icon:'error',
      duration:1000
    })
    return
  }
  if(this.data.re_en_password !== this.data.re_password){
    console.log(this.data.phone)
    wx.showToast({
      title: '确认密码有误',
      icon:'error',
      duration:1000
    })
    return
  }
  let res = await instance.post('register',{'username':this.data.phone,'password':this.data.re_password})
  console.log('register');
  if(res.code === 200){
    wx.showToast({
      title: '注册成功',
      icon:"success",
      duration:1000
    })
  }else{
    wx.showToast({
      title: '用户名重复',
      icon:"error",
      duration:1000
    })
  }
},
  inputUsernameChange(e){
    this.setData({
      username:e.detail.value
    })
  },
  inputPasswordChange(e){
    this.setData({
      password:e.detail.value
    })
  },
  inputPhoneChange(e){
    this.setData({
      phone:e.detail.value
    })
  },
  inputRePasswordChange(e){
    this.setData({
      re_password:e.detail.value
    })
  },
  inputEnPasswordChange(e){
    this.setData({
      re_en_password:e.detail.value
    })
  },

  // 登录/注册页面的切换监听
  click(e){
    let index = e.currentTarget.dataset.code;
    this.setData({
      current:index
    });
  },
  //获取验证码 
  getCode(){
    var that = this;
    if (!that.data.counting) {
      wx.showToast({
        title: '验证码已发送',
      })
      //开始倒计时60秒
      that.countDown(that, 60);
    } 
  },
  //倒计时60秒
  countDown(that,count){
    if (count == 0) {
      that.setData({
        codeText: '获取验证码',
        counting:false
      })
      return;
    }
    that.setData({
      counting:true,
      codeText: count + '秒后重新获取',
    })
    setTimeout(function(){
      count--;
      that.countDown(that, count);
    }, 1000);
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})
