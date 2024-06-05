import http from '../../utils/http'
Page({
  data: {
    // 真机调试时请更换本地址
    imgURL: 'http://localhost:8080/images/',

    tabs: [{ typename: "招牌热销", typeno: "1" },
    { typename: "热卖主食", typeno: "2" },
    { typename: "甜点饮品", typeno: "3" },
    { typename: "搭配小吃", typeno: "4" },
    { typename: "单人套餐", typeno: "5" },
    { typename: "下午茶", typeno: "6" }
    ],

    tabsList: [{
      price: 10.1, description: '太好吃了吧', img: "../../image/kaoya.png", typeno: "1", dishno: 1, dishname: "芜湖红皮烤鸭1"
    },
    {
      price: 10.4, description: '好吃到流油', img: "../../image/kaoya.png", typeno: "2", dishno: 2, dishname: "芜湖红皮烤鸭2"
    }
      ,
    {
      price: 10.4, description: '好吃到流油', img: "../../image/kaoya.png", typeno: "3", dishno: 3, dishname: "芜湖红皮烤鸭3"
    }
      ,
    {
      price: 10.4, description: '好吃到流油', img: "../../image/kaoya.png", typeno: "4", dishno: 4, dishname: "芜湖红皮烤鸭4"
    }
      ,
    {
      price: 10.4, description: '好吃到流油', img: "../../image/kaoya.png", typeno: "5", dishno: 5, dishname: "芜湖红皮烤鸭5"
    }
      ,
    {
      price: 10.4, description: '好吃到流油', img: "../../image/kaoya.png", typeno: "5", dishno: 6, dishname: "芜湖红皮烤鸭6"
    }
    ],
    user: {},
    showMask: false,
    indexId: 0,
    toTitle: "title-0",
    scrollTop: 0,
    top: [],
    showFeedbackMask:false,//评论
    totalPrice: 0, //选中商品总价格
    totalNum: 0, //选中商品数量
    cartList: [], //选中商品列表
    // 购物车动画
    animationData: {},
    animationMask: {},
    maskVisual: "hidden",
    maskFlag: true,
    totalPrice: 0, // 总价钱
    totalNum: 0, // 总件数
    orderInfo: {
      orderTabs: [],
      totalNum: 0,
      totalPrice: 0,
      username: '',
      feedbacks:''
    }
  },

  //评论蒙版
  changeFeedbackMask(){
    let currentStatus = !this.data.showFeedbackMask
    this.setData({
      showFeedbackMask:currentStatus
    })
  },

  // 展示评论
async showDishFeedback(event){
  let dishNo = event.currentTarget.dataset.param.dishno;
  let commentList = await http.get("getFeedback/" + dishNo)
  commentList = commentList.data;
  this.setData({
    showFeedbackMask:true,
    feedbacks:commentList
  })

  console.log(this.data.feedbacks)
},

  // 清空已选择的商品
  cleanDish(){
    let tabDish = this.data.tabsList;
    for(let i = 0; i < tabDish.length; i++){
      if(tabDish[i].quantity > 0){
        tabDish[i].quantity = 0;
      }
    }

    this.setData({
      tabsList:tabDish,
      totalNum:0,
      totalPrice:0,
      showMask:false
    })
  },

  // 提交订单,支付
  async submitOrder() {
    let orderArr = [];

    for (let i = 0; i < this.data.tabsList.length; i++) {
      if (this.data.tabsList[i].quantity !== 0 && this.data.tabsList[i].quantity !== undefined && this.data.tabsList[i].quantity !== null) {
        let ordItem = {
          dishno: this.data.tabsList[i].dishno || 0,
          quantity: this.data.tabsList[i].quantity || 0
        };
        orderArr.push(ordItem);
      }
    }

    this.data.orderInfo.orderTabs = orderArr;
    this.data.orderInfo.totalNum = this.data.totalNum;
    this.data.orderInfo.totalPrice = this.data.totalPrice;
    this.data.orderInfo.username = wx.getStorageSync('username');

    let res = await http.post('orderDish', this.data.orderInfo);

    if (res.code === 200) {
      wx.setStorageSync('orderMoney', this.data.totalPrice);
      wx.navigateTo({
        url: '../pay/pay',
      });
      // 完成支付，清除下单的数据
      this.cleanDish();
    } else {
      wx.showToast({
        title: res.msg,
        icon: 'error',
        duration: 1000,
        mask: true
      })
    }
  },


  // 减少数量，统计总价
  subCount: function (e) {
    let no = e.currentTarget.dataset.item.dishno;
    // 获取当前页面数据
    let selectedTab = this.data.tabsList;
    // 查找 dishNo 数据
    let dishNo = selectedTab.find(item => item.dishno === no);
    if (dishNo) {
      // 如果找到了 dishNo 的数据，则将判断是否为0
      if (dishNo.quantity <= 0) {
        wx.showToast({
          title: '数量不能再减啦~',
          icon: 'error',
          duration: 2000
        })
        return // 数量小于0返回，不能继续减
      }
      dishNo.quantity -= 1;
    }
    // 更新页面数据
    this.setData({
      tabsList: selectedTab
    });
    this.calculateTotal();
  },
  calculateTotal: function () {
    let totalNum = 0;
    let totalPrice = 0;
    this.data.tabsList.forEach(item => {
      totalNum += item.quantity;
      totalPrice += item.price * item.quantity;
    });
    this.setData({
      totalNum: totalNum,
      totalPrice: totalPrice.toFixed(2)
    });
  },
  // 增加数量，统计总价
  addCount: function (e) {
    let no = e.currentTarget.dataset.item.dishno;
    // 获取当前页面数据
    let selectedTab = this.data.tabsList;
    // 查找 dishNo 数据
    let dishNo = selectedTab.find(item => item.dishno === no);
    if (dishNo) {
      // 如果找到了 dishNo 的数据，则判断其数量是否大于99
      if (dishNo.quantity >= 99) {
        wx.showToast({
          title: '下单数量超出限制',
          icon: 'error',
          duration: 2000
        })
        return //限制增加的数量要小于99
      }
      dishNo.quantity += 1;
    }
    // 更新页面数据
    this.setData({
      tabsList: selectedTab
    });
    this.calculateTotal();
  },
  calculateTotal: function () {
    let totalNum = 0;
    let totalPrice = 0;
    this.data.tabsList.forEach(item => {
      totalNum += item.quantity;
      totalPrice += item.price * item.quantity;
    });
    this.setData({
      totalNum: totalNum,
      totalPrice: totalPrice.toFixed(2)
    });
  },
  // 处理和支付订单页面
  closeMask: function () {
    this.setData({
      showMask: false
    })
  },
  // 左侧点击事件
  jumpIndex(e) {
    let index = e.currentTarget.dataset.menuindex;
    let typeno = e.currentTarget.dataset.typeno;
    let that = this
    that.setData({
      indexId: index,
      toTitle: "title-" + typeno
    });
  },
  scrollToLeft(res) {
    // console.log("scrollToLeft-res:" + JSON.stringify(res) + JSON.stringify(this.data.top));
    this.setData({
      scrollTop: res.detail.scrollTop
    })
    var length = this.data.top.length;
    for (var i = 0; i < this.data.top.length; i++) {
      if (this.data.top[i] - this.data.top[0] <= this.data.scrollTop && (i < length - 1 && this.data.top[i + 1] - this.data.top[0] > this.data.scrollTop)) {
        if (this.data.indexId != i) {
          this.setData({
            indexId: i,
          });
        }
      }
    }
  },
  onLoad: async function (options) {
    var that = this;
    wx.showLoading({
      mask: true,
      title: '加载中…',
    })
    //获取分类
    // await GetFoodType(that)
    //获取菜品
    // await GetFoodCook(that)
    wx.hideLoading()
    //设置高度，左右滚动
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          winHeight: res.windowHeight - 100
        });
        var top2 = new Array();
        for (var i = 0; i < that.data.tabs.length; i++) {
          wx.createSelectorQuery().select('#view-' + that.data.tabs[i].typeno).boundingClientRect(function (rect) {
            var isTop = Number(rect.top);
            top2.push(isTop);
          }).exec();
        }
        that.setData({
          top: top2
        });
      }
    });
  },

  async getDishType() {
    let distType = await http.get('dishType');
    this.setData({
      tabs: distType.data
    });
    console.log(distType)
  },

  // 获取图片 URL 的函数
  getImgUrl(list) {
    list.forEach(element => {
      // 使用 replaceImgUrl 函数增加前缀
      element.img = this.data.imgURL + element.img;
    });
    this.setData({
      tabsList: list
    })
  },

  async getDishList() {
    const dishData = await http.get('dish');
    const dishList = dishData.data;
    this.getImgUrl(dishList);
  },

  onReady() {
    this.getDishList();
    this.getDishType();
  },

  onShow: function (options) {
    this.setData({
      "user": wx.getStorageSync('user')
    })
  },
  // 购物车增加数量
  addCart: function (e) {
    var id = e.currentTarget.dataset.id;
    var arr = wx.getStorageSync('cart') || [];
    var f = false;
    for (var i in this.data.tabsList) { // 遍历菜单找到被点击的菜品，数量加1
      if (this.data.tabsList[i].dishno == id) {
        this.data.tabsList[i].quantity += 1;
        if (arr.length > 0) {
          for (var j in arr) { // 遍历购物车找到被点击的菜品，数量加1
            if (arr[j].dishno == id) {
              arr[j].quantity += 1;
              f = true;
              try {
                wx.setStorageSync('cart', arr)
              } catch (e) {
                console.log(e)
              }
              break;
            }
          }
          if (!f) {
            arr.push(this.data.tabsList[i]);
          }
        } else {
          arr.push(this.data.tabsList[i]);
        }
        try {
          wx.setStorageSync('cart', arr)
        } catch (e) {
          console.log(e)
        }
        break;
      }
    }
    this.setData({
      cartList: arr,
      tabsList: this.data.tabsList
    })
    this.getTotalPrice();
  },
  // 购物车减少数量
  delCart: function (e) {
    var id = e.currentTarget.dataset.id;
    var arr = wx.getStorageSync('cart') || [];
    for (var i in this.data.tabsList) {
      if (this.data.tabsList[i].dishno == id) {
        this.data.tabsList[i].quantity -= 1;
        if (this.data.tabsList[i].quantity <= 0) {
          this.data.tabsList[i].quantity = 0;
        }
        if (arr.length > 0) {
          for (var j in arr) {
            if (arr[j].dishno == id) {
              arr[j].quantity -= 1;
              if (arr[j].quantity <= 0) {
                this.removeByValue(arr, id)
              }
              if (arr.length <= 0) {
                this.setData({
                  tabsList: this.data.tabsList,
                  cartList: [],
                  totalNum: 0,
                  totalPrice: 0,
                })
                this.cascadeDismiss()
              }
              try {
                wx.setStorageSync('cart', arr)
              } catch (e) {
                console.log(e)
              }
            }
          }
        }
      }
    }
    this.setData({
      cartList: arr,
      tabsList: this.data.tabsList
    })
    this.getTotalPrice();
  },
  // 定义根据id删除数组的方法
  removeByValue: function (array, val) {
    for (var i = 0; i < array.length; i++) {
      if (array[i].f_Cooks_Id == val) {
        array.splice(i, 1);
        break;
      }
    }
  },
  // 获取购物车总价、总数
  getTotalPrice: function () {
    var cartList = this.data.cartList; // 获取购物车列表
    var totalP = 0;
    var totalN = 0
    for (var i in cartList) { // 循环列表得到每个数据
      totalP += cartList[i].quantity * cartList[i].description; // 所有价格加起来     
      totalN += cartList[i].quantity
    }
    this.setData({ // 最后赋值到data中渲染到页面
      cartList: cartList,
      totalNum: totalN,
      totalPrice: totalP.toFixed(2)
    });
  },
  // 清空购物车
  cleanList: function (e) {
    for (var t in this.data.tabs) {
      for (var j in this.data.tabsList) {
        this.data.tabsList[j].quantity = 0;
      }
    }
    try {
      wx.setStorageSync('cart', "")
    } catch (e) {
      console.log(e)
    }
    this.setData({
      tabsList: this.data.tabsList,
      cartList: [],
      cartFlag: false,
      totalNum: 0,
      totalPrice: 0,
    })
    this.cascadeDismiss()
  },
  //删除购物车单项
  deleteOne: function (e) {
    var id = e.currentTarget.dataset.id;
    var index = e.currentTarget.dataset.index;
    var arr = wx.getStorageSync('cart')
    for (var i in this.data.tabsList) {
      if (this.data.tabsList[i].dishno == id) {
        this.data.tabsList[i].quantity = 0;
      }
    }
    arr.splice(index, 1);
    if (arr.length <= 0) {
      this.setData({
        tabsList: this.data.tabsList,
        cartList: [],
        cartFlag: false,
        totalNum: 0,
        totalPrice: 0,
      })
      this.cascadeDismiss()
    }
    try {
      wx.setStorageSync('cart', arr)
    } catch (e) {
      console.log(e)
    }
    this.setData({
      cartList: arr,
      tabsList: this.data.tabsList
    })
    this.getTotalPrice()
  },
  // 打开购物车方法
  cascadePopup: function () {
    var that = this;
    // 购物车打开动画
    var animation = wx.createAnimation({
      duration: 200,
      timingFunction: 'ease-in-out',
      delay: 0
    });
    that.animation = animation;
    animation.translate(0, -285).step();
    that.setData({
      animationData: that.animation.export(),
    });
    // 遮罩渐变动画
    var animationMask = wx.createAnimation({
      duration: 200,
      timingFunction: 'linear',
    });
    that.animationMask = animationMask;
    animationMask.opacity(0.8).step();
    that.setData({
      animationMask: that.animationMask.export(),
      maskVisual: "show",
      maskFlag: false,
    });
  },
  // 关闭购物车方法
  cascadeDismiss: function () {
    var that = this
    // 购物车关闭动画
    that.animation.translate(0, 285).step();
    that.setData({
      animationData: that.animation.export()
    });
    // 遮罩渐变动画
    that.animationMask.opacity(0).step();
    that.setData({
      animationMask: that.animationMask.export(),
    });
    // 隐藏遮罩层
    that.setData({
      maskVisual: "hidden",
      maskFlag: true
    });
  },

  // 提交订单
  gotoOrder: function () {
    this.setData({
      showMask: true
    })
  },
  //查看菜品详情
  toDetail: function (e) {
    var id = e.currentTarget.dataset.id;
    var goodDetail = [];
    for (var i = 0; i < this.data.tabsList.length; i++) {
      if (this.data.tabsList[i].f_Cooks_Id == id) {
        goodDetail.push(this.data.tabsList[i]);
      }
    }
    wx.navigateTo({
      url: '../../category/goodDetail/goodDetail?goodDetail=' + JSON.stringify(goodDetail),
    })
  },
  //搜索菜品
  Search: function (e) {
    var searchkey = e.detail.value;
    console.log(searchkey);
  }
})
//获取商品信息
function GetFoodCook(_this) {
  var that = _this
  return new Promise((resove, reject) => {
    //获取分类
    let data = {}
    let header = {}
    http.request(config.GetFoodCook_WXList, data, 'POST', header).then(function (res) {
      var e = res
      if (e.meta.Code == 200) {
        that.setData({
          tabsList: e.data.foodCookInfo == null ? [] : e.data.foodCookInfo
        })
        resove(true);
      } else {
        wx.showToast({
          title: res.Msg,
          duration: 2000,
          icon: "none",
          mask: true
        })
        reject(false)
      }
    }).catch((res) => {
      wx.showToast({
        title: res.Msg,
        duration: 2000,
        icon: "none",
        mask: true
      })
      reject(false)
    })
  })
}
//获取商品类别
function GetFoodType(_this) {
  var that = _this
  return new Promise((resove, reject) => {
    let data = {
      pagenum: 1,
      pagesize: 100
    }
    let header = {}
    http.request(config.GetFoodTypeList, data, 'POST', header).then(function (res) {
      var e = res
      if (e.meta.Code == 200) {
        that.setData({
          tabs: e.data.foodTypeInfo == null ? [] : e.data.foodTypeInfo
        })
        resove(true);
      } else {
        wx.showToast({
          title: res.Msg,
          duration: 2000,
          icon: "none",
          mask: true
        })
        reject(false)
      }
    }).catch((res) => {
      wx.showToast({
        title: res.Msg,
        duration: 2000,
        icon: "none",
        mask: true
      })
      reject(false)
    })
  })
}
