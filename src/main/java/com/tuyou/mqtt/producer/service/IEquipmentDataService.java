package com.tuyou.mqtt.producer.service;

import com.tuyou.mqtt.producer.pojo.dto.EquipmentDataDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentDataVO;
import com.tuyou.mqtt.producer.util.json.ExtLimit;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author YangHaiLong
 * @ClassName: EquipmentDataMapper
 * @Description: 设备数据Service操作接口
 * @Date
 */
public interface IEquipmentDataService{
	/**
     * @Description 新增设备数据
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应Integer数据
     */
    Integer saveEquipmentData(EquipmentDataDTO equipmentDataDTO);

    /**
     * 保存非空值
     * @param equipmentDataDTO
     * @return 响应Integer数据
     */
    Integer saveEquipmentDataNotNull(EquipmentDataDTO equipmentDataDTO);

    /**
     * @Description 修改设备数据
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应boolean数据
     */
    boolean uptEquipmentData(EquipmentDataDTO equipmentDataDTO);

    /**
     * @Description 删除设备数据
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应Integer数据
     */
    Integer delEquipmentData(EquipmentDataDTO equipmentDataDTO);

    /**
     * @Description 查询设备数据详情
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应EquipmentDataVO数据
     */
    EquipmentDataDTO findEquipmentData(EquipmentDataDTO equipmentDataDTO);

    /**
     * @Description 查询列表设备数据列表 
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     */
    List<EquipmentDataDTO> findEquipmentDataList(EquipmentDataDTO equipmentDataDTO);

    /**
     * @Description 查询列表设备数据(带分页)
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     */
    List<EquipmentDataDTO> findEquipmentDataLimit(EquipmentDataDTO equipmentDataDTO, ExtLimit extLimit);
}
