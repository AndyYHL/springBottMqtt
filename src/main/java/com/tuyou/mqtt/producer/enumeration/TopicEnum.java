package com.tuyou.mqtt.producer.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
/**
 * Mqtt主题枚举
 * @author yhl
 * 2018-09-29 16:22
 */
public enum TopicEnum {
    DEVICEREGISTER(1,"deviceregister"),OILTANKDATA(2,"oiltankdata");
    @EnumValue
    /**
     * 状态
     */
    private Integer code;
    /**
     * 说明
     */
    private String description;

    TopicEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
