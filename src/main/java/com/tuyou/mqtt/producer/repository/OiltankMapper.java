package com.tuyou.mqtt.producer.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuyou.mqtt.producer.pojo.domain.OiltankDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YangHaiLong
 * @ClassName: OiltankMapper
 * @Description: 油罐信息数据操作接口
 * @Date
 */
@Mapper
public interface OiltankMapper extends BaseMapper<OiltankDO> {


}
