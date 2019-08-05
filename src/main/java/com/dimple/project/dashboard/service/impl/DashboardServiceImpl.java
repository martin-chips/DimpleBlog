package com.dimple.project.dashboard.service.impl;

import com.dimple.common.constant.BadgeStyle;
import com.dimple.common.constant.Constants;
import com.dimple.common.utils.DateUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.blog.blog.mapper.BlogMapper;
import com.dimple.project.blog.tag.mapper.TagMapper;
import com.dimple.project.dashboard.domain.BusinessCommonData;
import com.dimple.project.dashboard.domain.LogMessage;
import com.dimple.project.dashboard.domain.VisitCount;
import com.dimple.project.dashboard.service.DashboardService;
import com.dimple.project.log.jobLog.mapper.JobLogMapper;
import com.dimple.project.log.logininfor.mapper.LogininforMapper;
import com.dimple.project.log.operlog.mapper.OperLogMapper;
import com.dimple.project.log.visitorLog.mapper.VisitLogMapper;
import com.dimple.project.system.dict.mapper.DictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @className: DashboardServiceImpl
 * @description:
 * @auther: Dimple
 * @date: 04/02/19
 * @version: 1.0
 */
@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    VisitLogMapper visitLogMapper;
    @Autowired
    OperLogMapper operLogMapper;
    @Autowired
    LogininforMapper logininforMapper;
    @Autowired
    JobLogMapper jobLogMapper;
    @Autowired
    DictDataMapper dictDataMapper;
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagMapper tagMapper;


    @Override
    public List<BusinessCommonData> selectSpiderData() {
        return visitLogMapper.selectSpiderData();
    }

    @Override
    public List<VisitCount> getVisitData() {
        List<String> pastDaysList = DateUtils.getPastDaysList(7);
        List<VisitCount> visitCounts = new ArrayList<>();
        for (String date : pastDaysList) {
            Integer visitorCountByVisitTime = visitLogMapper.selectVisitCountByCreateTime(date);
            VisitCount visitCount = new VisitCount();
            visitCount.setDate(date);
            visitCount.setValue(visitorCountByVisitTime);
            visitCounts.add(visitCount);
        }
        return visitCounts;
    }

    @Override
    public List<LogMessage> selectLogMessage() {
        Stream<LogMessage> loginLogStream = logininforMapper.selectLoginInforData().stream().map(log ->
                new LogMessage.Builder()
                        .date(log.getLoginTime())
                        .dateStr(DateUtils.showTime(log.getLoginTime()))
                        .message(StringUtils.format("@{}: {} {}", log.getIpaddr(), log.getLoginName(), strongStringToHTML(log.getMsg())))
                        .style(Constants.SUCCESS.equals(log.getStatus()) ? BadgeStyle.SUCCESS.getType() : BadgeStyle.DANGER.getType())
                        .builder()
        );
        Stream<LogMessage> operateLogStream = operLogMapper.selectOperLogData().stream().map(log ->
                new LogMessage.Builder().
                        dateStr(DateUtils.showTime(log.getOperTime()))
                        .date(log.getOperTime())
                        .message(StringUtils.format("@{}：{} 对 {} 进行了 {} 操作", log.getOperIp(), log.getOperName(), strongStringToHTML(log.getTitle()), strongStringToHTML(dictDataMapper.selectDictLabel("sys_oper_type", log.getBusinessType().toString()))))
                        .style("badge-" + dictDataMapper.selectCssClassByDictTypeAndDictValue("sys_oper_type", log.getBusinessType().toString()))
                        .builder()
        );
        Stream<LogMessage> visitLogStream = visitLogMapper.selectVisitData().stream().map(log ->
                {
                    LogMessage logMessage = new LogMessage.Builder()
                            .date(log.getCreateTime())
                            .message(StringUtils.format("@{} 浏览了 {}", log.getIpAddr(), "<a class='logUrl'  style='font-weight: bold;' href='" + log.getRequestUrl() + "'>" + log.getTitle() + "</a>"))
                            .dateStr(DateUtils.showTime(log.getCreateTime()))
                            .style(BadgeStyle.INFO.getType())
                            .builder();
                    String blogIdString = log.getRequestUrl().replaceAll("[^0-9]", "");
                    try {
                        Integer id = Integer.valueOf(blogIdString);
                        String title = "";
                        //表示是文章
                        if (log.getRequestUrl().contains("article")) {
                            title = blogMapper.selectBlogTitleByBlogId(id);
                            if (StringUtils.isNotEmpty(title)) {
                                logMessage.setMessage(StringUtils.format("@{} 浏览了 {}", log.getIpAddr(), "<a class='logUrl' style='font-weight: bold;' href=' " + log.getRequestUrl() + "'>" + "文章：" + title + "</a>"));
                            }
                        } else if (log.getRequestUrl().contains("tag")) {
                            title = tagMapper.selectTagTitleByTagId(id);
                            if (StringUtils.isNotEmpty(title)) {
                                logMessage.setMessage(StringUtils.format("@{} 浏览了 {}", log.getIpAddr(), "<a class='logUrl' style='font-weight: bold;' href='" + log.getRequestUrl() + "'>" + "标签：" + title + "</a>"));
                            }
                        }

                    } catch (Exception e) {
                        //ignore
                    }
                    if (log.getRequestUrl().contains("search")) {
                        String searchKey = null;
                        try {
                            searchKey = URLDecoder.decode(log.getRequestUrl().replaceAll("/f/search/", "").replaceAll(".html", ""), "UTF-8");
                            if (StringUtils.isNotEmpty(searchKey)) {
                                logMessage.setMessage(StringUtils.format("@{} 浏览了 {}", log.getIpAddr(), "<a style='font-weight: bold;' href=' " + log.getRequestUrl() + "'>" + "标签：" + searchKey + "</a>"));
                            }
                        } catch (UnsupportedEncodingException e) {
                            //ignore
                        }
                    }
                    return logMessage;
                }
        );
        Stream<LogMessage> jobLogStream = jobLogMapper.selectJobData().stream().map(log ->
                new LogMessage.Builder()
                        .dateStr(DateUtils.showTime(log.getCreateTime()))
                        .date(log.getCreateTime())
                        .message(StringUtils.format("{}({}) {} {}", log.getJobName(), log.getJobGroup(), Constants.SUCCESS.equals(log.getStatus()) ? "启动成功：" : "启动失败：", strongStringToHTML(log.getJobMessage())))
                        .style(Constants.SUCCESS.equals(log.getStatus()) ? BadgeStyle.SUCCESS.getType() : BadgeStyle.DANGER.getType())
                        .builder()
        );
        return Stream.concat(loginLogStream, Stream.concat(Stream.concat(operateLogStream, visitLogStream), jobLogStream))
                .sorted(Comparator.comparing(LogMessage::getDate).reversed()).limit(100).collect(Collectors.toList());
    }

    private String strongStringToHTML(String str) {
        return "<b>" + str + "</b>";
    }
}
