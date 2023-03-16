<template>
    <div class="search">
        <div class="search__body">
            <div class="search__input">
                <el-input v-model="keyword" size="medium" placeholder="请输入关键字回车搜索"
                          @keyup.enter.native="search">
                    <i slot="suffix" class="el-input__icon el-icon-search" @click="search"></i>
                </el-input>
            </div>
            <splitLine></splitLine>
            <el-scrollbar>
                <div class="search__content">
                    <el-skeleton v-if="loading" :rows="6" animated/>
                    <ul class="search__list">
                        <li v-for="(article, index) in articles" :key="index">
                            <div class="list-item">
                                <div class="list-item__title" v-html="article.title" @click="goto(article)"></div>
                                <div class="list-item__content" v-html="article.summary"></div>
                            </div>
                        </li>
                    </ul>
                    <div class="search__empty" v-if="showEmptyResult">Oops~ 暂未找到关键词为”{{ keyword }}“的文章</div>
                </div>
            </el-scrollbar>
            <el-pagination
                    class="search__page"
                    v-if="total>articles.length"
                    :total="total"
                    layout="prev, pager, next"
                    :page-size="pageSize"
                    @current-change="currentChange"
            ></el-pagination>
        </div>
    </div>
</template>
<script>
import splitLine from "@/components/splitLine/";

export default {
    name: 'search',
    props: {},
    data() {
        return {
            loading: false,
            keyword: '',
            searched: false,
            total: 0,
            pageSize: 10,
            pageNum: 1,
            articles: []
        }
    },
    components: {splitLine},
    watch: {
        keyword() {
            this.searched = false
        }
    },
    computed: {
        showEmptyResult() {
            return !this.articles.length && this.searched
        }
    },
    methods: {
        async search() {
            if (!this.keyword.trim()) {
                this.searched = false
                this.articles = []
                this.$message({
                    type: 'warning',
                    message: '请输入关键词搜索'
                })
                return
            }
            this.loading = true;
            const searchRes = await this.$api.searchArticle({
                searchValue: this.keyword.trim(),
                pageNum: this.pageNum,
                pageSize: this.pageSize
            }).catch(e => {
                console.log(e)
            });
            if (searchRes && searchRes.code === 200) {
                var result = searchRes.rows;
                for (let resultElement of result) {
                    const reg = new RegExp(this.keyword.trim(), 'ig')
                    resultElement.title = resultElement.title.replace(reg, (val) => {
                        return `<span style="color:red">${val}</span>`
                    });
                    resultElement.summary = resultElement.summary.replace(reg, (val) => {
                        return `<span style="color:red">${val}</span>`
                    });
                }
                this.articles = result;

                this.searched = true;
                this.total = searchRes.total;
                this.loading = false;
            } else {

                this.loading = false;
            }

        },
        currentChange(val) {
            this.pageNum = val
            this.search()
        },
        goto(article) {
            this.$emit('hasJumped')
            this.$router.push({name: 'articleDetail', params: {id: article.id}})
        }
    }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';

.search {
  &__body {
  }

  &__page {
    margin-top: 10px;
    text-align: center;
  }

  &__input {
    .el-input__inner {
      border-radius: 40px;
    }

    .el-icon-search {
      cursor: pointer;
      font-size: 16px;
    }
  }

  &__content {
    width: 100%;
    max-height: 200px;
    padding-left: 20px;
  }

  &__list {
    .list-item {
      &__title {
        position: relative;
        padding: 8px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        transition: all ease 0.2s;
      }

      &__title:hover {
        @include themeify() {
          color: themed('color-ele-primary');
        }
      }

      &__title:before {
        position: absolute;
        left: -2px;
        top: 50%;
        transform: translate(-100%, -50%);
        content: '';
        width: 8px;
        height: 8px;
        border: 3px solid;
        border-radius: 50%;
        font-weight: normal;
        transition: all ease 0.2s;
        @include themeify() {
          border-color: themed('color-ele-primary');
        }
      }

      &__title:hover:before {
        @include themeify() {
          border-color: themed('color-ele-danger');
        }
      }

      &__content {
        padding: 0 8px;
        line-height: 1.5;
      }
    }

    .search-keyword {
      @include themeify() {
        color: themed('color-ele-danger');
      }
    }
  }

  &__empty {
    text-align: center;
    @include themeify() {
      color: themed('color-ele-holder');
    }
  }

  .split-line hr {
    margin: 20px 0;
  }
}

.search-box {
  border-radius: 8px;
  min-width: 340px;

  .el-dialog__body {
    padding-top: 0;
  }
}
</style>
