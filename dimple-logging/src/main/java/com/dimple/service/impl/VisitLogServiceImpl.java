package com.dimple.service.impl;

import com.dimple.annotation.VLog;
import com.dimple.domain.VisitLog;
import com.dimple.repository.VisitLogRepository;
import com.dimple.service.VisitLogService;
import com.dimple.service.dto.VisitLogQueryCriteria;
import com.dimple.utils.IpUtil;
import com.dimple.utils.QueryHelp;
import com.dimple.utils.SpiderUtils;
import com.dimple.utils.StringUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @className: VisitLogServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VisitLogServiceImpl implements VisitLogService {
    private final VisitLogRepository visitLogRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(VisitLog visitLog, JoinPoint joinPoint, HttpServletRequest httpServletRequest) {
        // 获得注解
        VLog vLog = getAnnotationLog(joinPoint);
        if (vLog == null) {
            return;
        }
        visitLog.setPageId(getPageId(vLog, joinPoint));
        String ip = IpUtil.getIp(httpServletRequest);
        visitLog.setRequestIp(ip);
        visitLog.setUrl(httpServletRequest.getRequestURI());
        //设置入口地址
        visitLog.setEntryUrl(httpServletRequest.getHeader("referer"));
        // 处理设置注解上的参数
        visitLog.setTitle(vLog.title());
        visitLog.setAddress(IpUtil.getCityInfo(ip));

        String header = httpServletRequest.getHeader("User-Agent");
        final UserAgent userAgent = UserAgent.parseUserAgentString(header);
        final String spider = SpiderUtils.parseUserAgent(header);
        visitLog.setOs(userAgent.getOperatingSystem().getName());
        visitLog.setSpider(spider);
        visitLog.setBrowser(userAgent.getBrowser().getName());
        visitLogRepository.save(visitLog);
    }

    @Override
    public List<VisitLog> queryAll(VisitLogQueryCriteria criteria) {
        return visitLogRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)));
    }

    @Override
    public void download(Object queryAll, HttpServletResponse response) {

    }

    @Override
    public Page<VisitLog> queryAll(VisitLogQueryCriteria criteria, Pageable pageable) {
        return visitLogRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
    }

    @Override
    public void clear() {

    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private VLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(VLog.class);
        }
        return null;
    }

    /**
     * 获取PageId
     *
     * @param joinPoint 切入点
     * @return PageId
     */
    private Long getPageId(VLog vLog, JoinPoint joinPoint) {
        String pageIdStr = vLog.pageId();
        if (StringUtils.isEmpty(pageIdStr)) {
            return null;
        }
        //get target method
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = null;
        try {
            method = joinPoint.getTarget().getClass().getMethod(methodSignature.getMethod().getName(), methodSignature.getMethod().getParameterTypes());
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
        //express SpEL
        ExpressionParser expressionParser = new SpelExpressionParser();
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] params = localVariableTableParameterNameDiscoverer.getParameterNames(method);

        Object[] args = joinPoint.getArgs();
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }

        Expression expression = expressionParser.parseExpression(pageIdStr);
        Object value = expression.getValue(context);

        if (value == null) {
            return null;
        }
        try {
            return (Long) value;
        } catch (Exception e) {
            log.error("get pageId error for parameters {}", value);
            return null;
        }
    }
}
