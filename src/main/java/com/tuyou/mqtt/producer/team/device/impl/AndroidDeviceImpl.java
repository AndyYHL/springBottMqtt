package com.tuyou.mqtt.producer.team.device.impl;

import com.alibaba.fastjson.JSON;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
import com.tuyou.mqtt.producer.service.IMqttGateway;
import com.tuyou.mqtt.producer.team.device.DeviceTeamFactory;
import com.tuyou.mqtt.producer.util.json.Info;
import lombok.extern.slf4j.Slf4j;
import net.toyou.pojo.swagger2.ResponseResult;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * Android 设备注册
 */
@Slf4j
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
        if (addDeviceCount > 0) {
            if (StringUtils.isNotBlank(equipmentInfoDTO.getEquipmentNo())) {
                ResponseResult result = new ResponseResult();
                result.setCode(HttpStatus.SC_OK);
                // 发送注册消息
                result.setMessage("Android设备号:".concat(equipmentInfoDTO.getEquipmentNo()).concat("注册成功！"));
                iMqttGateway.sendToMqtt(JSON.toJSONString(result), equipmentInfoDTO.getEquipmentNo());
            }
        } else {
            log.info("设备{}注册失败", JSON.toJSONString(equipmentInfoDTO));
        }
        return addDeviceCount;
    }
}
