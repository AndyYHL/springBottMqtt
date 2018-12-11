package com.tuyou.mqtt.producer.web.controller;

import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.service.IEquipmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备采集数据
 * @author yhl
 */
@RestController
@RequestMapping(value = ClientApiFinal.version + "equipmentData/")
public class EquipmentDataController {
    @Autowired
    IEquipmentDataService equipmentDataService;
}
