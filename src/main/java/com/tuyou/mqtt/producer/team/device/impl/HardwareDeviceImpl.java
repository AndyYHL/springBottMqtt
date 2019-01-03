package com.tuyou.mqtt.producer.team.device.impl;

import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
import com.tuyou.mqtt.producer.team.device.DeviceTeamFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * 硬件设备 设备注册
 */
@Component
public class HardwareDeviceImpl implements DeviceTeamFactory {
    @Autowired
    IEquipmentInfoService equipmentInfoService;

    @Override
    public Integer deviceRegister(EquipmentInfoDTO equipmentInfoDTO) {
        // 判断是否已经存在此设备
        Integer addDeviceCount = equipmentInfoService.saveEquipmentInfo(equipmentInfoDTO);
        return addDeviceCount;
    }
}
