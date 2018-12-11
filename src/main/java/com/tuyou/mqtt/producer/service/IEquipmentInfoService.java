package com.tuyou.mqtt.producer.service;

import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentInfoVO;
import com.tuyou.mqtt.producer.util.json.ExtLimit;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author YangHaiLong
 * @ClassName: EquipmentInfoMapper
 * @Description: 设备信息Service操作接口
 * @Date
 */
public interface IEquipmentInfoService{
	/**
     * 新增设备信息
     * @Description 新增设备信息
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应Integer数据
     */
    Integer saveEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 保存非空值
     * @param equipmentInfoDTO
     * @return 响应Integer数据
     */
    Integer saveEquipmentInfoNotNull(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 修改设备信息
     * @Description 修改设备信息
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应boolean数据
     */
    boolean uptEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 删除设备信息
     * @Description 删除设备信息
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应Integer数据
     */
    Integer delEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 查询设备信息详情
     * @Description 查询设备信息详情
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应EquipmentInfoVO数据
     */
    EquipmentInfoVO findEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO);

    /**
     * 查询列表设备信息列表
     * @Description 查询列表设备信息列表 
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应List<equipmentInfoVO>数据
     */
    List<EquipmentInfoVO> findEquipmentInfoList(EquipmentInfoDTO equipmentInfoDTO);

    /**
     *  查询列表设备信息(带分页)
     * @Description 查询列表设备信息(带分页)
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应List<equipmentInfoVO>数据
     */
    List<EquipmentInfoVO> findEquipmentInfoLimit(EquipmentInfoDTO equipmentInfoDTO, ExtLimit extLimit);
}
