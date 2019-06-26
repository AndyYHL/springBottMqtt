package com.tuyou.mqtt.producer.pojo.swagger2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * API单数据响应实体
 *
 * @author Eugene
 * 2018-12-04 13:38
 */
@ApiModel("API单数据响应对象")
public class DataResponseResult<T> extends ResponseResult {

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据", required = true, dataType = "T")
    private T data;

    public DataResponseResult() {
    }

    public DataResponseResult(T data) {
        this.data = data;
    }

    public DataResponseResult(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public DataResponseResult(Integer code, String message, String version, T data) {
        super(code, message, version);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
