// pages/details/details.js
import instance from '../../utils/http'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    info: [
      { title: "我的钱包",url:"/pages/canteen/canteen"},
      { title: "联系客服" },
      { title: "帮助与反馈" },
      { title: "设置" }
    ],
    user: {
      money:0
    },
    token: ''
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.validateJwt();
    this.getUserInfo();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  async validateJwt() {
    this.setData({
      token:wx.getStorageSync('token')
    })
    await instance.get('validateToken/' + this.data.token)
  },

  async getUserInfo() {
    this.setData({
      username:wx.getStorageSync('username')
    })
    let u2 = await instance.get('getUserInfo/' + this.data.username)
    u2 = u2.data
    this.setData({
      user:u2
    })
    wx.setStorageSync('user', u2);
    
  },

  logout(){
    this.setData({
      user:{},
      username:''
    })
    wx.removeStorageSync('username');
    wx.removeStorageSync('token')

    wx.navigateTo({
      url: '/pages/login/login',
    })
  }
})