package com.tuyou.mqtt.producer.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuyou.mqtt.producer.enumeration.EquipmentTypeEnum;
import com.tuyou.mqtt.producer.pojo.domain.EquipmentDataDO;
import com.tuyou.mqtt.producer.pojo.domain.EquipmentInfoDO;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentDataDTO;
import com.tuyou.mqtt.producer.repository.EquipmentInfoMapper;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
import com.tuyou.mqtt.producer.service.IMqttGateway;
import com.tuyou.mqtt.producer.util.json.ExtLimit;
import com.tuyou.mqtt.producer.util.json.Info;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentInfoVO;
import org.springframework.beans.BeanUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yhl
 * 设备操作操作类
 */
@Slf4j
@Service
public class EquipmentInfoServiceImpl extends ServiceImpl<EquipmentInfoMapper, EquipmentInfoDO> implements IEquipmentInfoService {
    @Autowired
    IMqttGateway iMqttGateway;

    /**
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应Integer数据
     * @Description 新增设备信息
     */
    @Override
    public Integer saveEquipmentInfo(EquipmentInfoDTO equipmentInfoDTO) {
        // 判断是否已经存在此设备
        QueryWrapper<EquipmentInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EquipmentInfoDO::getEquipmentNo, equipmentInfoDTO.getEquipmentNo());
        log.info("执行的sql:{}",queryWrapper.getSqlSegment());
        Integer selectCount = super.baseMapper.selectCount(queryWrapper);
        if (selectCount == 0) {
            // 不存在，进行保存
            EquipmentInfoDO equipmentInfoDO = new EquipmentInfoDO();
            BeanUtils.copyProperties(equipmentInfoDTO, equipmentInfoDO);
            equipmentInfoDO.setCreatedTime(new Date());
            Integer addCount = super.baseMapper.insert(equipmentInfoDO);
            log.info("设备注册成功{}", addCount);
            if (equipmentInfoDTO.getEquipmentType().equals(EquipmentTypeEnum.ANDROID.getEquipmentType())) {
                if (StringUtils.isNotBlank(equipmentInfoDTO.getEquipmentNo())){
                    Info info = new Info();
                    info.setStatus(HttpStatus.SC_OK);
                    info.setMessage("设备号:".concat(equipmentInfoDTO.getEquipmentNo()).concat("注册成功！"));
                    iMqttGateway.sendToMqtt(JSON.toJSONString(info), equipmentInfoDTO.getEquipmentNo());
                }
            }
            return addCount;
        }
        log.info("设备数据重复。无法进行添加。设备NO:{}", selectCount);
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
        EquipmentInfoDO equipmentInfoDO = new EquipmentInfoDO();
        BeanUtils.copyProperties(equipmentInfoDTO, equipmentInfoDO);

        int uptCount = super.baseMapper.updateById(equipmentInfoDO);
        if (uptCount == 0) {
            return false;
        }
        return true;
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
        EquipmentInfoVO equipmentInfoVO = new EquipmentInfoVO();
        EquipmentInfoDO equipmentInfoDO = null;

        if (StringUtils.isNotBlank(equipmentInfoDTO.getEquipmentNo())) {
            // 判断是否已经存在此设备
            QueryWrapper<EquipmentInfoDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(EquipmentInfoDO::getEquipmentNo, equipmentInfoDTO.getEquipmentNo());
            equipmentInfoDO = super.baseMapper.selectOne(queryWrapper);
        }

        if (null == equipmentInfoDO) {
            return null;
        }
        BeanUtils.copyProperties(equipmentInfoDO, equipmentInfoVO);
        return equipmentInfoVO;
    }

    /**
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应List<equipmentInfoVO>数据
     * @Description 查询列表设备信息列表 分页可有可无
     */
    @Override
    public List<EquipmentInfoVO> findEquipmentInfoList(EquipmentInfoDTO equipmentInfoDTO) {
        QueryWrapper<EquipmentInfoDO> queryWrapper = new QueryWrapper<>();
        // lambada 条件
        LambdaQueryWrapper<EquipmentInfoDO> lambdaQueryWrapper = queryWrapper.lambda();
        lambdaQueryWrapper.eq(EquipmentInfoDO::getEnterpriseId, equipmentInfoDTO.getEnterpriseId());

        // 站点不为空
        if (null != equipmentInfoDTO.getStationId()) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getStationId, equipmentInfoDTO.getStationId());
        }
        // 设备编号
        if (StringUtils.isNotBlank(equipmentInfoDTO.getEquipmentNo())) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getEquipmentNo, equipmentInfoDTO.getEquipmentNo());
        }
        // 设备名称
        if (StringUtils.isNotBlank(equipmentInfoDTO.getEquipmentName())) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getEquipmentName, equipmentInfoDTO.getEquipmentName());
        }
        // 设备状态
        if (null != equipmentInfoDTO.getStatus()) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getStatus, equipmentInfoDTO.getStatus());
        }
        // 设备类型
        if (null != equipmentInfoDTO.getEquipmentType()) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getEquipmentType, equipmentInfoDTO.getEquipmentType());
        }

        queryWrapper.lambda().orderByDesc(EquipmentInfoDO::getCreatedTime);
        System.out.println(queryWrapper.getSqlSegment());

        List<EquipmentInfoDO> equipmentInfoDOList = super.baseMapper.selectList(queryWrapper);
        List<EquipmentInfoVO> equipmentInfoVOList = JSON.parseArray(JSON.toJSONString(equipmentInfoDOList), EquipmentInfoVO.class);
        return equipmentInfoVOList;
    }

    /**
     * @param equipmentInfoDTO 请求equipmentInfoDTO数据
     * @return 响应List<equipmentInfoVO>数据
     * @Description 查询列表设备信息(带分页)
     */
    @Override
    public List<EquipmentInfoVO> findEquipmentInfoLimit(EquipmentInfoDTO equipmentInfoDTO, ExtLimit extLimit) {
        QueryWrapper<EquipmentInfoDO> queryWrapper = new QueryWrapper<>();
        // lambada 条件
        LambdaQueryWrapper<EquipmentInfoDO> lambdaQueryWrapper = queryWrapper.lambda();
        lambdaQueryWrapper.eq(EquipmentInfoDO::getEnterpriseId, equipmentInfoDTO.getEnterpriseId());

        // 站点不为空
        if (null != equipmentInfoDTO.getStationId()) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getStationId, equipmentInfoDTO.getStationId());
        }
        // 设备编号
        if (StringUtils.isNotBlank(equipmentInfoDTO.getEquipmentNo())) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getEquipmentNo, equipmentInfoDTO.getEquipmentNo());
        }
        // 设备名称
        if (StringUtils.isNotBlank(equipmentInfoDTO.getEquipmentName())) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getEquipmentName, equipmentInfoDTO.getEquipmentName());
        }
        // 设备状态
        if (null != equipmentInfoDTO.getStatus()) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getStatus, equipmentInfoDTO.getStatus());
        }
        // 设备类型
        if (null != equipmentInfoDTO.getEquipmentType()) {
            lambdaQueryWrapper.eq(EquipmentInfoDO::getEquipmentType, equipmentInfoDTO.getEquipmentType());
        }

        queryWrapper.lambda().orderByDesc(EquipmentInfoDO::getCreatedTime);
        System.out.println(queryWrapper.getSqlSegment());

        List<EquipmentInfoVO> equipmentInfoVOList = new ArrayList<>();
        if (null == extLimit) {
            List<EquipmentInfoDO> equipmentInfoDOList = super.baseMapper.selectList(queryWrapper);
            if (equipmentInfoDOList.size() > 0) {
                equipmentInfoVOList = JSON.parseArray(JSON.toJSONString(equipmentInfoDOList), EquipmentInfoVO.class);
            }
            return equipmentInfoVOList;
        }

        Integer count = super.baseMapper.selectCount(queryWrapper);
        IPage<EquipmentInfoDO> equipmentInfoDOIPage = new Page<>(extLimit.getPageindex(), extLimit.getPagesize(), count.longValue());
        IPage<EquipmentInfoDO> equipmentInfoDOList = super.baseMapper.selectPage(equipmentInfoDOIPage, queryWrapper);
        if (equipmentInfoDOList.getRecords().size() > 0) {
            equipmentInfoVOList = JSON.parseArray(JSON.toJSONString(equipmentInfoDOList.getRecords()), EquipmentInfoVO.class);
        }
        extLimit.setCount(new Long(equipmentInfoDOList.getTotal()).intValue());
        return equipmentInfoVOList;
    }
}