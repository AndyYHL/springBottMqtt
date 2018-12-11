package com.tuyou.mqtt.producer.pojo.dto;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;

import java.util.Date;

/**
 * 设备数据
 * @author yhl
 */
@Data
@ToString
@NoArgsConstructor
public class EquipmentDataDTO {
   /**
     * 主键
     */
    private Integer equipmentDataId;
   /**
     * 设备ID
     */
    private Integer equipmentId;
   /**
     * 设备名称
     */
    private String equipmentName;
   /**
     * 设备编号
     */
    private String equipmentNo;
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
     * 油罐号
     */
    private String tankNo;
   /**
     * 商品id
     */
    private Integer productTypeId;
   /**
     * 类型名称
     */
    private String typeName;
   /**
     * 油品
     */
    private String oils;
   /**
     * 油高
     */
    private String oilHeight;
   /**
     * 水高
     */
    private String waterHeight;
   /**
     * 油体积
     */
    private String oilVolume;
   /**
     * 水体积
     */
    private String waterVolume;
   /**
     * 温度
     */
    private String temperature;
   /**
     * 库存量
     */
    private String inventory;
   /**
     * 更新时间
     */
    private Date updateTime;
   /**
     * 创建时间
     */
    private Date createTime;
   /**
     * 备注
     */
    private String remark;
   /**
     * 备用1
     */
    private Integer reserve1;
   /**
     * 备用2
     */
    private Integer reserve2;
   /**
     * 备用3
     */
    private Integer reserve3;
   /**
     * 备用4
     */
    private Integer reserve4;
   /**
     * 备用5
     */
    private Integer reserve5;
   /**
     * 备用6
     */
    private String reserve6;
   /**
     * 备用7
     */
    private String reserve7;
   /**
     * 备用8
     */
    private String reserve8;
   /**
     * 备用9
     */
    private String reserve9;
   /**
     * 备用10
     */
    private String reserve10;
}
