package com.tuyou.mqtt.producer.web.controller;

import com.alibaba.fastjson.JSON;
import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.enumeration.SendMessageCodeEnum;
import com.tuyou.mqtt.producer.pojo.param.SendMqttCriteria;
import com.tuyou.mqtt.producer.service.IEmqService;
import com.tuyou.mqtt.producer.service.IMqttGateway;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.toyou.pojo.swagger2.DataResponseResult;
import net.toyou.pojo.swagger2.ResponseResult;
import org.apache.http.HttpStatus;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author yhl
 * mqtt 发送消息
 */
@Api(tags = "消息推送", value = "消息推送", hidden = true)
@Slf4j
@RestController
@RequestMapping(value = ClientApiFinal.version + "mqtt/")
public class MQTTServerController {

    @Autowired
    private IEmqService iEmqService;
    @Autowired
    private IMqttGateway mqttGateway;

    String TOPIC = "MQTT_PRODUCER_TOPIC";

    @ApiIgnore
    @GetMapping("send/msg")
    public boolean send(@RequestParam String msg) throws MqttException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "==========:" + msg);
        return iEmqService.publish(TOPIC, msg);
    }

    @ApiIgnore
    @GetMapping("send/cs")
    public String sendMage() {
        String string = "{\"equipmentNo\":\"123456789\",\"equipmentName\":\"XX油站采集器\",\"modelNumber\":\"型号\",\"status\":1,\"equipmentType\":1,\"lng\":\"104.0658152103\",\"lat\":\"30.6574616159\"}";
        mqttGateway.sendToMqtt(string, "wiz_publish");
        return "OK";
    }

    /**
     * 快速推送消息
     *
     * @param sendMqttCriteria 发送的消息
     * @return
     */
    @ApiOperation(value = "根据站点ID获取这点注册的设备", notes = "获取设备")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.SC_OK, message = "新增成功!"),
            @ApiResponse(code = HttpStatus.SC_FAILED_DEPENDENCY, message = "参数不完整!"),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "请求异常!")
    })
    @PostMapping("sendMqtt")
    public ResponseResult sendMqtt(@RequestBody SendMqttCriteria sendMqttCriteria) {
        log.info("发送给消息的设备：{},发送的内容是:{}", sendMqttCriteria.getTopic(), sendMqttCriteria.getSendData());
        DataResponseResult dataResponseResult = new DataResponseResult();
        try {
            // 匹配消息
            if (sendMqttCriteria.getMessageType().equals(SendMessageCodeEnum.MATCHING.getCode())) {
                dataResponseResult.setCode(SendMessageCodeEnum.MATCHING.getCode());
                // 转成Map加入对象
                Map map = JSON.parseObject(sendMqttCriteria.getSendData(),Map.class);
                log.info("转成的Map的字符串是:{}",JSON.toJSONString(map));
                dataResponseResult.setData(map);
            }

            log.info("开始发送消息:{}", JSON.toJSONString(dataResponseResult));
            mqttGateway.sendToMqtt(JSON.toJSONString(dataResponseResult), sendMqttCriteria.getTopic());
            dataResponseResult.setCode(HttpStatus.SC_OK);
            dataResponseResult.setMessage("发送成功");
        } catch (Exception ex) {
            dataResponseResult.setCode(HttpStatus.SC_BAD_GATEWAY);
            dataResponseResult.setMessage(new String("发送异常!").concat(ex.getMessage()));
        }
        return dataResponseResult;
    }
}
