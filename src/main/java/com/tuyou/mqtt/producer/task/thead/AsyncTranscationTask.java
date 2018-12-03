package com.tuyou.mqtt.producer.task.thead;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * Created by wuhaochao on 2017/7/25.
 */
@Component
public class AsyncTranscationTask {

    /**
     * myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
     *
     * @param i
     * @throws InterruptedException
     */
    @Async("myTaskAsyncPool")
    public void insertTransTask(String i) {
        //开始导入交易信息
        String method_BlockByNumber = "eth_getBlockByNumber".concat(i);
        System.out.println(method_BlockByNumber);
        //批量插入
        //transcationMapper.insertTranscationList(list);
    }
}