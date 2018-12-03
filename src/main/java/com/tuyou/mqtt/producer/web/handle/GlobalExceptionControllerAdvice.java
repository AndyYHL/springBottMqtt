package com.tuyou.mqtt.producer.web.handle;

import com.tuyou.mqtt.producer.util.json.Info;
import com.tuyou.mqtt.producer.util.json.JsonUtil;
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
    public JsonUtil jsonHandler(HttpServletRequest request, Exception e) throws Exception {
        JsonUtil jsonUtil = new JsonUtil();
        Info info = new Info();
        info.setMessage(e.getMessage());
        info.setStatus(HttpStatus.SC_GATEWAY_TIMEOUT);
        jsonUtil.setInfo(info);
        // 调用异常
        log(e, request);
        return jsonUtil;
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
