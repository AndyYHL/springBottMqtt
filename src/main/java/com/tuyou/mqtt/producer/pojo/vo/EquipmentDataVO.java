package com.tuyou.mqtt.producer.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备数据
 *
 * @author yhl
 */
@Data
@ToString
@NoArgsConstructor
@ApiModel("EquipmentDataVO对象信息")
public class EquipmentDataVO implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "equipmentDataId 主键", dataType = "int")
    private Integer equipmentDataId;
    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID", dataType = "int")
    private Integer equipmentId;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", dataType = "String")
    private String equipmentName;
    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号", dataType = "String")
    private String equipmentNo;
    /**
     * 使用站点
     */
    @ApiModelProperty(value = "使用站点", dataType = "String")
    private String stationId;
    /**
     * 使用站点名称
     */
    @ApiModelProperty(value = "使用站点名称", dataType = "String")
    private String stationName;
    /**
     * 总站id
     */
    @ApiModelProperty(value = "总站id", dataType = "int")
    private Integer enterpriseId;
    /**
     * 总站名称
     */
    @ApiModelProperty(value = "总站名称", dataType = "String")
    private String enterpriseName;
    /**
     * 油罐号ID
     */
    @ApiModelProperty(value = "油罐号ID", dataType = "int")
    private Integer oiltankId;
    /**
     * 油罐号
     */
    @ApiModelProperty(value = "油罐号", dataType = "String")
    private String oiltankNo;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id", dataType = "int")
    private Integer productTypeId;
    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称", dataType = "String")
    private String typeName;
    /**
     * 油品
     */
    @ApiModelProperty(value = "油品", dataType = "String")
    private String oils;
    /**
     * 油高
     */
    @ApiModelProperty(value = "油高", dataType = "String")
    private String oilHeight;
    /**
     * 水高
     */
    @ApiModelProperty(value = "水高", dataType = "String")
    private String waterHeight;
    /**
     * 油体积
     */
    @ApiModelProperty(value = "油体积", dataType = "String")
    private String oilVolume;
    /**
     * 水体积
     */
    @ApiModelProperty(value = "水体积", dataType = "String")
    private String waterVolume;
    /**
     * 温度
     */
    @ApiModelProperty(value = "温度", dataType = "String")
    private String temperature;
    /**
     * 库存量
     */
    @ApiModelProperty(value = "库存量", dataType = "String")
    private String inventory;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", dataType = "Date")
    private Date updateTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    private Date createTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", dataType = "String")
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

