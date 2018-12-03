package com.tuyou.mqtt.producer.util.json;

import lombok.Data;
/**
 * @author yhl
 * 输出结构化JSON
 *
 */
@Data
public class JsonUtil {
    /**
     * 请求参数
     */
    private Object data;
    /**
     * 公共参数
     */

    private Info info;
    /**
     * 分页数据
     */
    private ExtLimit extlimit;
    /**
     * 其他参数
     */
    private Object other;
}
