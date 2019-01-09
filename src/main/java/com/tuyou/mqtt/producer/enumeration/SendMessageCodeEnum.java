package com.tuyou.mqtt.producer.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.Setter;

public enum SendMessageCodeEnum {
    MATCHING(100,"匹配消息"),UPDATE(101,"更新消息"),ADVERT(102,"广告消息"),CONSUMPTION(103,"消费消息");
    @EnumValue
    /**
     * 状态
     */
    @Getter
    @Setter
    private int code;
    /**
     * 说明
     */
    @Getter
    @Setter
    private String description;

    SendMessageCodeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
