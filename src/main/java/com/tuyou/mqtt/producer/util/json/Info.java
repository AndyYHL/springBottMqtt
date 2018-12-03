package com.tuyou.mqtt.producer.util.json;

import lombok.Data;
/**
 * @author yhl
 * 返回主要的提示信息
 */
@Data
public class Info {
    /**
     * 模块
     */
    private String type;
    /**
     * 请求功能
     */
    private String action;
    /**
     * APP校验码
     */
    private String appkey;
    /**
     * APP用户来源
     */
    private String usource;
    /**
     * 用户校验规则
     */
    private String token;
    /**
     * 其它校验码
     */
    private String checkcode;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 需要跳转地址或者请求来源地址等
     */
    private String url;
    /**
     * 处理的结果
     */
    private Integer status;
    /**
     * 附加信息，错误等情况下将原因返回.如果是添加，将新主键保存到此信息
     */
    private String message;
    /**
     * 版本信息。部分数据可能需要版本信息进行判断
     */
    private String version;
}
