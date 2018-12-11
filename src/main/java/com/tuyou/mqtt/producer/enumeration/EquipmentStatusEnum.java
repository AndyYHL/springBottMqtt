package com.tuyou.mqtt.producer.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.Setter;

public enum EquipmentStatusEnum {
    ONLINE(1,"在线"),SEALUP(2,"封存"),IDLE(3,"闲置"),SCRAP(4,"报废"),REPAIRED(5,"待修"),SPARE(6,"备用");
    @EnumValue
    /**
     * 状态
     */
    @Getter
    @Setter
    private int status;
    /**
     * 说明
     */
    private String description;

    EquipmentStatusEnum(int status, String description) {
        this.status = status;
        this.description = description;
    }
}
