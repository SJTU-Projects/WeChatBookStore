//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userName: "",
    password: "",
    errorMessage: "",
  },
  //用户名输入
  bindNameInput: function(event){
      this.setData({userName : event.detail.value})
  },
  //密码输入
  bindPasswordInput: function(event){
    this.setData({ password: event.detail.value })
  },
  //事件处理函数
  bindViewTap: function() {
    var that = this;
    wx.request({
      url: 'http://localhost:8080/Login', 
      method: "POST",
      mode: "cors",
      data: {
        username: this.data.userName,
        password: this.data.password
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        if(res.data[0] == "access"){
          app.globalData.userLogged = true;
          wx.setStorageSync("sessionid", res.header["Set-Cookie"]); //存储cookie
          that.setData({"errorMessage" : ""});
          console.log(res.data[1]);
          const id=res.data[1]-0;
          wx.navigateTo({
            url: '../books/books'
            //url:'../detail/detail'
            ,success:function(res){
              res.eventChannel.emit('acceptDataFromOpenerPage',{id:id})
            }
          })
        }else if(res.data[0] == "no user"){
          that.setData({"errorMessage" : "用户名错误"});
          //console.log(res.data[0]);
        }
        else if(res.data[0] == "wrong code"){
          that.setData({"errorMessage" : "密码错误"});
          //console.log(res.data[0]);
        }
      }
    }); 
  },
  onLoad: function () {
   },
   
})

