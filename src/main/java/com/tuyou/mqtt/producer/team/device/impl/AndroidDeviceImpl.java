package com.tuyou.mqtt.producer.team.device.impl;

import com.alibaba.fastjson.JSON;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
import com.tuyou.mqtt.producer.service.IMqttGateway;
import com.tuyou.mqtt.producer.team.device.DeviceTeamFactory;
import com.tuyou.mqtt.producer.util.json.Info;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * Android 设备注册
 */
@Component
public class AndroidDeviceImpl implements DeviceTeamFactory {
    @Autowired
    IEquipmentInfoService equipmentInfoService;
    @Autowired
    IMqttGateway iMqttGateway;

    @Override
    public Integer deviceRegister(EquipmentInfoDTO equipmentInfoDTO) {
        // 判断是否已经存在此设备
        Integer addDeviceCount = equipmentInfoService.saveEquipmentInfo(equipmentInfoDTO);

        if (StringUtils.isNotBlank(equipmentInfoDTO.getEquipmentNo())){
            Info info = new Info();
            info.setStatus(HttpStatus.SC_OK);
            // 发送注册消息
            info.setMessage("Android设备号:".concat(equipmentInfoDTO.getEquipmentNo()).concat("注册成功！"));
            iMqttGateway.sendToMqtt(JSON.toJSONString(info), equipmentInfoDTO.getEquipmentNo());
        }

        return addDeviceCount;
    }
}
