package com.tuyou.mqtt.producer.util.json;

import lombok.Data;

/**
 * @author yhl
 * 公共 返回参数分页
 */
@Data
public class ExtLimit {
    /**
     * 开始的条数
     */
    private Integer pagestart;
    /**
     * 返回数据条数
     */
    private int pagesize;
    /**
     * 当前请求的页数
     */
    private int pageindex;
    /**
     * 查询结果的总数量
     */
    private Integer count;
    /**
     * 排序的字段
     */
    private String sort;
    /**
     * 排序方式
     */
    private String dir;
}
