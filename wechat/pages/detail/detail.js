const app = getApp()
Page(
  {
    data:{
      book:{name:"7th",price:"12",detail:"ok",image:"../assets/7th.jpg"},
      name:"7th",
      price:12,
      detail:"ok",
      image:"../assets/7th.jpg"
    },
    onLoad: function () {
      var that = this;
      const eventChannel=this.getOpenerEventChannel();
      eventChannel.on('acceptDataFromOpenerPage',function(data){
        console.log("next");
        const tid=data.book;
        console.log(data.book);
        that.setData({book:tid});
      })
    },
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
    }
  }
)