package com.tuyou.mqtt.producer.pojo.vo;

import lombok.Data;

/**
 * @author yhl
 * 导出Excel的标头并且设置列宽
 */
@Data
public class TitlesTtempletVO {
    /**
     * 标题
     */
    private String title;
    /**
     * 列宽
     */
    private Integer columnWidth;
}
