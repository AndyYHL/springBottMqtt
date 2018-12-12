package com.tuyou.mqtt.producer.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 油罐信息
 */
@Data
@TableName("oiltank")
@ToString
@NoArgsConstructor
public class OiltankDO {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
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
