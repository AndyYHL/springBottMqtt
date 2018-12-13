package com.tuyou.mqtt.producer.util.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author yhl
 * 输出结构化JSON
 *
 */
@Data
@ApiModel(value="返回的结果",description="返回结果体")
public class JsonUtil<D,O> {
    /**
     * 请求参数
     */
    @ApiModelProperty(value="请求参数",name="data",required=true,dataType = "Long")
    private D data;
    /**
     * 公共参数
     */
    @ApiModelProperty(value="返回的信息体",name="info",required=true,dataType = "Long")
    private Info info;
    /**
     * 分页数据
     */
    @ApiModelProperty(value="分页数据",name="extlimit",required=true,dataType = "Long")
    private ExtLimit extlimit;
    /**
     * 其他参数
     */
    @ApiModelProperty(value="其他参数",name="other",required=true,dataType = "Long")
    private O other;
}
