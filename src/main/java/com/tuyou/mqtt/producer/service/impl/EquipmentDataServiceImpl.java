package com.tuyou.mqtt.producer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuyou.mqtt.producer.pojo.domain.EquipmentDataDO;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentInfoVO;
import com.tuyou.mqtt.producer.repository.EquipmentDataMapper;
import com.tuyou.mqtt.producer.service.IEquipmentDataService;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
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

/**
 * @author yhl
 * 采集数据操作类
 */
@Service
public class EquipmentDataServiceImpl extends ServiceImpl<EquipmentDataMapper, EquipmentDataDO> implements IEquipmentDataService {
    /**
     * 采集数据处理
     */
    @Autowired
    IEquipmentInfoService equipmentInfoService;
    /**
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应Integer数据
     * @Description 新增设备数据
     */
    @Override
    public Integer saveEquipmentData(EquipmentDataDTO equipmentDataDTO) {
        // 根据设备编号获取设备信息
        EquipmentInfoDTO equipmentInfoDTO = new EquipmentInfoDTO();
        equipmentInfoDTO.setEquipmentNo(equipmentDataDTO.getEquipmentNo());
        EquipmentInfoDTO equipmentInfo = equipmentInfoService.findEquipmentInfo(equipmentInfoDTO);

        // 如果没有获取设备，直接保存处理最后结果
        EquipmentDataDO equipmentDataDO = new EquipmentDataDO();
        BeanUtils.copyProperties(equipmentDataDTO,equipmentDataDO);

        // 没有获取设备信息，直接进行保存
        if (null == equipmentInfo){
            return super.baseMapper.insert(equipmentDataDO);
        }

        // 已经获取到设备信息，进行设置总站分站保存
        equipmentDataDO.setEquipmentId(equipmentInfo.getEquipmentId());
        equipmentDataDO.setEquipmentName(equipmentInfo.getEquipmentName());
        equipmentDataDO.setEquipmentNo(equipmentInfo.getEquipmentNo());
        equipmentDataDO.setEnterpriseId(equipmentInfo.getEnterpriseId());
        equipmentDataDO.setEnterpriseName(equipmentInfo.getEnterpriseName());
        equipmentDataDO.setStationId(equipmentInfo.getStationId());
        equipmentDataDO.setStationName(equipmentInfo.getStationName());
        equipmentDataDO.setUpdateTime(new Date());
        equipmentDataDO.setCreateTime(new Date());
        return super.baseMapper.insert(equipmentDataDO);
    }

    /**
     * 保存非空值
     *
     * @param equipmentDataDTO
     * @return 响应Integer数据
     */
    @Override
    public Integer saveEquipmentDataNotNull(EquipmentDataDTO equipmentDataDTO) {
        return 0;
    }

    /**
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应boolean数据
     * @Description 修改设备数据
     */
    @Override
    public boolean uptEquipmentData(EquipmentDataDTO equipmentDataDTO) {
        return false;
    }

    /**
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应Integer数据
     * @Description 删除设备数据
     */
    @Override
    public Integer delEquipmentData(EquipmentDataDTO equipmentDataDTO) {
        return 0;
    }

    /**
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应EquipmentDataVO数据
     * @Description 查询设备数据详情
     */
    @Override
    public EquipmentDataDTO findEquipmentData(EquipmentDataDTO equipmentDataDTO) {
        return null;
    }

    /**
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     * @Description 查询列表设备数据列表 分页可有可无
     */
    @Override
    public List<EquipmentDataDTO> findEquipmentDataList(EquipmentDataDTO equipmentDataDTO) {
        return null;
    }

    /**
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     * @Description 查询列表设备数据(带分页)
     */
    @Override
    public List<EquipmentDataDTO> findEquipmentDataLimit(EquipmentDataDTO equipmentDataDTO, ExtLimit extLimit) {
        return null;
    }
}