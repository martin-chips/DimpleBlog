<template>
    <div class="timeline-content layout-content">
        <Row>
            <Col :xs="24" :sm="17" :md="24" :lg="17">
                <div class="layout-left">
                    <SectionTitle :mainTitle="'存档时光'"
                                  :subTitle="'Timeline'"
                                  :menus="menus"
                                  :withRefresh="true"
                                  :withTimeSelect="true"
                                  @refresh="refresh"
                                  @menusControl="menusControl"
                                  @confirmDateSelect="confirmDateSelect"
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
                    <BrowseMore @browseMore="browseMore" :noMoreData="noMoreData" ref="browseMore"></BrowseMore>
                </div>
            </Col>
            <Col :xs="0" :sm="0" :md="0" :lg="7">
                <div class="layout-right">
                    <Recommend style="margin-top:15px;"/>
                    <Hot style="margin-top:15px;"/>
                </div>
            </Col>
        </Row>
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
    import BrowseMore from "../../views/BrowseMore";

    export default {
        name: 'TimeLineContent',
        components: {SectionTitle, ArchiveListTimeTitle, ArchiveListCell, BrowseMore, Recommend, Hot},
        data() {
            return {
                selected_category: undefined,
                // 排序
                timeSorted: false,
                mostComment: undefined,
                recommend: undefined,
                pageSize: 30,
                pageNum: 1,
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
                dateRange: []
            };
        },
        beforeRouteLeave(to, from, next) {
            // 导航离开时清空已有的数据
            this.clearTimelineInfo();
            next();
        },
        mounted() {
            if (Object.keys(this.$store.state.timeline.timeline).length === 0) {
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
                clearTimelineInfo: 'timeline/CLEAR_TIMELINE_INFO'
            }),
            ...mapActions({
                getTimelineInfo: 'timeline/GET_TIMELINE_INFO'
            }),
            browseMore() {
                this.pageNum++;
                this.updateTimeLineInfo(false);
            },
            updateTimeLineInfo(reset) {
                // 排序条件
                let orderings = [];
                if (this.mostComment !== undefined) {
                    orderings.push(this.mostComment ? 'commentCount' : '-commentCount');
                }
                let params = {
                    categoryId: this.selected_category,
                    orderByColumn: orderings.toString(),
                    isAsc: this.timeSorted ? 'asc' : 'desc',
                    support: this.recommend,
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    beginTime: this.dateRange[0],
                    endTime: this.dateRange[1]
                };
                this.getTimelineInfo({params, reset}).then(response => {
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
                this.selected_category = categoryId;
                this.pageNum = 0;
                this.updateTimeLineInfo(true);
            },
            refresh() {
                this.selected_category = undefined;
                this.timeSorted = false;
                this.mostComment = undefined;
                this.recommend = undefined;
                this.page = 0;
                this.dateRange = [];
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
                this.pageNum = 1;
                this.updateTimeLineInfo(true);
            },
            confirmDateSelect(dateRange) {
                this.dateRange = dateRange;
                this.updateTimeLineInfo(true);
            },
            dateSelect(dateRange) {

            },
            dateSelectClear() {
                this.dateRange = [];
                this.updateTimeLineInfo(true);
            }
        }
    }
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
</style>
