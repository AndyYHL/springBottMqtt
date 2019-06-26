package com.tuyou.mqtt.producer.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuyou.mqtt.producer.pojo.domain.EquipmentInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YangHaiLong
 * @ClassName: EquipmentInfoMapper
 * @Description: 设备信息数据操作接口
 * @Date
 */
@Mapper
public interface EquipmentInfoMapper extends BaseMapper<EquipmentInfoDO> {


}
