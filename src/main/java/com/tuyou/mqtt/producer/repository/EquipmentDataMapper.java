package com.tuyou.mqtt.producer.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuyou.mqtt.producer.pojo.domain.EquipmentDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YangHaiLong
 * @ClassName: EquipmentDataMapper
 * @Description: 设备数据数据操作接口
 * @Date
 */
@Mapper
public interface EquipmentDataMapper extends BaseMapper<EquipmentDataDO> {


}
