package com.tuyou.mqtt.producer.pojo.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述类的功能
 *
 * @author Administrator
 * 2019-06-20 17:02
 */
@Data
public class HttpClientResult implements Serializable {
    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应数据
     */
    private String content;

    /**
     * 构造函数
     *
     * @param code 状态码
     */
    public HttpClientResult(Integer code) {
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code    状态码
     * @param content 返回的结果
     */
    public HttpClientResult(Integer code, String content) {
        this.code = code;
        this.content = content;
    }
}
