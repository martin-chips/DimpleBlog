<template>
  <div>
    {{res}}
  </div>
</template>

<script>
  import SockJS from 'sockjs-client';
  import Stomp from 'stompjs';

  export default {
    name: "index",
    data() {
      return {
        res: ""
      }
    },
    methods:{
      initWebSocket() {
        this.connection();
        let that= this;
        // 断开重连机制,尝试发送消息,捕获异常发生时重连
        this.timer = setInterval(() => {
          try {
            that.stompClient.send("test");
          } catch (err) {
            console.log("断线了: " + err);
            that.connection();
          }
        }, 5000);
      },
      connection() {
        // 建立连接对象
        let socket = new SockJS('http://127.0.0.1:8080/websocket');
        // 获取STOMP子协议的客户端对象
        this.stompClient = Stomp.over(socket);
        // 定义客户端的认证信息,按需求配置
        let headers = {
          Authorization:''
        }
        // 向服务器发起websocket连接
        this.stompClient.connect(headers,() => {
          this.stompClient.subscribe('topic/pullLogger', (msg) => { // 订阅服务端提供的某个topic
            console.log('广播成功')
            console.log(msg);  // msg.body存放的是服务端发送给我们的信息
          },headers);
          this.stompClient.send("/app/chat.addUser",
            headers,
            JSON.stringify({sender: '',chatType: 'JOIN'}),
          )   //用户加入接口
        }, (err) => {
          // 连接发生错误时的处理函数
          console.log('失败')
          console.log(err);
        });
      },    //连接 后台
      disconnect() {
        if (this.stompClient) {
          this.stompClient.disconnect();
        }
      },  // 断开连接
    },
    mounted(){
      this.initWebSocket();
    },
    beforeDestroy: function () {
      // 页面离开时断开连接,清除定时器
      this.disconnect();
      clearInterval(this.timer);
    }
  }
</script>

<style scoped>

</style>
