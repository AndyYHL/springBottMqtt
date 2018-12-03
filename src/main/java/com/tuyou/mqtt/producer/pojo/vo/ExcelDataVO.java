package com.tuyou.mqtt.producer.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yhl
 * 导出Excel的数据组合
 */
@Data
public class ExcelDataVO implements Serializable{
    private static final long serialVersionUID = 4444017239100620999L;
    /**
     * 第一行标头
     */
    private String firstTitles;
    /**
     * 第二行备注
     */
    private String remark;
    /**
     * 表头
     */
    private List<TitlesTtempletVO> titles;
    /**
     * 数据
     */
    private List<List<Object>> rows;
    /**
     * 页签名称
     */
    private String sheetName;

}
