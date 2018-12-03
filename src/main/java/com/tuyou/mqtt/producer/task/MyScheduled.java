package com.tuyou.mqtt.producer.task;

import com.tuyou.mqtt.producer.task.thead.AsyncTranscationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yhl
 * 定时任务,每五分钟探测一次区块链中的是否存在最新交易信息
 */
@Component
public class MyScheduled {
    @Autowired
    private AsyncTranscationTask asyncTranscationTask;

    /**
     * 每天每5秒执行一次
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void executeTask2() {
        //获取最新区块编号
        try {
            //批量插入
            asyncTranscationTask.insertTransTask("1");
            System.out.println("定时任务启动");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
