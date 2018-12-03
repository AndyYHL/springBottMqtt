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

        System.out.println("========preHandle=========");
        request.setAttribute("startTime",System.currentTimeMillis());
        System.out.println("startTime:"+ System.currentTimeMillis());
        return true;

		/*if(url.startsWith("/resources/")) { //不拦截的资源
			return true;
		}
		if(url.startsWith("/api/gis/")) { //不拦截的API接口请求
			return true;
		}
		for(String s : uncheckUrlEnds) { //不拦截的url
			if(url.endsWith(s)) {
				return true;
			}
		}
		if(isUserLogined()) {//已经登录
			return true;
		}
		else {//没有登录
			if(uncheckUrls.contains(url)) {//允许访问登录页面资源
				return true;
			}
			else {
				sendRedirectTo(request, response, "timeout", "/login");
				return false;
			}
		}*/
    }


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        System.out.println("========afterCompletion=========");
        Long start = (Long) arg0.getAttribute("startTime");
        System.out.println("耗时:"+(System.currentTimeMillis() - start));
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
        System.out.println("========postHandle=========");
        Long start = (Long) arg0.getAttribute("startTime");
        System.out.println("耗时:"+(System.currentTimeMillis() - start));
    }
}
