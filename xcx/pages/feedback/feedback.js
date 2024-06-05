// pages/feedback/feedback.js
import http from '../../utils/http'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    dishNo:0,
    commentContent: '' // 评论内容
  },

  inputChange(event) {
    // 获取用户输入的文本内容
    const value = event.detail.value;
    // 更新数据中的评论内容
    this.setData({
      commentContent: value
    })
  },

  async submitComment() {
    if(this.data.commentContent){
      await http.post("addFeedback",{
        dishNo:this.data.dishNo,
        userId:wx.getStorageSync('user').id,
        comments:this.data.commentContent
      })
      wx.navigateBack();
      wx.showToast({
        title: '发表成功',
      })
  }else{
    wx.showToast({
      icon:'error',
      title: '请输入评论内容',
    })
  }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      dishNo:options.dishNo
    })
    console.log(options.dishNo); // 输出'value1'
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