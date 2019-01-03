package com.tuyou.mqtt.producer.team.device;

import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;

/**
 * 设备注册渠道
 * @author yhl
 */
public interface DeviceTeamFactory {
    /**
     * 分渠道进行设备注册
     * @param equipmentInfoDTO 注册设备
     * @return 1 注册成功 0 注册失败
     */
     Integer deviceRegister(EquipmentInfoDTO equipmentInfoDTO);
}
