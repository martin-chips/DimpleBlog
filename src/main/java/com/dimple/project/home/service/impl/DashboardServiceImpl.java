package com.dimple.project.home.service.impl;

import com.dimple.common.utils.DateUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.home.domain.LineChartData;
import com.dimple.project.home.mapper.DashBoardMapper;
import com.dimple.project.home.service.DashboardService;
import com.dimple.project.log.domain.VisitLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @className: DashboardServiceImpl
 * @description:
 * @author: Dimple
 * @date: 12/20/19
 */
@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    DashBoardMapper dashBoardMapper;

    @Override
    public Map<String, Long> getPanelGroupData() {
        Map<String, Long> resultMap = new HashMap<>();
        Long visitorCount = dashBoardMapper.getVisitorCount();
        Long blogCount = dashBoardMapper.getBlogCount();
        Long bookCount = dashBoardMapper.getBookCount();
        Long noteCount = dashBoardMapper.getNoteCount();
        resultMap.put("visitorCount", visitorCount);
        resultMap.put("blogCount", blogCount);
        resultMap.put("bookCount", bookCount);
        resultMap.put("noteCount", noteCount);
        return resultMap;
    }

    @Override
    public LineChartData<Long> getLineChartData(String type) {
        LineChartData lineChartData = null;
        switch (type) {
            case LineChartData.BLOG_LINE:
                lineChartData = getBlogLineChartData();
                break;
            case LineChartData.BOOK_LINE:
                lineChartData = getBookLineChartData();
                break;
            case LineChartData.NOTE_LINE:
                lineChartData = getNoteLineChartData();
                break;
            case LineChartData.VISITOR_LINE:
                lineChartData = getVisitorLineChartData();
                break;
            default:
        }
        log.info("get line chart data \n{}", lineChartData);
        return lineChartData;
    }

    @Override
    public List<Map<String, Long>> getSpiderData() {
        return dashBoardMapper.getSpiderData();
    }

    @Override
    public List<String> getVisitLogStringList() {
        List<VisitLog> visitLogList = dashBoardMapper.getVisitLog();
        List<String> result = new ArrayList<>();
        for (VisitLog visitLog : visitLogList) {
            String name = "";
            if (visitLog.getPageId() == -1000) {
                name = "留言页";
            } else {
                Long pageId = visitLog.getPageId();
                if (pageId != null) {
                    name = dashBoardMapper.getBlogNameByPageId(pageId);
                }
            }
            //XXX 分钟前 : @47.112.14.207 浏览了 <strong>title</strong> <a href='url'>XXXXX</a>
            result.add(StringUtils.format("{} : @{} 浏览了 <strong> {} </strong> <a href='{}'> {} </a>", DateUtils.showTime(visitLog.getCreateTime()), visitLog.getIp(), visitLog.getTitle(), visitLog.getUrl(), StringUtils.isEmpty(name) ? visitLog.getUrl() : name));
        }
        return result;
    }

    /**
     * 获取访问折线图数据
     *
     * @return 折线图数据
     */
    private LineChartData getVisitorLineChartData() {
        //Get days before the current time mark one week.
        List<String> actualDataDayList = DateUtils.getPastDaysList(7);
        List<Long> actualData = new LinkedList<>();
        for (String day : actualDataDayList) {
            Long count = dashBoardMapper.getVisitorCountByCreateTime(day);
            actualData.add(count);
        }
        //Get days before the select time mark one week.
        List<String> expectedDataDayList = DateUtils.getPastDaysList(7, 7);
        List<Long> expectedData = new LinkedList<>();
        for (String day : expectedDataDayList) {
            Long count = dashBoardMapper.getVisitorCountByCreateTime(day);
            expectedData.add(count);
        }
        LineChartData<Long> lineChartData = new LineChartData<>();
        lineChartData.setActualData(actualData);
        lineChartData.setExpectedData(expectedData);
        lineChartData.setAxisData(actualDataDayList);
        return lineChartData;
    }

    private LineChartData getNoteLineChartData() {
        //Get days before the current time mark one week.
        List<String> actualDataDayList = DateUtils.getPastDaysList(7);
        List<Long> actualData = new LinkedList<>();
        for (String day : actualDataDayList) {
            Long count = dashBoardMapper.getNoteCountByCreateTime(day);
            actualData.add(count);
        }
        //Get days before the select time mark one week.
        List<String> expectedDataDayList = DateUtils.getPastDaysList(7, 7);
        List<Long> expectedData = new LinkedList<>();
        for (String day : expectedDataDayList) {
            Long count = dashBoardMapper.getNoteCountByCreateTime(day);
            expectedData.add(count);
        }
        LineChartData<Long> lineChartData = new LineChartData<>();
        lineChartData.setActualData(actualData);
        lineChartData.setExpectedData(expectedData);
        lineChartData.setAxisData(actualDataDayList);
        return lineChartData;
    }

    private LineChartData getBookLineChartData() {
        //Get days before the current time mark one week.
        List<String> actualDataDayList = DateUtils.getPastDaysList(7);
        List<Long> actualData = new LinkedList<>();
        for (String day : actualDataDayList) {
            Long count = dashBoardMapper.getBookCountByCreateTime(day);
            actualData.add(count);
        }
        //Get days before the select time mark one week.
        List<String> expectedDataDayList = DateUtils.getPastDaysList(7, 7);
        List<Long> expectedData = new LinkedList<>();
        for (String day : expectedDataDayList) {
            Long count = dashBoardMapper.getBookCountByCreateTime(day);
            expectedData.add(count);
        }
        LineChartData<Long> lineChartData = new LineChartData<>();
        lineChartData.setActualData(actualData);
        lineChartData.setExpectedData(expectedData);
        lineChartData.setAxisData(actualDataDayList);
        return lineChartData;
    }

    private LineChartData getBlogLineChartData() {
        //Get days before the current time mark one week.
        List<String> actualDataDayList = DateUtils.getPastDaysList(7);
        List<Long> actualData = new LinkedList<>();
        for (String day : actualDataDayList) {
            Long count = dashBoardMapper.getBlogCountByCreateTime(day);
            actualData.add(count);
        }
        //Get days before the select time mark one week.
        List<String> expectedDataDayList = DateUtils.getPastDaysList(7, 7);
        List<Long> expectedData = new LinkedList<>();
        for (String day : expectedDataDayList) {
            Long count = dashBoardMapper.getBlogCountByCreateTime(day);
            expectedData.add(count);
        }
        LineChartData<Long> lineChartData = new LineChartData<>();
        lineChartData.setActualData(actualData);
        lineChartData.setExpectedData(expectedData);
        lineChartData.setAxisData(actualDataDayList);
        return lineChartData;
    }

}
