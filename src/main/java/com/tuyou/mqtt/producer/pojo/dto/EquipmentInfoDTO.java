package com.tuyou.mqtt.producer.pojo.dto;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;

import java.util.Date;

/**
 * 设备信息
 */
@Data
@ToString
@NoArgsConstructor
public class EquipmentInfoDTO {
   /**
     * 主键
     */
    private Integer equipmentId;
   /**
     * 设备名称
     */
    private String equipmentName;
   /**
     * 设备编号
     */
    private String equipmentTypeNo;
   /**
     * 地址
     */
    private String address;
   /**
     * 类别
     */
    private String category;
   /**
     * 型号
     */
    private String modelNumber;
   /**
     * 制造单位
     */
    private String unit;
   /**
     * 投运日期
     */
    private Date commissioningDate;
   /**
     * 购入日期
     */
    private Date purchaseDate;
   /**
     * 产品图片
     */
    private String picture;
   /**
     * 技术数据
     */
    private String technicalData;
   /**
     * 备注
     */
    private String remark;
   /**
     * 设备状态
     */
    private Integer status;
   /**
     * 设备类型
     */
    private Integer equipmentType;
   /**
     * 经度
     */
    private String lng;
   /**
     * 纬度
     */
    private String lat;
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
