package com.tuyou.mqtt.producer.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author yhl
 * 推送消息类
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface IMqttGateway {
    void sendToMqtt(String data,@Header(MqttHeaders.TOPIC) String topic);
}
