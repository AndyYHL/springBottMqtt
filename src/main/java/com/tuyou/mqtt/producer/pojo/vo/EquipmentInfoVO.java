package com.tuyou.mqtt.producer.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;

import java.util.Date;

/**
 * 设备信息
 * @author yhl
 */
@Data
@ToString
@NoArgsConstructor
@ApiModel(value = "设备信息(EquipmentInfoVO)", description = "设备信息(EquipmentInfoVO)")
public class EquipmentInfoVO {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "equipmentId", dataType = "Integer")
    private Integer equipmentId;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", name = "equipmentName", dataType = "String")
    private String equipmentName;
    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号", name = "equipmentNo", dataType = "String")
    private String equipmentNo;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", name = "address", dataType = "String")
    private String address;
    /**
     * 类别
     */
    @ApiModelProperty(value = "类别", name = "category", dataType = "String")
    private String category;
    /**
     * 型号
     */
    @ApiModelProperty(value = "型号", name = "modelNumber", dataType = "String")
    private String modelNumber;
    /**
     * 制造单位
     */
    @ApiModelProperty(value = "制造单位", name = "unit", dataType = "String")
    private String unit;
    /**
     * 投运日期
     */
    @ApiModelProperty(value = "投运日期", name = "commissioningDate", dataType = "Date")
    private Date commissioningDate;
    /**
     * 购入日期
     */
    @ApiModelProperty(value = "购入日期", name = "purchaseDate", dataType = "Date")
    private Date purchaseDate;
    /**
     * 产品图片
     */
    @ApiModelProperty(value = "产品图片", name = "picture", dataType = "String")
    private String picture;
    /**
     * 技术数据
     */
    @ApiModelProperty(value = "技术数据", name = "technicalData", dataType = "String")
    private String technicalData;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", dataType = "String")
    private String remark;
    /**
     * 设备状态
     */
    @ApiModelProperty(value = "设备状态（ 1 上线、2 封存、3 闲置、4 报废、5 待修、6 备用）", name = "status", dataType = "String")
    private Integer status;
    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型1 硬件设备 2 android设计 3 其他设备", name = "equipmentType", dataType = "String")
    private Integer equipmentType;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", name = "lng", dataType = "String")
    private String lng;
    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", name = "lat", dataType = "String")
    private String lat;
    /**
     * 使用站点
     */
    @ApiModelProperty(value = "使用站点", name = "stationId", dataType = "String")
    private String stationId;
    /**
     * 使用站点名称
     */
    @ApiModelProperty(value = "使用站点名称", name = "stationName", dataType = "String")
    private String stationName;
    /**
     * 总站id
     */
    @ApiModelProperty(value = "总站id", name = "enterpriseId", dataType = "Integer")
    private Integer enterpriseId;
    /**
     * 总站名称
     */
    @ApiModelProperty(value = "总站名称", name = "enterpriseName", dataType = "String")
    private String enterpriseName;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者", name = "createdId", dataType = "Integer")
    private Integer createdId;
    /**
     * 创建者名称
     */
    @ApiModelProperty(value = "创建者名称", name = "createdName", dataType = "String")
    private String createdName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createdTime", dataType = "Date")
    private Date createdTime;
}

