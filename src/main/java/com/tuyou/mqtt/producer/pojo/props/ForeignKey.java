package com.tuyou.mqtt.producer.pojo.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 对外请求的私钥
 *
 * @author yhl
 * 2018-11-16 10:10
 */
@Component
@Data
public class ForeignKey {

    /**
     * 请求的是私钥
     */
    @Value("${foreign.privatekey}")
    private String privateKey;
}

