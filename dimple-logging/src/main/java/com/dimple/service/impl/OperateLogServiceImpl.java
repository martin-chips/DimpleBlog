package com.dimple.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.dimple.annotation.OLog;
import com.dimple.domain.OperateLog;
import com.dimple.repository.OperateLogRepository;
import com.dimple.service.OperateLogService;
import com.dimple.service.dto.LogQueryCriteria;
import com.dimple.service.mapstruct.LogErrorMapper;
import com.dimple.service.mapstruct.LogSmallMapper;
import com.dimple.utils.FileUtil;
import com.dimple.utils.IpUtil;
import com.dimple.utils.PageUtil;
import com.dimple.utils.QueryHelp;
import com.dimple.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @className: LogServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service
@RequiredArgsConstructor
public class OperateLogServiceImpl implements OperateLogService {
    private static final Logger log = LoggerFactory.getLogger(OperateLogServiceImpl.class);
    private final OperateLogRepository operateLogRepository;
    private final LogErrorMapper logErrorMapper;
    private final LogSmallMapper logSmallMapper;

    @Override
    public Object queryAll(LogQueryCriteria criteria, Pageable pageable) {
        Page<OperateLog> page = operateLogRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
        String status = "ERROR";
        if (status.equals(criteria.getLogType())) {
            return PageUtil.toPage(page.map(logErrorMapper::toDto));
        }
        return page;
    }

    @Override
    public List<OperateLog> queryAll(LogQueryCriteria criteria) {
        return operateLogRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)));
    }

    @Override
    public Object queryAllByUser(LogQueryCriteria criteria, Pageable pageable) {
        Page<OperateLog> page = operateLogRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
        return PageUtil.toPage(page.map(logSmallMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, OperateLog operateLog) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OLog aopOLog = method.getAnnotation(OLog.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        List<Object> argValues = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        //参数名称
        for (Object argValue : argValues) {
            params.append(argValue).append(" ");
        }
        // 描述
        if (operateLog != null) {
            operateLog.setDescription(aopOLog.value());
        }
        assert operateLog != null;
        operateLog.setRequestIp(ip);

        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                username = new JSONObject(argValues.get(0)).get("username").toString();
            } catch (Exception e) {
                OperateLogServiceImpl.log.error(e.getMessage(), e);
            }
        }
        operateLog.setAddress(IpUtil.getCityInfo(operateLog.getRequestIp()));
        operateLog.setMethod(methodName);
        operateLog.setUsername(username);
        operateLog.setParams(params.toString() + " }");
        operateLog.setBrowser(browser);
        operateLogRepository.save(operateLog);
    }

    @Override
    public Object findByErrDetail(Long id) {
        OperateLog operateLog = operateLogRepository.findById(id).orElseGet(OperateLog::new);
        ValidationUtil.isNull(operateLog.getId(), "Log", "id", id);
        byte[] details = operateLog.getExceptionDetail();
        return Dict.create().set("exception", new String(ObjectUtil.isNotNull(details) ? details : "".getBytes()));
    }

    @Override
    public void download(List<OperateLog> operateLogs, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OperateLog operateLog : operateLogs) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", operateLog.getUsername());
            map.put("IP", operateLog.getRequestIp());
            map.put("IP来源", operateLog.getAddress());
            map.put("描述", operateLog.getDescription());
            map.put("浏览器", operateLog.getBrowser());
            map.put("请求耗时/毫秒", operateLog.getTime());
            map.put("异常详情", new String(ObjectUtil.isNotNull(operateLog.getExceptionDetail()) ? operateLog.getExceptionDetail() : "".getBytes()));
            map.put("创建日期", operateLog.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByError() {
        operateLogRepository.deleteByLogType("ERROR");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByInfo() {
        operateLogRepository.deleteByLogType("INFO");
    }
}
