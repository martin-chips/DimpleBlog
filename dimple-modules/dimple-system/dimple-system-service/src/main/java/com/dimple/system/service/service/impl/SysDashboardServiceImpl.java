package com.dimple.system.service.service.impl;

import com.dimple.common.core.utils.DateUtils;
import com.dimple.system.service.entity.dashboard.Dashboard;
import com.dimple.system.service.entity.dashboard.DashboardItem;
import com.dimple.system.service.mapper.SysDashboardMapper;
import com.dimple.system.service.service.SysDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * SysDashboardServiceImpl
 *
 * @author Dimple
 */
@Service
public class SysDashboardServiceImpl implements SysDashboardService {
    @Autowired
    private SysDashboardMapper dashboardMapper;

    private static final String VISIT_LOG = "blog_visit_log";
    private static final String COMMENT = "blog_comment";
    private static final String ARTICLE = "blog_article";

    private static final String LOGIN = "sys_logininfor";

    @Override
    public Dashboard getDashboardWeeklyCount() {
        Date currentWeekStart = DateUtils.addDays(DateUtils.addWeeks(DateUtils.getNowDate(), -1), 1);
        Date currentWeekEnd = DateUtils.getNowDate();
        Date lastWeekStart = DateUtils.addDays(DateUtils.addWeeks(DateUtils.addWeeks(DateUtils.getNowDate(), -1), -1), 1);
        Date lastWeekEnd = DateUtils.addWeeks(DateUtils.getNowDate(), -1);

        Dashboard dashboard = new Dashboard();
        dashboard.setWeeks(getWeekDateSimpleStr(DateUtils.getNowDate(), -1));
        dashboard.setVisitor(getDashboardItem(currentWeekStart, currentWeekEnd, lastWeekStart, lastWeekEnd, VISIT_LOG));
        dashboard.setComment(getDashboardItem(currentWeekStart, currentWeekEnd, lastWeekStart, lastWeekEnd, COMMENT));
        dashboard.setArticle(getDashboardItem(currentWeekStart, currentWeekEnd, lastWeekStart, lastWeekEnd, ARTICLE));
        dashboard.setLogin(getDashboardItem(currentWeekStart, currentWeekEnd, lastWeekStart, lastWeekEnd, LOGIN));
        return dashboard;
    }

    private DashboardItem getDashboardItem(Date currentWeekStart, Date currentWeekEnd, Date lastWeekStart, Date lastWeekEnd, String tableName) {
        List<Long> currentWeekCount = dashboardMapper.getCountByDay(tableName, currentWeekStart, currentWeekEnd);
        List<Long> lastWeekCount = dashboardMapper.getCountByDay(tableName, lastWeekStart, lastWeekEnd);
        return new DashboardItem(lastWeekCount, currentWeekCount);
    }

    private static List<String> getWeekDateSimpleStr(final Date date, final int amount) {
        List<String> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date tempStartDate = DateUtils.addDays(DateUtils.addWeeks(date, amount), 1);
        while (!tempStartDate.after(date)) {
            calendar.setTime(tempStartDate);
            String dateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, tempStartDate);
            String dayOfWeek = sdf.format(calendar.getTime());
            result.add(dayOfWeek);
            tempStartDate = DateUtils.addDays(tempStartDate, 1);
        }
        return result;
    }


}
