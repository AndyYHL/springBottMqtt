package com.tuyou.mqtt.producer.web.handle;

import com.tuyou.mqtt.producer.pojo.swagger2.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 控制器全局异常通知
 *
 * @author yhl
 * 2018-11-06 9:14
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionControllerAdvice {
    /**
     * 全局异常捕获
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult jsonHandler(HttpServletRequest request, Exception e) throws Exception {
        // 调用异常
        log(e, request);
        return new ResponseResult(HttpStatus.SC_GATEWAY_TIMEOUT, e.getMessage());
    }

    private void log(Exception ex, HttpServletRequest request) {
        log.error("************************异常开始*******************************");
        log.error(ex.getMessage());
        log.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        log.error("请求参数");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            log.error(name + "---" + request.getParameter(name));
        }
        StackTraceElement[] error = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            log.error(stackTraceElement.toString());
        }
        log.error("************************异常结束*******************************");
    }
}
