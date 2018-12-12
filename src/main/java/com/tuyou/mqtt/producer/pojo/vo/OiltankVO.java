package com.tuyou.mqtt.producer.pojo.vo;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;

import java.util.Date;

/**
 * 油罐信息
 */
@Data
@ToString
@NoArgsConstructor
public class OiltankVO {
    /**
     * 主键
     */
    private Integer oiltankId;
    /**
     * 油罐号
     */
    private String oiltankNo;
    /**
     * 油罐名称
     */
    private String oiltankName;
    /**
     * 使用站点
     */
    private Integer stationId;
    /**
     * 使用站点名称
     */
    private String stationName;
    /**
     * 总站id
     */
    private Integer enterpriseId;
    /**
     * 总站名称
     */
    private String enterpriseName;
    /**
     * 创建者
     */
    private Integer createdId;
    /**
     * 创建者名称
     */
    private String createdName;
    /**
     * 创建时间
     */
    private Date createdTime;
}

