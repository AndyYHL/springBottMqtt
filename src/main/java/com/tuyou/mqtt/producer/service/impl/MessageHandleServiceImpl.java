package com.tuyou.mqtt.producer.service.impl;

import com.alibaba.fastjson.JSON;
import com.tuyou.mqtt.producer.enumeration.EquipmentTypeEnum;
import com.tuyou.mqtt.producer.enumeration.TopicEnum;
import com.tuyou.mqtt.producer.factory.DeviceAbstractFactory;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentDataDTO;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.service.IEquipmentDataService;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
import com.tuyou.mqtt.producer.service.IMessageHandleService;
import com.tuyou.mqtt.producer.team.device.DeviceTeamFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息处理
 *
 * @author yhl
 */
@Slf4j
@Service
public class MessageHandleServiceImpl implements IMessageHandleService {
    /**
     * 设备信息
     */
    @Autowired
    IEquipmentInfoService equipmentInfoService;
    /**
     * 设备采集的数据
     */
    @Autowired
    IEquipmentDataService equipmentDataService;
    @Autowired
    DeviceAbstractFactory deviceAbstractFactory;

    @Override
    public void MessageHandle(String topic, String message) {
        if (StringUtils.isNotBlank(message)) {
            try {
                if (TopicEnum.DEVICEREGISTER.getDescription().equalsIgnoreCase(topic)) {
                    log.info("设备注册，主题:{},内容:{}", topic, message);
                    EquipmentInfoDTO equipmentInfoDTO = JSON.parseObject(message, EquipmentInfoDTO.class);
                    //equipmentInfoService.saveEquipmentInfo(equipmentInfoDTO);

                    if (equipmentInfoDTO.getEquipmentType().equals(EquipmentTypeEnum.ANDROID.getEquipmentType())) {
                        DeviceTeamFactory deviceTeamFactory = deviceAbstractFactory.getDeviceTeam(EquipmentTypeEnum.ANDROID);
                        deviceTeamFactory.deviceRegister(equipmentInfoDTO);
                    }else if (equipmentInfoDTO.getEquipmentType().equals(EquipmentTypeEnum.DEVICE.getEquipmentType())){
                        DeviceTeamFactory deviceTeamFactory = deviceAbstractFactory.getDeviceTeam(EquipmentTypeEnum.DEVICE);
                        deviceTeamFactory.deviceRegister(equipmentInfoDTO);
                    }
                } else if (TopicEnum.OILTANKDATA.getDescription().equalsIgnoreCase(topic)) {
                    log.info("设备采集数据，主题:{},内容:{}", topic, message);
                    EquipmentDataDTO equipmentDataDTO = JSON.parseObject(message,EquipmentDataDTO.class);
                    equipmentDataService.saveEquipmentData(equipmentDataDTO);
                }
            } catch (Exception ex) {
                log.info("消息处理异常：主题:{},内容:{},异常内容:{}", topic, message, ex.getMessage());
            }
        } else {
            log.info("数据为空，不进行处理，主题:{},内容:{}", topic, message);
        }
    }
}
