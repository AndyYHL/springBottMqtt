package com.tuyou.mqtt.producer.util.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author yhl
 * 公共 返回参数分页
 */
@Data
@ApiModel(value="分页对象",description="查询列表时的分页对象ExtLimit")
public class ExtLimit {
    /**
     * 开始的条数
     */
    @ApiModelProperty(value="开始条数",name="pagestart",hidden=true)
    private Integer pagestart;
    /**
     * 返回数据条数
     */
    @Min(value = 1,message = "请求条数最小1")
    @ApiModelProperty(value="每页显示的条数",name="pagesize",required=true,dataType = "Int")
    private Integer pagesize;
    /**
     * 当前请求的页数
     */
    @Min(value = 1,message = "请求页面最小为1")
    @ApiModelProperty(value="请求的页数",name="pageindex",required=true,dataType = "Int")
    private Integer pageindex;
    /**
     * 查询结果的总数量
     */
    @ApiModelProperty(value="查下结果返回的总条数",name="count",required=true,dataType = "Int")
    private Integer count;
    /**
     * 排序的字段
     */
    @ApiModelProperty(value="排序的字段",name="sort",hidden=true)
    private String sort;
    /**
     * 排序方式
     */
    @ApiModelProperty(value="排序的方式",name="dir",hidden=true)
    private String dir;
}
