package com.tuyou.mqtt.producer.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.Setter;

public enum EquipmentTypeEnum {
    DEVICE(1,"硬件设备"),ANDROID(2,"android设备"),IOS(3,"IOS设备"),OTHER(4,"其他设备");
    @EnumValue
    /**
     * 状态
     */
    @Getter
    @Setter
    private int equipmentType;
    /**
     * 说明
     */
    private String description;

    EquipmentTypeEnum(int equipmentType, String description) {
        this.equipmentType = equipmentType;
        this.description = description;
    }
}
