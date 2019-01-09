package com.tuyou.mqtt.producer.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 消息推送的消息体
 * @author yhl
 */
@ApiModel(value="MQTT消息推送",description="MQTT消息推送")
@Data
@ToString
@NoArgsConstructor
public class SendMqttCriteria {
    /**
     * 发送消息的主题
     */
    @ApiModelProperty(value="主题/设备号",name="topic",required=true,dataType = "String")
    private String topic;
    /**
     * 发送的消息内容
     */
    @ApiModelProperty(value="发送的内容",name="sendData",required=true,dataType = "Int")
    private String sendData;
}
