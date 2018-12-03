package com.tuyou.mqtt.producer.service;

/**
 * 模板消息业务接口定义
 *
 * @author ryh
 * 2018-10-30 16:33
 */
public interface ITemplateMsgService {

    /**
     * 发送模板消息
     *
     * @param openId       微信用户openId
     * @param enterpriseId 总站id
     * @param type         模板类型：1 消费 2 会员等级变更 3 积分兑换 4 充值
     * @param dataJson     入参json
     * @param userId       用户id
     */
    Boolean sendTemplateMsg(String openId, String enterpriseId, String type, String dataJson, String userId);
}

