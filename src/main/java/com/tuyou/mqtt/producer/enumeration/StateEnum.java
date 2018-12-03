package com.tuyou.mqtt.producer.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 状态枚举
 * @author yhl
 * 2018-09-29 16:22
 */
public enum StateEnum {
    BLOCK(-1, "锁定"), ACTIVATE(0, "激活");

    @EnumValue
    /**
     * 状态
     */
    private int code;
    /**
     * 说明
     */
    private String description;

    StateEnum(int code, String description) {
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

