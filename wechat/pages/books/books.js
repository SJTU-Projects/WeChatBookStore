const app = getApp()
// pages/books/books.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    books:[{name:"coc",image:"https://img.yzcdn.cn/vant/cat.jpeg"},{name:"coc",image:"https://img.yzcdn.cn/vant/cat.jpeg"}],
    carouselImgUrls: [
      "../../assets/7th.jpg",
      "../../assets/coc.jpg",
      "../../assets/csapp.jpg",
      "../../assets/frozen.jpg"
    ],
    searchValue:"",
    show: false,
    book: null,
    active:0,
    id:0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    const eventChannel=this.getOpenerEventChannel();
    eventChannel.on('acceptDataFromOpenerPage',function(data){
      console.log("next");
      const tid=data.id-0;
      console.log(data.id);
      that.setData({id:tid});
    })
    if (!app.globalData.userLogged){
      wx.navigateTo({
        url: '../index/index'
      })
    }else{
      wx.request({
        url: 'http://localhost:8080/managebook',
        method: "POST",
        data: {
         way:"getbooks"
        },
        header: {
          'content-type': 'application/json', // 默认值
          'cookie': wx.getStorageSync("sessionid") //cookie
        },
        success(res) {
          console.log(res);
          // var base64Image =  res.data[0].image; // 后台返回的base64数据
          // var imgData = base64Image.replace(/[\r\n]/g, '') // 将回车换行换为空字符''
          //res.data[0].image=imgData;
          that.setData({
            books: res.data,
            // image:imgData,
          })
        }
      });
    }
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
  //点击书籍
  bindViewTap: function (event) {
    wx.navigateTo({
      url: '../detail/detail'
      //url:'../detail/detail'
      ,success:function(res){
        res.eventChannel.emit('acceptDataFromOpenerPage',{book:event.currentTarget.dataset.book})
      }
    })
    //this.setData({ show: true, book: event.currentTarget.dataset.book});
    //这里不采用遮罩层，而是利用这里的数据来向新页面发送信息。——这样还可以少跑一次后台？我需要在books加载的时候把book的所有信息都写进去才是。（其实也差不多，就多了一个dtail）
    //console.log(event.currentTarget.dataset.book);
  },
  //搜索框
  onChange(e) {
    this.setData({
      searchvalue: e.detail
    });
  },

  onSearch() {
    wx.showToast({
      title: '搜索功能还没做，偷个懒！',
      icon: 'none',
      duration: 2000
    })
  },
  //点击遮罩层
  onClickHide() {
    this.setData({ show: false });
  },
  //点击坐下图标
  onClickIcon() {
    wx.showToast({
      title: '客服没钱请！购物车还没做！',
      icon: 'none',
      duration: 2000
    })
  },
  //点击右下按钮
  onClickButton() {
    wx.showToast({
      title: '别买了！！！',
      icon: 'none',
      duration: 2000
    })
  },
  //点击底边栏
  onChange(event) {
    // event.detail 的值为当前选中项的索引
    this.setData({ active: event.detail });
  }
})