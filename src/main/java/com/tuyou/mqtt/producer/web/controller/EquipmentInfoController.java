package com.tuyou.mqtt.producer.web.controller;

import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备信息
 * @author yhl
 *
 */
@RestController
@RequestMapping(value = ClientApiFinal.version + "equipmentInfo/")
public class EquipmentInfoController {
    @Autowired
    IEquipmentInfoService equipmentInfoService;

}
