<template>
  <div :id="id"/>
</template>

<script>
import 'prismjs/themes/prism.css';
import '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import 'tui-color-picker/dist/tui-color-picker.css';
import '@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css';

import Editor from "@toast-ui/editor";

// 工具栏显示中文
import '@toast-ui/editor/dist/i18n/zh-cn.js';
import codeSyntaxHighlight
  from '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight-all.js';

import colorSyntax from '@toast-ui/editor-plugin-color-syntax';

import defaultOptions from './defaultOptions'

export default {
  name: 'MarkdownEditor',
  props: {
    markdown: {
      type: String,
      default: ''
    },
    id: {
      type: String,
      required: false,
      default() {
        return 'markdown-editor-' + +new Date() + ((Math.random() * 1000).toFixed(0) + '')
      }
    },
    options: {
      type: Object,
      default() {
        return defaultOptions
      }
    },
    mode: {
      type: String,
      default: 'markdown'
    },
    height: {
      type: String,
      required: false,
      default: '300px'
    },
    language: {
      type: String,
      required: false,
      default: 'zh-CN'
    }
  },
  data() {
    return {
      editor: null
    }
  },
  computed: {
    editorOptions() {
      const options = Object.assign({}, defaultOptions, this.options)
      options.initialEditType = this.mode
      options.height = this.height
      options.language = this.language
      return options
    }
  },
  watch: {
    markdown(newMarkdown, preMarkdown) {
      if (newMarkdown !== preMarkdown && newMarkdown !== this.editor.getMarkdown()) {
        this.editor.setMarkdown(newMarkdown)
      }
    },
    language(val) {
      this.destroyEditor()
      this.initEditor()
    },
    height(newValue) {
      this.editor.height(newValue)
    },
    mode(newValue) {
      this.editor.changeMode(newValue)
    }
  },
  mounted() {
    this.initEditor()
  },
  destroyed() {
    this.destroyEditor()
  },
  methods: {
    initEditor() {
      this.editor = new Editor({
        el: document.getElementById(this.id),
        ...this.editorOptions,
        plugins: [[codeSyntaxHighlight, {highlighter: Prism}], colorSyntax]
      });
      if (this.markdown) {
        this.editor.setMarkdown(this.markdown)
      }
      this.editor.on('change', () => {
        this.$emit('input', this.editor.getMarkdown())
      });
      this.uploadImg();
    },
    uploadImg() {
      this.editor.removeHook('addImageBlobHook');
      this.editor.on('addImageBlobHook', (_file, callback) => {
        const file = new FormData();
        file.append('file', _file);
        callback("https://avatars.githubusercontent.com/u/33685170?s=40&v=4", file.name)
        // const result = upload.uploadImg(file);
        // result.then(({code, msg, url}) => {
        //   if (code === 0) {
        //     this.$message.success(msg);
        //     callback(url, file.name)
        //   }
        // })
      })
    },
    destroyEditor() {
      if (!this.editor) return
      this.editor.off('change')
      this.editor.destroy()
    }
  }
}
</script>
