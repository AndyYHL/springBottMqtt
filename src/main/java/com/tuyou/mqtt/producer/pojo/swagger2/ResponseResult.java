package com.tuyou.mqtt.producer.pojo.swagger2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * API接口基础信息响应实体
 *
 * @author Eugene
 * 2018-12-04 14:06
 */
@ApiModel("API接口基础信息响应对象")
public class ResponseResult implements Serializable {

    public static final String MESSAGE_SUCESS = "success";

    public static final String MESSAGE_FAILURE = "failure";

    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应码", required = true, dataType = "Integer")
    private Integer code;

    /**
     * 响应描述消息
     */
    @ApiModelProperty(value = "响应消息", required = true, dataType = "String")
    private String message;

    /**
     * API版本
     */
    @ApiModelProperty(value = "响应版本", dataType = "String")
    private String version;

    public ResponseResult() {
    }


    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, String message, String version) {
        this.code = code;
        this.message = message;
        this.version = version;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
