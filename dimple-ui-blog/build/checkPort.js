/**
 * @desc 检查端口占用并返回一个可用端口
 */
const detect = require('detect-port')

module.exports = function checkPort(port = 8820) {
  return new Promise((resolve) => {
    detect(port, (err, _port) => {
      if (err) {
        console.log(err)
      }
      if (port == _port) {
        resolve(port)
      } else {
        resolve(_port)
        console.log(`port: ${port} was occupied, try port: ${_port}`)
      }
    })
  })
}
