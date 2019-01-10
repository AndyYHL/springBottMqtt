package com.tuyou.mqtt.producer.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * web日志打印 日志切面
 * @author yhl
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    /**
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     * com.tuyou.mqtt.producer.web.controller 换成自己的包路径
     */
    @Pointcut("execution( * com.tuyou.mqtt.producer.web.controller.*.*(..))")
    public void logPointCut() {
    }

    /**
     * 执行前操作
     * @param joinPoint
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("请求地址 : " + request.getRequestURL().toString());
        log.info("HTTP METHOD : " + request.getMethod());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        log.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 执行后打印日志
     * returning的值和doAfterReturning的参数名一致
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
        log.info("返回值 : " + ret);
    }

    /**
     * 环绕通知做打印耗时
     * 也可以把上面的2个合到这里来
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // ob 为方法的返回值
        Object ob = pjp.proceed();
        log.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }
}
