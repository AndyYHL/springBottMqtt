package com.tuyou.mqtt.producer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuyou.mqtt.producer.repository.ITemplateMsgMapper;
import com.tuyou.mqtt.producer.pojo.domain.TemplateInfoDO;
import com.tuyou.mqtt.producer.service.ITemplateMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 模板消息业务接口实现
 *
 * @author ryh
 * 2018-10-30 16:41
 */
@Slf4j
@Service
public class TemplateMsgService extends ServiceImpl<ITemplateMsgMapper, TemplateInfoDO> implements ITemplateMsgService {
    @Override
    public Boolean sendTemplateMsg(String openId, String enterpriseId, String type, String dataJson, String userId) {
        return null;
    }
}
