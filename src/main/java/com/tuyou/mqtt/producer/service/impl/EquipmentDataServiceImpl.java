package com.tuyou.mqtt.producer.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuyou.mqtt.producer.pojo.domain.EquipmentDataDO;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.pojo.dto.OiltankDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentInfoVO;
import com.tuyou.mqtt.producer.pojo.vo.OiltankVO;
import com.tuyou.mqtt.producer.repository.EquipmentDataMapper;
import com.tuyou.mqtt.producer.service.IEquipmentDataService;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
import com.tuyou.mqtt.producer.service.IOiltankService;
import com.tuyou.mqtt.producer.util.json.ExtLimit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentDataDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentDataVO;
import org.springframework.beans.BeanUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
    @Autowired
    IOiltankService oiltankService;

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
        EquipmentInfoVO equipmentInfoVO = equipmentInfoService.findEquipmentInfo(equipmentInfoDTO);

        // 如果没有获取设备，直接保存处理最后结果
        EquipmentDataDO equipmentDataDO = new EquipmentDataDO();
        BeanUtils.copyProperties(equipmentDataDTO, equipmentDataDO);

        // 计算 水高、油高、水体积、油体积、温度、库存量
        // 全部计算 缩小
        BigDecimal baseRadix = new BigDecimal(100);
        BigDecimal waterHeight = new BigDecimal(equipmentDataDO.getWaterHeight()).divide(baseRadix);
        BigDecimal oilHeight = new BigDecimal(equipmentDataDO.getWaterHeight()).divide(baseRadix);
        BigDecimal waterVolume = new BigDecimal(equipmentDataDO.getWaterHeight()).divide(baseRadix);
        BigDecimal oilVolume = new BigDecimal(equipmentDataDO.getWaterHeight()).divide(baseRadix);
        BigDecimal temperature = new BigDecimal(equipmentDataDO.getWaterHeight()).divide(baseRadix);
        BigDecimal inventory = new BigDecimal(equipmentDataDO.getWaterHeight()).divide(baseRadix);

        equipmentDataDO.setWaterHeight(waterHeight.toString());
        equipmentDataDO.setOilHeight(oilHeight.toString());
        equipmentDataDO.setWaterVolume(waterVolume.toString());
        equipmentDataDO.setOilVolume(oilVolume.toString());
        equipmentDataDO.setTemperature(temperature.toString());
        equipmentDataDO.setInventory(inventory.toString());
        equipmentDataDO.setUpdateTime(new Date());
        equipmentDataDO.setCreateTime(new Date());

        // 没有获取设备信息，直接进行保存
        if (null == equipmentInfoVO) {
            return super.baseMapper.insert(equipmentDataDO);
        }

        // 已经获取到设备信息，进行设置总站分站保存
        equipmentDataDO.setEquipmentId(equipmentInfoVO.getEquipmentId());
        equipmentDataDO.setEquipmentName(equipmentInfoVO.getEquipmentName());
        equipmentDataDO.setEquipmentNo(equipmentInfoVO.getEquipmentNo());
        equipmentDataDO.setEnterpriseId(equipmentInfoVO.getEnterpriseId());
        equipmentDataDO.setEnterpriseName(equipmentInfoVO.getEnterpriseName());
        equipmentDataDO.setStationId(equipmentInfoVO.getStationId());
        equipmentDataDO.setStationName(equipmentInfoVO.getStationName());

        // 跟油罐表建立关系
        OiltankDTO oiltankDTO = new OiltankDTO();
        oiltankDTO.setOiltankNo(equipmentDataDO.getOiltankNo());
        oiltankDTO.setEnterpriseId(equipmentDataDO.getEnterpriseId());
        oiltankDTO.setEnterpriseName(equipmentDataDO.getEnterpriseName());
        oiltankDTO.setStationId(equipmentDataDO.getStationId());
        oiltankDTO.setStationName(equipmentDataDO.getStationName());
        oiltankDTO.setOiltankName(equipmentDataDO.getEquipmentName());

        // 获取油罐信息
        OiltankVO oiltankVO = oiltankService.findOiltank(oiltankDTO);
        equipmentDataDO.setOiltankId(oiltankVO.getOiltankId());
        equipmentDataDO.setOiltankNo(oiltankVO.getOiltankNo());

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
    public EquipmentDataVO findEquipmentData(EquipmentDataDTO equipmentDataDTO) {
        EquipmentDataVO equipmentDataVO = new EquipmentDataVO();
        EquipmentDataDO equipmentDataDO;
        if (StringUtils.isNotBlank(equipmentDataDTO.getEquipmentNo())) {
            // 判断是否已经存在此设备
            QueryWrapper<EquipmentDataDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(EquipmentDataDO::getEquipmentNo, equipmentDataDTO.getEquipmentNo());
            equipmentDataDO = super.baseMapper.selectOne(queryWrapper);
        } else {
            equipmentDataDO = super.baseMapper.selectById(equipmentDataDTO.getEquipmentId());
        }

        if (null == equipmentDataDO) {
            BeanUtils.copyProperties(equipmentDataDO, equipmentDataVO);
            return equipmentDataVO;
        }
        return null;
    }

    /**
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     * @Description 查询列表设备数据列表 分页可有可无
     */
    @Override
    public List<EquipmentDataVO> findEquipmentDataList(EquipmentDataDTO equipmentDataDTO) {
        QueryWrapper<EquipmentDataDO> queryWrapper = new QueryWrapper<>();
        //lambada 条件
        LambdaQueryWrapper<EquipmentDataDO> lambdaQueryWrapper = queryWrapper.lambda();
        lambdaQueryWrapper.eq(EquipmentDataDO::getEnterpriseId, equipmentDataDTO.getEnterpriseId());
        //站点不为空
        if (null != equipmentDataDTO.getStationId()) {
            lambdaQueryWrapper.eq(EquipmentDataDO::getStationId, equipmentDataDTO.getStationId());
        }
        if (null != equipmentDataDTO.getProductTypeId()) {
            lambdaQueryWrapper.eq(EquipmentDataDO::getProductTypeId, equipmentDataDTO.getProductTypeId());
        }

        queryWrapper.lambda().orderByDesc(EquipmentDataDO::getUpdateTime);
        System.out.println(queryWrapper.getSqlSegment());

        List<EquipmentDataDO> equipmentDataDOList = super.baseMapper.selectList(queryWrapper);
        List<EquipmentDataVO> equipmentDataVOList = JSON.parseArray(JSON.toJSONString(equipmentDataDOList), EquipmentDataVO.class);
        return equipmentDataVOList;
    }

    /**
     * @param equipmentDataDTO 请求equipmentDataDTO数据
     * @return 响应List<equipmentDataVO>数据
     * @Description 查询列表设备数据(带分页)
     */
    @Override
    public List<EquipmentDataVO> findEquipmentDataLimit(EquipmentDataDTO equipmentDataDTO, ExtLimit extLimit) {
        QueryWrapper<EquipmentDataDO> queryWrapper = new QueryWrapper<>();
        //lambada 条件
        LambdaQueryWrapper<EquipmentDataDO> lambdaQueryWrapper = queryWrapper.lambda();
        lambdaQueryWrapper.eq(EquipmentDataDO::getEnterpriseId, equipmentDataDTO.getEnterpriseId());
        //站点不为空
        if (null != equipmentDataDTO.getStationId()) {
            lambdaQueryWrapper.eq(EquipmentDataDO::getStationId, equipmentDataDTO.getStationId());
        }
        if (null != equipmentDataDTO.getProductTypeId()) {
            lambdaQueryWrapper.eq(EquipmentDataDO::getProductTypeId, equipmentDataDTO.getProductTypeId());
        }
        //时间段
        if (null != equipmentDataDTO.getCreateTime() && null != equipmentDataDTO.getCreateTime()) {
            //获取当天最大
            LocalDateTime localDateTime = LocalDateTime.ofInstant(equipmentDataDTO.getCreateTime().toInstant(), ZoneId.systemDefault());
            localDateTime = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
            lambdaQueryWrapper.between(EquipmentDataDO::getCreateTime, equipmentDataDTO.getCreateTime(), localDateTime);
        }

        queryWrapper.lambda().orderByDesc(EquipmentDataDO::getUpdateTime);
        System.out.println(queryWrapper.getSqlSegment());

        List<EquipmentDataVO> equipmentDataVOList = new ArrayList<>();
        if (null == extLimit) {
            List<EquipmentDataDO> equipmentDataDOList = super.baseMapper.selectList(queryWrapper);
            if (equipmentDataDOList.size() > 0) {
                equipmentDataVOList = JSON.parseArray(JSON.toJSONString(equipmentDataDOList), EquipmentDataVO.class);
            }
            return equipmentDataVOList;
        }
        Integer count = super.baseMapper.selectCount(queryWrapper);
        IPage<EquipmentDataDO> equipmentDataDOIPage = new Page<>(extLimit.getPageindex(), extLimit.getPagesize(), count.longValue());
        IPage<EquipmentDataDO> equipmentDataDOList = super.baseMapper.selectPage(equipmentDataDOIPage, queryWrapper);
        if (equipmentDataDOList.getRecords().size() > 0) {
            equipmentDataVOList = JSON.parseArray(JSON.toJSONString(equipmentDataDOList.getRecords()), EquipmentDataVO.class);
        }
        extLimit.setCount(new Long(equipmentDataDOList.getTotal()).intValue());
        return equipmentDataVOList;
    }
}