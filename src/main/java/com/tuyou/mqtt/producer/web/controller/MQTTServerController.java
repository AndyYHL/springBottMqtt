package com.tuyou.mqtt.producer.web.controller;

import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.service.IEmqService;
import com.tuyou.mqtt.producer.service.IMqttGateway;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * mqtt 发送消息
 */
@RestController
@RequestMapping(value = ClientApiFinal.version + "mqtt/")
public class MQTTServerController {

    @Autowired
    private IEmqService iEmqService;
    @Autowired
    private IMqttGateway mqttGateway;

    String TOPIC = "MQTT_PRODUCER_TOPIC";

    @RequestMapping("/")
    public String sayHello() {
        return "Hello !";
    }

    @GetMapping("send/msg")
    public boolean send(@RequestParam String msg) throws MqttException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "==========:" + msg);
        return iEmqService.publish(TOPIC, msg);
    }

    /**
     * 快速推送消息
     * @param sendData
     * @return
     */
    @RequestMapping("sendMqtt.do")
    public String sendMqtt(@RequestParam String  sendData){
        mqttGateway.sendToMqtt(sendData,"hello2222");
        return "OK";
    }
}
