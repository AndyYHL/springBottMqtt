package com.tuyou.mqtt.producer.pojo.swagger2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 分页信息实体
 *
 * @author Eugene
 * 2019-01-02 10:15
 */
@ApiModel("分页信息对象")
public class PagingResponse<T> implements Serializable {
    @ApiModelProperty(value = "页码", dataType = "Long")
    private Long currentPage;

    @ApiModelProperty(value = "页面记录数", dataType = "Long")
    private Long pageSize;

    @ApiModelProperty(value = "总页数", dataType = "Long")
    private Long totalPage;

    @ApiModelProperty(value = "总记录数", dataType = "Long")
    private Long totalCount;

    @ApiModelProperty(value = "分页数据列表", required = true, dataType = "List")
    private List<T> list;

    public PagingResponse() {
    }

    public PagingResponse(Long currentPage, Long pageSize, List<T> list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
    }

    public PagingResponse(Long currentPage, Long pageSize, Long totalPage, Long totalCount, List<T> list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.list = list;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
