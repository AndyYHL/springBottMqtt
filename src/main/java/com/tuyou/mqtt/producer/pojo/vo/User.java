package com.tuyou.mqtt.producer.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 */
@Data
public class User implements Serializable {
    String userName;
    String pwd;
}
