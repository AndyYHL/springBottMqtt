package com.tuyou.mqtt.producer.web.controller;

import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.service.IEmqService;
import com.tuyou.mqtt.producer.service.IMqttGateway;
import io.swagger.annotations.Api;
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
@Api(tags = "消息推送",value = "消息推送",hidden = true)
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

    @GetMapping("send/cs")
    public String sendMage(){
        String string = "{\"equipmentNo\":\"123456789\",\"equipmentName\":\"XX油站采集器\",\"modelNumber\":\"型号\",\"status\":1,\"equipmentType\":1,\"lng\":\"104.0658152103\",\"lat\":\"30.6574616159\"}";
        mqttGateway.sendToMqtt(string,"wiz_publish");
        return "OK";
    }
    /**
     * 快速推送消息
     * @param sendData
     * @return
     */
    @RequestMapping("sendMqtt.do")
    public String sendMqtt(@RequestParam(value = "sendData")String  sendData,@RequestParam(value = "topic") String topic){
        mqttGateway.sendToMqtt(sendData,topic);
        return "OK";
    }
}
