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
    created() {
      this.openSocket()
    },
    methods: {
      openSocket() {
        if (this.stompClient == null) {
          this.res = "<div style='color: #18d035;font-size: 14px'>通道连接成功,静默等待....</div>"
          // this.$refs['logContainerDiv'].append();
          // 建立连接对象
          let socket = new SockJS('http://127.0.0.1:8080/websocket?token=kl');
          // 获取STOMP子协议的客户端对象
          this.stompClient = Stomp.over(socket);
          this.stompClient.connect({token: 'kl'}, () => {
            this.stompClient.subscribe('/topic/pullLogger', (event) => {
                let content = JSON.parse(event.body);
                let leverhtml = '';
                let className = "<span style='color: #229379'>" + content.className + "</span>";
                switch (content.level) {
                  case 'INFO':
                    leverhtml = "<span style='color: #90ad2b'>" + content.level + "</span>";
                    break;
                  case 'DEBUG':
                    leverhtml = "<span style='color: #A8C023'>" + content.level + "</span>";
                    break;
                  case 'WARN':
                    leverhtml = "<span style='color: #fffa1c'>" + content.level + "</span>";
                    break;
                  case 'ERROR':
                    leverhtml = "<span style='color: #e3270e'>" + content.level + "</span>";
                    break;
                }
                this.res += "<div style='color: #18d035;font-size: 14px'>" + content.timestamp + " " + leverhtml + " --- [" + content.threadName + "] " + className + " ：" + content.body + "</div>"
                // this.$refs['logContainerDiv'].append(content.timestamp + " " + leverhtml + " --- [" + content.threadName + "] " + className + " ：" + content.body + "<br/>");
                if (content.exception != "") {
                  this.res += "<div>" + content.exception + "</div>"
                  // this.$refs['logContainerDiv'].append();
                }
                if (content.cause != "") {
                  this.res += "<div>" + content.cause + "</div>"
                  // this.$refs['logContainerDiv'].append(content.cause);
                }
                // this.$refs['logContainer'].scrollTo(this.$refs['logContainerDiv'].height() - this.$refs['logContainer'].height());
              },
              {
                token: "kltoen"
              });
          });
        }
      },
      closeSocket() {
        if (this.stompClient != null) {
          this.stompClient.disconnect();
          this.stompClient = null;
        }
      }
    }
  }
</script>

<style scoped>

</style>
