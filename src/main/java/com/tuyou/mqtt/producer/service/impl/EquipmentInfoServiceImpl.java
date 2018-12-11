package com.tuyou.mqtt.producer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuyou.mqtt.producer.pojo.domain.EquipmentInfoDO;
import com.tuyou.mqtt.producer.repository.EquipmentInfoMapper;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
import com.tuyou.mqtt.producer.util.json.ExtLimit;
import org.springframework.beans.factory.annotation.Autowired;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentInfoVO;
import org.springframework.beans.BeanUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yhl
 * 设备操作操作类
 */
@Service
public class EquipmentInfoServiceImpl extends ServiceImpl<EquipmentInfoMapper, EquipmentInfoDO> implements IEquipmentInfoService {
    /**
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应Integer数据
     * @Description 新增设备信息
     */
    @Override
    public Integer saveEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO) {
        return 0;
    }

    /**
     * 保存非空值
     *
     * @param equipmentInfoDTO
     * @return 响应Integer数据
     */
    @Override
    public Integer saveEquipmentInfoNotNull(EquipmentInfoDTO equipmentInfoDTO) {
        return 0;
    }

    /**
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应boolean数据
     * @Description 修改设备信息
     */
    @Override
    public boolean uptEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO) {
        return false;
    }

    /**
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应Integer数据
     * @Description 删除设备信息
     */
    @Override
    public Integer delEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO) {
        return 0;
    }

    /**
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应EquipmentInfoVO数据
     * @Description 查询设备信息详情
     */
    @Override
    public EquipmentInfoVO findEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO) {
        return null;
    }

    /**
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应List<equipmentInfoVO>数据
     * @Description 查询列表设备信息列表 分页可有可无
     */
    @Override
    public List<EquipmentInfoVO> findEquipmentInfoList(EquipmentInfoDTO equipmentInfoDTO) {
        return null;
    }

    /**
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应List<equipmentInfoVO>数据
     * @Description 查询列表设备信息(带分页)
     */
    @Override
    public List<EquipmentInfoVO> findEquipmentInfoLimit(EquipmentInfoDTO equipmentInfoDTO, ExtLimit extLimit) {
        return null;
    }
}