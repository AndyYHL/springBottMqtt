package com.tuyou.mqtt.producer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuyou.mqtt.producer.pojo.domain.EquipmentDataDO;
import com.tuyou.mqtt.producer.repository.EquipmentDataMapper;
import com.tuyou.mqtt.producer.service.IEquipmentDataService;
import com.tuyou.mqtt.producer.util.json.ExtLimit;
import org.springframework.beans.factory.annotation.Autowired;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentDataDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentDataVO;
import org.springframework.beans.BeanUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentDataService extends ServiceImpl<EquipmentDataMapper, EquipmentDataDO> implements IEquipmentDataService {
/**
     * @Description 新增设备数据
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应Integer数据
     */
    @Override
    public Integer saveEquipmentData(EquipmentDataDTO equipmentDataDTO){
    	return 0;
    }

    /**
     * 保存非空值
     * @param equipmentDataDTO
     * @return 响应Integer数据
     */
    @Override
    public Integer saveEquipmentDataNotNull(EquipmentDataDTO equipmentDataDTO){
    	return 0;
    }

    /**
     * @Description 修改设备数据
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应boolean数据
     */
    @Override
    public boolean uptEquipmentData(EquipmentDataDTO equipmentDataDTO){
    	return false;
    }

    /**
     * @Description 删除设备数据
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应Integer数据
     */
    @Override
    public Integer delEquipmentData(EquipmentDataDTO equipmentDataDTO){
    	return 0;
    }

    /**
     * @Description 查询设备数据详情
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应EquipmentDataVO数据
     */
    @Override
    public EquipmentDataVO findEquipmentData(EquipmentDataDTO equipmentDataDTO){
    	return null;
    }

    /**
     * @Description 查询列表设备数据列表 分页可有可无
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     */
    @Override
    public List<EquipmentDataVO> findEquipmentDataList(EquipmentDataDTO equipmentDataDTO){
    	return null;
    }

    /**
     * @Description 查询列表设备数据(带分页)
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     */
    @Override
    public List<EquipmentDataVO> findEquipmentDataLimit(EquipmentDataDTO equipmentDataDTO, ExtLimit extLimit){
    	return null;
    }
}