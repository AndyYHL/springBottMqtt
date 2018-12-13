package com.tuyou.mqtt.producer.util.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author yhl
 * 返回主要的提示信息
 */
@Data
@ApiModel(value="返回结果主题",description="主要返回的查询标识，错误信息。Info")
public class Info {
    /**
     * 模块
     */
    @ApiModelProperty(value="请求的模块",name="type",hidden=true)
    private String type;
    /**
     * 请求功能
     */
    @ApiModelProperty(value="需要调用的功能",name="action",hidden=true)
    private String action;
    /**
     * APP校验码
     */
    @ApiModelProperty(value="请求令牌",name="appkey",hidden=true)
    private String appkey;
    /**
     * APP用户来源
     */
    @ApiModelProperty(value="调用结构的来源",name="usource",hidden=true)
    private String usource;
    /**
     * 用户校验规则
     */
    @ApiModelProperty(value="请求头",name="token",hidden=true)
    private String token;
    /**
     * 其它校验码
     */
    @ApiModelProperty(value="签名",name="checkcode",hidden=true)
    private String checkcode;
    /**
     * 时间戳
     */
    @ApiModelProperty(value="时间戳",name="timestamp",required=true,dataType = "Long")
    private String timestamp;
    /**
     * 需要跳转地址或者请求来源地址等
     */
    @ApiModelProperty(value="需要跳转的URL",name="url",hidden=true)
    private String url;
    /**
     * 处理的结果
     */
    @ApiModelProperty(value="状态,200 标识成功",name="status",required=true,dataType = "Int")
    private Integer status;
    /**
     * 附加信息，错误等情况下将原因返回.如果是添加，将新主键保存到此信息
     */
    @ApiModelProperty(value="查询的异常结果",name="message",required=true,dataType = "String")
    private String message;
    /**
     * 版本信息。部分数据可能需要版本信息进行判断
     */
    @ApiModelProperty(value="接口版本",name="version",hidden=true)
    private String version;
}
