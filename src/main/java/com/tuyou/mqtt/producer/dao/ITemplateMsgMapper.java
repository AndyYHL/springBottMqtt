package com.tuyou.mqtt.producer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuyou.mqtt.producer.pojo.domain.TemplateInfoDO;
import org.apache.ibatis.annotations.Param;
/**
 * 模板消息Mapper定义
 *
 * @author Administrator
 * 2018-10-30 17:19
 */

public interface ITemplateMsgMapper extends BaseMapper<TemplateInfoDO> {

    /**
     * 通过总站id和类型查询模板信息
     *
     * @param enterpriseId 总站id
     * @param type         模板类型
     * @return
     */
    TemplateInfoDO selectByEnterpriseId(@Param("enterpriseId") String enterpriseId, @Param("type") String type);
}
