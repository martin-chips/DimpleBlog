export default {
  minHeight: '300px',
  previewStyle: 'vertical',  // Markdown Editor的预览样式（标签，垂直）
  useCommandShortcut: true,  // 是否使用键盘快捷键执行命令
  useDefaultHTMLSanitizer: true,
  usageStatistics: false,  // 发送主机名到谷歌分析
  hideModeSwitch: false,  // 隐藏模式开关选项卡栏
  // 工具栏配置
  toolbarItems: [
    ['heading', 'bold', 'italic', 'strike'],
    ['hr', 'quote'],
    ['ul', 'ol', 'task', 'indent', 'outdent'],
    ['table', 'image', 'link'],
    ['code', 'codeblock'],
  ]
}
