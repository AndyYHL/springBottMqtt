package com.tuyou.mqtt.producer.service;

import com.tuyou.mqtt.producer.pojo.dto.OiltankDTO;
import com.tuyou.mqtt.producer.pojo.vo.OiltankVO;
import com.tuyou.mqtt.producer.util.json.ExtLimit;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author YangHaiLong
 * @ClassName: OiltankMapper
 * @Description: 油罐信息Service操作接口
 * @Date
 */
public interface IOiltankService{
	/**
     * @Description 新增油罐信息
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应Integer数据
     */
    Integer saveOiltank(OiltankDTO oiltankDTO);

    /**
     * 保存非空值
     * @param oiltankDTO
     * @return 响应Integer数据
     */
    Integer saveOiltankNotNull(OiltankDTO oiltankDTO);

    /**
     * @Description 修改油罐信息
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应boolean数据
     */
    boolean uptOiltank(OiltankDTO oiltankDTO);

    /**
     * @Description 删除油罐信息
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应Integer数据
     */
    Integer delOiltank(OiltankDTO oiltankDTO);

    /**
     * @Description 查询油罐信息详情
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应OiltankVO数据
     */
    OiltankVO findOiltank(OiltankDTO oiltankDTO);

    /**
     * @Description 查询列表油罐信息列表 
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应List<oiltankVO>数据
     */
    List<OiltankVO> findOiltankList(OiltankDTO oiltankDTO);

    /**
     * @Description 查询列表油罐信息(带分页)
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应List<oiltankVO>数据
     */
    List<OiltankVO> findOiltankLimit(OiltankDTO oiltankDTO, ExtLimit extLimit);
}
