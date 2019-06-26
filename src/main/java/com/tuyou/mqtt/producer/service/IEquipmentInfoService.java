package com.tuyou.mqtt.producer.service;

import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentInfoVO;

import java.util.List;

/**
 * @author YangHaiLong
 * @ClassName: EquipmentInfoMapper
 * @Description: 设备信息Service操作接口
 * @Date
 */
public interface IEquipmentInfoService {
    /**
     * 新增设备信息
     *
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应Integer数据
     * @Description 新增设备信息
     */
    Integer saveEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 修改设备信息
     *
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应boolean数据
     * @Description 修改设备信息
     */
    boolean uptEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 删除设备信息
     *
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应Integer数据
     * @Description 删除设备信息
     */
    Integer delEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 根据分站ID来获取设备信息
     *
     * @param stationId 分站ID
     * @return 设备信息
     */
    List<EquipmentInfoVO> getEquipmentInfo(String stationId);

    /**
     * 查询设备信息详情
     *
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应EquipmentInfoVO数据
     * @Description 查询设备信息详情
     */
    EquipmentInfoVO findEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 查询列表设备信息列表
     *
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应List<equipmentInfoVO>数据
     * @Description 查询列表设备信息列表
     */
    List<EquipmentInfoVO> findEquipmentInfoList(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 查询列表设备信息(带分页)
     *
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应List<equipmentInfoVO>数据
     * @Description 查询列表设备信息(带分页)
     */
    List<EquipmentInfoVO> findEquipmentInfoLimit(EquipmentInfoDTO equipmentInfoDTO);
}
