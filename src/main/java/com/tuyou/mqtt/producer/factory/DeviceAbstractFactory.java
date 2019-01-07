package com.tuyou.mqtt.producer.factory;

import com.tuyou.mqtt.producer.enumeration.EquipmentTypeEnum;
import com.tuyou.mqtt.producer.pojo.props.SpringContext;
import com.tuyou.mqtt.producer.team.device.DeviceTeamFactory;
import com.tuyou.mqtt.producer.team.device.impl.AndroidDeviceImpl;
import com.tuyou.mqtt.producer.team.device.impl.HardwareDeviceImpl;
import org.springframework.context.annotation.Configuration;

/**
 * @author yhl
 * 设备来源工厂
 */
@Configuration
public class DeviceAbstractFactory {
    /**
     * 工厂生产
     * @param equipmentTypeEnum
     * @return
     */
    public DeviceTeamFactory getDeviceTeam(EquipmentTypeEnum equipmentTypeEnum) {

        DeviceTeamFactory deviceTeamFactory = null;
        switch (equipmentTypeEnum){
            case ANDROID:
                deviceTeamFactory = SpringContext.getBean(AndroidDeviceImpl.class);
                break;
            case DEVICE:
                deviceTeamFactory = SpringContext.getBean(HardwareDeviceImpl.class);
                break;
            default:
                break;
        }
        return deviceTeamFactory;
    }
}
