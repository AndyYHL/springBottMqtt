package com.tuyou.mqtt.producer.web.controller;

import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.pojo.param.SendMqttCriteria;
import com.tuyou.mqtt.producer.service.IEmqService;
import com.tuyou.mqtt.producer.service.IMqttGateway;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import net.toyou.pojo.swagger2.ResponseResult;
import org.apache.http.HttpStatus;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author yhl
 * mqtt 发送消息
 */
@Api(tags = "消息推送",value = "消息推送",hidden = true)
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
    public String sendMage(){
        String string = "{\"equipmentNo\":\"123456789\",\"equipmentName\":\"XX油站采集器\",\"modelNumber\":\"型号\",\"status\":1,\"equipmentType\":1,\"lng\":\"104.0658152103\",\"lat\":\"30.6574616159\"}";
        mqttGateway.sendToMqtt(string,"wiz_publish");
        return "OK";
    }
    /**
     * 快速推送消息
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
    public ResponseResult sendMqtt(@RequestBody SendMqttCriteria sendMqttCriteria){
        log.info("发送给消息的设备：{},发送的内容是:{}",sendMqttCriteria.getTopic(),sendMqttCriteria.getSendData());
        ResponseResult result = new ResponseResult();
        try{
            mqttGateway.sendToMqtt(sendMqttCriteria.getSendData(),sendMqttCriteria.getTopic());
            result.setCode(HttpStatus.SC_OK);
            result.setMessage("发送成功");
        }catch (Exception ex){
            result.setCode(HttpStatus.SC_BAD_GATEWAY);
            result.setMessage(new String("发送异常!").concat(ex.getMessage()));
        }
        return result;
    }
}
