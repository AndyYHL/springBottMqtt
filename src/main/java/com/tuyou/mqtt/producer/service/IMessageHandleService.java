package com.tuyou.mqtt.producer.service;

import com.tuyou.mqtt.producer.enumeration.TopicEnum;

/**
 * 消息处理
 * @author yhl
 */
public interface IMessageHandleService {
    /**
     * 消息处理
     * @param topic 订阅的主题
     * @param message 消息内容
     */
    void MessageHandle(String topic, String message);
}
