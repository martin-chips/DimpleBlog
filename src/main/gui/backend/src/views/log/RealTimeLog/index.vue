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

        var stompClient = null;

        if (stompClient == null) {
          this.res = "连接成功,正在等在";
          var socket = new SockJS('http://127.0.0.1:8080/websocket');
          stompClient = Stomp.over(socket);
          stompClient.connect({token: "kl"}, function (frame) {
            stompClient.subscribe('/topic/pullLogger', function (event) {
              var content = JSON.parse(event.body);
              var leverhtml = '';
              var className = '<span class="classnametext">' + content.className + '</span>';
              switch (content.level) {
                case 'INFO':
                  leverhtml = '<span class="infotext">' + content.level + '</span>';
                  break;
                case 'DEBUG':
                  leverhtml = '<span class="debugtext">' + content.level + '</span>';
                  break;
                case 'WARN':
                  leverhtml = '<span class="warntext">' + content.level + '</span>';
                  break;
                case 'ERROR':
                  leverhtml = '<span class="errortext">' + content.level + '</span>';
                  break;
              }
              $("#log-container div").append("<p class='logp'>" + content.timestamp + " " + leverhtml + " --- [" + content.threadName + "] " + className + " ：" + content.body + "</p>");
              if (content.exception != "") {
                $("#log-container div").append("<p class='logp'>" + content.exception + "</p>");
              }
              if (content.cause != "") {
                $("#log-container div").append("<p class='logp'>" + content.cause + "</p>");
              }
              $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
            }, {
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
