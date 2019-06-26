package com.tuyou.mqtt.producer.service;

import com.tuyou.mqtt.producer.pojo.dto.OiltankDTO;
import com.tuyou.mqtt.producer.pojo.vo.OiltankVO;

import java.util.List;

/**
 * @author YangHaiLong
 * @ClassName: OiltankMapper
 * @Description: 油罐信息Service操作接口
 * @Date
 */
public interface IOiltankService {
    /**
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应Integer数据
     * @Description 新增油罐信息
     */
    Integer saveOiltank(OiltankDTO oiltankDTO);

    /**
     * 保存非空值
     *
     * @param oiltankDTO
     * @return 响应Integer数据
     */
    Integer saveOiltankNotNull(OiltankDTO oiltankDTO);

    /**
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应boolean数据
     * @Description 修改油罐信息
     */
    boolean uptOiltank(OiltankDTO oiltankDTO);

    /**
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应Integer数据
     * @Description 删除油罐信息
     */
    Integer delOiltank(OiltankDTO oiltankDTO);

    /**
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应OiltankVO数据
     * @Description 查询油罐信息详情
     */
    OiltankVO findOiltank(OiltankDTO oiltankDTO);

    /**
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应List<oiltankVO>数据
     * @Description 查询列表油罐信息列表
     */
    List<OiltankVO> findOiltankList(OiltankDTO oiltankDTO);

    /**
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应List<oiltankVO>数据
     * @Description 查询列表油罐信息(带分页)
     */
    List<OiltankVO> findOiltankLimit(OiltankDTO oiltankDTO);
}
