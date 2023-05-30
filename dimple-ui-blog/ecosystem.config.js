module.exports = {
  apps: [
    {
      name: `Dimple's Blog`,
      cwd: './',
      script: 'server.js',
      max_memory_restart: '1024M',
      log_date_format: 'YYYY-MM-DD HH:mm Z',
      exec_mode: 'cluster',
      // 自定义应用程序的错误日志文件(错误日志文件)
      error_file: './logs/app-err.log',
      // 自定义应用程序日志文件(正常日志文件)
      out_file: './logs/app-out.log',
      // 设置追加日志而不是新建日志
      // 启用/禁用应用程序崩溃或退出时自动重启，默认为true, 发生异常的情况下自动重启
      autorestart: true,
      merge_logs: true,
      instances: '1',
      watch: 'false',
      env: {
        NODE_ENV: 'development'
      },
      env_production: {
        NODE_ENV: 'production' // 环境参数，当前指定为生产环境
      }
    }
  ]
}
