package com.tuyou.mqtt.producer.pojo.swagger2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * API分页数据响应实体
 *
 * @author Eugene
 * 2019-01-02 10:27
 */
@ApiModel("API分页数据响应对象")
public class PagingResponseResult<T> extends ResponseResult {

    @ApiModelProperty(value = "分页响应数据", required = true, dataType = "List")
    private PagingResponse<T> paging;

    public PagingResponseResult() {
    }

    public PagingResponseResult(PagingResponse<T> paging) {
        this.paging = paging;
    }

    public PagingResponseResult(Integer code, String message, PagingResponse<T> paging) {
        super(code, message);
        this.paging = paging;
    }

    public PagingResponseResult(Integer code, String message, String version, PagingResponse<T> paging) {
        super(code, message, version);
        this.paging = paging;
    }

    public PagingResponse<T> getPaging() {
        return paging;
    }

    public void setPaging(PagingResponse<T> paging) {
        this.paging = paging;
    }
}
