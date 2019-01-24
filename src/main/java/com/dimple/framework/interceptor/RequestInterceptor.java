package com.dimple.framework.interceptor;

import com.dimple.modules.endModule.visitorManager.service.VisitorService;
import com.dimple.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: RequestInterceptor
 * @Description: 用户请求拦截器
 * @Auther: Owenb
 * @Date: 11/28/18 18:56
 * @Version: 1.0
 */
@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    VisitorService visitorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = ShiroUtil.getIp();
        log.info("当前访问的IP地址是： " + ip);
        String id = request.getSession().getId();
        System.out.println("当前的id" + id);
        String requestUrl = request.getRequestURI();
        //使用view来区别是不是前端访问用户
        if ("/".equals(requestUrl) || requestUrl.contains("view")) {
            Boolean success = visitorService.checkoutIp(ip);
            if (success == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
