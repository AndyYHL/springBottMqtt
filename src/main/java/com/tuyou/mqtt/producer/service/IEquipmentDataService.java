package com.tuyou.mqtt.producer.service;

import com.tuyou.mqtt.producer.pojo.dto.EquipmentDataDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentDataVO;

import java.util.List;

/**
 * @author YangHaiLong
 * @ClassName: EquipmentDataMapper
 * @Description: 设备数据Service操作接口
 * @Date
 */
public interface IEquipmentDataService {
    /**
     * 新增设备数据
     *
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应Integer数据
     * @Description 新增设备数据
     */
    Integer saveEquipmentData(EquipmentDataDTO equipmentDataDTO);

    /**
     * 保存非空值
     *
     * @param equipmentDataDTO
     * @return 响应Integer数据
     */
    Integer saveEquipmentDataNotNull(EquipmentDataDTO equipmentDataDTO);

    /**
     * 修改设备数据
     *
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应boolean数据
     * @Description 修改设备数据
     */
    boolean uptEquipmentData(EquipmentDataDTO equipmentDataDTO);

    /**
     * 删除设备数据
     *
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应Integer数据
     * @Description 删除设备数据
     */
    Integer delEquipmentData(EquipmentDataDTO equipmentDataDTO);

    /**
     * 查询设备数据详情
     *
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应EquipmentDataVO数据
     * @Description 查询设备数据详情
     */
    EquipmentDataVO findEquipmentData(EquipmentDataDTO equipmentDataDTO);

    /**
     * 查询列表设备数据列表
     *
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     * @Description 查询列表设备数据列表
     */
    List<EquipmentDataVO> findEquipmentDataList(EquipmentDataDTO equipmentDataDTO);

    /**
     * 查询列表设备数据(带分页)
     *
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     * @Description 查询列表设备数据(带分页)
     */
    List<EquipmentDataVO> findEquipmentDataLimit(EquipmentDataDTO equipmentDataDTO);
}
