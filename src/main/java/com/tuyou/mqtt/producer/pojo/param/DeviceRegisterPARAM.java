package com.tuyou.mqtt.producer.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 设备注册。硬件发送的请求实体
 *
 * @author yhl
 */
@Data
@ToString
@NoArgsConstructor
@ApiModel(value = "设备注册的请求参数", description = "设备注册的请求参数")
public class DeviceRegisterPARAM {
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", name = "equipmentName", required = true, dataType = "String")
    private String equipmentName;
    /**
     * 设备号
     */
    @ApiModelProperty(value = "设备号", name = "equipmentNo", required = true, dataType = "String")
    private String equipmentNo;
    /**
     * 型号
     */
    @ApiModelProperty(value = "型号", name = "modelNumber", required = true, dataType = "String")
    private String modelNumber;
    /**
     * 制造单位
     */
    @ApiModelProperty(value = "制造单位", name = "unit", dataType = "String")
    private String unit;
    /**
     * 投运时间
     */
    @ApiModelProperty(value = "投运时间", name = "commissioningDate", dataType = "Date")
    private Date commissioningDate;
    /**
     * 设备状态
     */
    @ApiModelProperty(value = "设备状态，1 上线、2 封存、3 闲置、4 报废、5 待修、6 备用", name = "status", dataType = "Integer")
    private Integer status;
    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型，1 硬件设备 2 android设计 3 其他设备", name = "equipmentType", dataType = "Integer")
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
     * 分站
     */
    @ApiModelProperty(value = "分站", name = "stationId", required = true, dataType = "String")
    private String stationId;
    /**
     * 总站
     */
    @ApiModelProperty(value = "总站", name = "enterpriseId", required = true, dataType = "Integer")
    private Integer enterpriseId;
}
