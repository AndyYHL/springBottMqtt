package com.tuyou.mqtt.producer.pojo.param;

import com.tuyou.mqtt.producer.util.json.ExtLimit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author yhl
 * 请求参数体-获取液位仪请求参数
 */
@Data
@ToString
@NoArgsConstructor
@ApiModel(value="液位仪采集数据请求体",description="液位仪采集数据请求体")
public class EquipmentDataPARAM {
    /**
     * 总站id
     */
    @NotNull(message = "总站id不能为空")
    @Min(value = 1,message = "总站id最小为1")
    @ApiModelProperty(value="总站id",name="enterpriseId",required=true,dataType = "Int")
    private Integer enterpriseId;
    /**
     * 总站名称
     */
    @ApiModelProperty(value="总站名称",name="enterpriseName",dataType = "String")
    private String enterpriseName;
    /**
     * 使用站点
     */
    @ApiModelProperty(value="分站点ID",name="stationId",dataType = "String")
    private Integer stationId;
    /**
     * 使用站点名称
     */
    @ApiModelProperty(value="站点名称",name="stationName",dataType = "String")
    private String stationName;
    /**
     * 商品id
     */
    @ApiModelProperty(value="商品ID",name="productTypeId",dataType = "Int")
    private Integer productTypeId;
    /**
     * 类型名称
     */
    @ApiModelProperty(value="类型名称",name="typeName",dataType = "String")
    private String typeName;
    /**
     * 油品
     */
    @ApiModelProperty(value="油品名称",name="oils",dataType = "String")
    private String oils;
    /**
     * 分页参数
     */
    @NotNull(message = "分页参数不能为空")
    @ApiModelProperty(value="分页参数",name="extLimit",required=true,dataType = "ExtLimit")
    private ExtLimit extLimit;
}
