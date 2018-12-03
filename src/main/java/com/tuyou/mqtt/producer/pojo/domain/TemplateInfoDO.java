package com.tuyou.mqtt.producer.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 微信模板消息DO
 *
 * @author Administrator
 * 2018-10-30 17:21
 */
@Data
@TableName("wechat_template_info")
public class TemplateInfoDO {
    /**
     * 主键id
     */
    private String id;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 总站id
     */
    private String enterpriseId;

    /**
     * 模板类型： 1 消费 2 会员等级变更 3 积分兑换 4 充值
     */
    private String type;

    /**
     * 状态 -1 停用 0 启用
     */
    private String state;

    /**
     * 模板跳转地址
     */
    private String url;
}
