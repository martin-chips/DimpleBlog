<template>
  <div class="tags">
    <layout title="标签" cover="/img/cover/tags.jpg">
      <tags-iterator :tags="tags"></tags-iterator>
    </layout>
  </div>
</template>
<script>
import api from '@/api/'
import tagsIterator from '@/views/components/tags-iterator'
import {Enum} from "../../api/visitor";

export default {
  name: 'tags',
  metaInfo() {
    return {
      title: `标签  - Dimple's Blog`,
      meta: [
        {
          name: 'description',
          content: '用nodejs开发的豆瓣爬虫 douban-spider-v，同步我的观影记录'
        },
        {
          name: 'keywords',
          content: '豆瓣电影,豆瓣爬虫,豆瓣爬虫,douban spider'
        }
      ]
    }
  },
  components: {tagsIterator},
  data() {
    return {
      tags: []
    }
  },
  async asyncData({store, route, isServer, _req}) {
    const tagRes = await api.listAsyncTag(_req)
    await api.saveVisitLog(Enum.LIST_TAG, "", "/app/tags", tagRes.code, _req);
    if (tagRes.code === 200) return {tags: tagRes.rows, total: tagRes.total}
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
</style>
