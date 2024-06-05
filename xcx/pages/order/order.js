// pages/order/order.js
import http from "../../utils/http"
Page({
  /**
   * 页面的初始数据
   */
  data: {
    // 真机调试时请更换本地址
    imgURL: 'http://localhost:8080/images/',
    user: {},
    username: "",
    orderData: [],
    detailList: [],
    showUI: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      'user': wx.getStorageSync('user'),
      username: wx.getStorageSync('username')
    })
  },

  async getOrderDetail(orderID) {
    let oid = orderID.currentTarget.dataset.oid;
    let res = await http.get('orderDetail/' + oid);
    let resData = res.data
    this.setData({
      detailList: resData
    })
    console.log(this.data.detailList);

    this.setData({
      showUI: true
    })
  },

  closeUI(){
    this.setData({
      showUI: false
    })
  },

  goToComments(envent) {
    let dishNo = envent.currentTarget.dataset.item.dishNo
    this.setData({
      showUI: false
    }
    )

    wx.navigateTo({
      url: '/pages/feedback/feedback?dishNo=' + dishNo
    })

  },

  async getOrderList() {
    let od = await http.get("userOrderList/" + this.data.username);
    this.setData({
      "orderData": od.data
    })
    console.log(this.data.orderData);
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
    this.getOrderList();
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

  }
})