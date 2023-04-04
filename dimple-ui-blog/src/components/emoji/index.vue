<template>
  <div class="emoji">
    <el-popover
      v-model="emojiShow"
      placement="bottom-start"
      width="400"
      trigger="click"
      content="这是一段内容"
    >
      <el-tabs type="card">
        <el-tab-pane v-for="(tab, index) in tabs" :key="index" :label="tab.label">
          <el-scrollbar>
            <div class="emoji__panel">
              <span v-for="(emoji, index) in tab.emojis" :key="index" @click="pick(emoji)">{{ emoji }}</span>
            </div>
          </el-scrollbar>
        </el-tab-pane>
      </el-tabs>
      <span slot="reference" class="emoji__trigger">😀</span>
    </el-popover>
  </div>
</template>
<script>
import emoji from "emojilib";

export default {
  name: 'emoji',
  data() {
    return {
      emojiShow: false,
      filter: [
        {
          label: '表情动作',
          key: ['face', 'fingers']
        },
        {
          label: '人物',
          key: ['man', 'people', 'human']
        },
        {
          label: '动物',
          key: 'animal'
        },
        {
          label: '其它',
          key: ['food', 'transportation', 'rocket']
        }
      ],
      tabs: []
    }
  },
  components: {},
  created() {
    this.filterEmoji()
  },
  methods: {
    pick(emoji) {
      this.emojiShow = false
      this.$emit('getEmoji', emoji)
    },
    filterEmoji() {
      this.tabs = this.filter.map((ef) => {
        const emojis = Object.keys(emoji).filter((key) => {
          if (Array.isArray(ef.key)) return ef.key.some((efk) => emoji[key].includes(efk))
          else return emoji[key].includes(ef.key)
        })
        return {
          emojis,
          label: ef.label
        }
      })
    }
  }
}
</script>
<style lang="scss">
.emoji {
  &__trigger {
    font-size: 18px;
    cursor: pointer;
  }
  &__panel {
    height: 230px;
    padding: 8px;
    span {
      display: inline-block;
      padding: 4px 6px;
      cursor: pointer;
      font-size: 18px;
    }
  }
}
</style>
