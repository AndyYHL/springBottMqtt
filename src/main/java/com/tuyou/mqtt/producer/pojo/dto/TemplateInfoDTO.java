package com.tuyou.mqtt.producer.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 微信模板消息DO
 *
 * @author Administrator
 * 2018-10-30 17:21
 */
@Data
@NoArgsConstructor
public class TemplateInfoDTO {
    /**
     * 主键id
     */
    @NotNull(message = "id不能为空")
    private String id;

    /**
     * 模板id
     */
    @NotNull(message = "id不能为空")
    private String templateId;

    /**
     * 总站id
     */
    @NotNull(message = "总站id不能为空")
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
