<template>
  <div class="components-container">
    <div class="editor-container">
      <markdown-editor v-model="content"/>
    </div>

    <el-button style="margin-top:80px;" type="primary" icon="el-icon-document" @click="getHtml">
      Get HTML
    </el-button>
    <div v-html="html"/>
  </div>
</template>

<script>
import MarkdownEditor from '@/components/MarkdownEditor'

export default {
  name: 'MarkdownDemo',
  components: {MarkdownEditor},
  data() {
    return {
      content: '',
      html: '',
    }
  },
  computed: {
  },
  methods: {
    getHtml() {
      this.html = this.$refs.markdownEditor.getHtml()
      console.log(this.html)
    }
  }
}
</script>

<style scoped>
.editor-container {
  margin-bottom: 30px;
}
</style>
