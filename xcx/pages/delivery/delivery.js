// pages/delivery/delivery.js
import http from '../../utils/http'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {
      username: '',
      name: '',
      phone: '',
      address: ''
    }
  },

  async editDeliveryInfo() {
    await http.put('editDeliveryInfo', this.data.user);
    wx.switchTab({
      url: '../details/details',
    })
  },

  handleInputName: function (e) {
    this.setData({
      "user.name": e.detail.value,
    });
  },

  handleInputPhone: function (e) {
    this.setData({
      "user.phone": e.detail.value,
    });
  },

  handleInputAddress: function (e) {
    this.setData({
      "user.address": e.detail.value,
    });

    console.log(this.data.user)
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({ 
      user: wx.getStorageSync('user') 
    })
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