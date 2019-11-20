<template>
  <div class="timeline-content layout-content">
    <el-row>
      <el-col :xs="24" :sm="17" :md="17" :lg="17">
        <div class="layout-left">
          <SectionTitle :mainTitle="'存档时光'"
                        :subTitle="'Timeline'"
                        :menus="menus"
                        :withRefresh="true"
                        :withTimeSelect="true"
                        @refresh="refresh"
                        @menusControl="menusControl"
                        @comfirmDateSelect="dateSelect"
                        @clearDateSelect="dateSelectClear">
          </SectionTitle>
          <div v-for="year in sortedYearKeys(timeline)" v-if="Object.keys(timeline).length > 0">
            <ArchiveListTimeTitle :date="year+'年'"
                                  :count="timeline[year].count"></ArchiveListTimeTitle>
            <div v-for="month in sortedMonthKeys(timeline[year].months)">
              <ArchiveListTimeTitle :date="month+'月'"
                                    :count="timeline[year].months[month].length"
                                    :dateType="'month'">
              </ArchiveListTimeTitle>
              <ArchiveListCell v-for="post in timeline[year].months[month]" :post="post"
                               :key="post.id"></ArchiveListCell>
            </div>
          </div>
          <!--          <browse-more @browseMore="browseMore" :noMoreData="noMoreData" ref="browseMore"></browse-more>-->
        </div>
      </el-col>
      <el-col :xs="0" :sm="7" :md="7" :lg="7">
        <div class="layout-right">
          <Recommend style="margin-top:15px;"/>
          <Hot style="margin-top:15px;"/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
  import {
    mapState,
    mapMutations,
    mapActions
  } from 'vuex';

  import SectionTitle from "../../views/SectionTitle";
  import ArchiveListTimeTitle from "../../views/archive/ArchiveListTimeTitle";
  import ArchiveListCell from "../../views/archive/ArchiveListCell";
  import Recommend from "../../views/Recommend";
  import Hot from "../../views/Hot";

  export default {
    name: 'TimeLineContent',
    components: {SectionTitle, ArchiveListTimeTitle, ArchiveListCell, Recommend, Hot},
    data() {
      return {
        top_category: undefined,
        // 排序
        timeSorted: false,
        mostComment: undefined,
        recommend: undefined,
        limit_size: 100,
        page: 0,
        menus: [
          {
            title: '顺序',
            selectedTitle: '倒序',
            selected: true,
            method: 'timeSorted'
          },
          {title: '最多评论', selected: false, method: 'mostComment'},
          {title: '推荐', selected: false, method: 'recommend'}
        ],
        selectedDateRange: []
      };
    },
    beforeRouteLeave(to, from, next) {
      // 导航离开时清空已有的数据
      this.clearTimelineInfo();
      next();
    },
    mounted() {
      if (Object.keys(this.$store.state.timeline.timeline).length === 0) {
        console.log('non ssr');
        // 未SSR的情况
        this.updateTimeLineInfo(true);
      }
    },
    beforeDestroy() {
      // 导航离开时清空vuex中数据
      this.clearTimelineInfo();
    },
    computed: {
      ...mapState({
        timeline: state => state.timeline.timeline,
        noMoreData: state => state.timeline.noMoreData
      })
    },
    methods: {
      ...mapMutations({
        clearTimelineInfo: 'timeline/CLAER_TIMELINE_INFO'
      }),
      ...mapActions({
        getTimelineInfo: 'timeline/GET_TIMELINE_INFO'
      }),
      browseMore() {
        this.page++;
        this.updateTimeLineInfo(false);
      },
      updateTimeLineInfo(reset) {
        // 排序条件
        let orderings = [];
        if (this.timeSorted) {
          orderings.push('add_time');
        } else {
          orderings.push('-add_time');
        }
        if (this.mostComment !== undefined) {
          if (this.mostComment) {
            orderings.push('comment_num');
          } else {
            orderings.push('-comment_num');
          }
        }
        this.getTimelineInfo({
          params: {
            params: {
              top_category: this.top_category,
              ordering: orderings.toString(),
              is_recommend: this.recommend,
              time_min: this.selectedDateRange[0],
              time_max: this.selectedDateRange[1],
              limit: this.limit_size,
              offset: this.page * this.limit_size
            }
          },
          reset
        }).then(response => {
          this.$refs.browseMore.stopLoading();
        }).catch(error => {
          this.$refs.browseMore.stopLoading();
          console.log(error);
        });
      },
      sortedYearKeys(years) {
        let that = this;
        return Object.keys(years).sort(function (year1, year2) {
          return that.timeSorted ? year1 - year2 : year2 - year1;
        });
      },
      sortedMonthKeys(months) {
        let that = this;
        return Object.keys(months).sort(function (month1, month2) {
          return that.timeSorted ? month1 - month2 : month2 - month1;
        });
      },
      selectCategory(categoryId) {
        this.top_category = categoryId;
        this.page = 0;
        this.updateTimeLineInfo(true);
      },
      refresh() {
        this.top_category = undefined;
        this.timeSorted = false;
        this.mostComment = undefined;
        this.recommend = undefined;
        this.page = 0;
        this.selectedDateRange = [];
        this.updateTimeLineInfo(true);
      },
      menusControl(params) {
        switch (params[0]) {
          case 'timeSorted':
            this.timeSorted = !params[1];
            break;
          case 'mostComment':
            this.mostComment = params[1] ? params[1] : undefined;
            break;
          case 'recommend':
            this.recommend = params[1] ? params[1] : undefined;
            break;
        }
        // 清空原数据
        this.page = 0;
        this.updateTimeLineInfo(true);
      },
      dateSelect(dateRange) {
        this.selectedDateRange = [dateRange[0], dateAdd(dateRange[1], 60 * 60 * 24 * 1000)];
        this.page = 0;
        this.limit_size = MaxLimitSize;
        this.updateTimeLineInfo(true);
      },
      dateSelectClear() {
        this.selectedDateRange = [];
        this.page = 0;
        this.limit_size = 100;
        this.updateTimeLineInfo(true);
      }
    },

  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
</style>
