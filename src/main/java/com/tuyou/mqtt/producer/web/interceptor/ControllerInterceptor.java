package com.tuyou.mqtt.producer.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author web 拦截器
 */
@Slf4j
public class ControllerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // 得到请求URL
        String url = request.getRequestURI();
        url = url.replace(request.getContextPath(), "");
        log.info("请求的URL:{}", url);
        log.info("========preHandle=========");
        request.setAttribute("startTime", System.currentTimeMillis());
        log.info("startTime:{}", System.currentTimeMillis());
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        log.info("========afterCompletion=========");
        Long start = (Long) arg0.getAttribute("startTime");
        log.info("耗时:{}", (System.currentTimeMillis() - start));
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
        log.info("========postHandle=========");
        Long start = (Long) arg0.getAttribute("startTime");
        log.info("耗时:{}", (System.currentTimeMillis() - start));
    }
}
