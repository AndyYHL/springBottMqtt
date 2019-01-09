package com.tuyou.mqtt.producer.web.controller;

import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentInfoVO;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
import io.swagger.annotations.Api;
import net.toyou.pojo.swagger2.DataResponseResult;
import net.toyou.pojo.swagger2.PagingResponse;
import net.toyou.pojo.swagger2.PagingResponseResult;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 设备信息
 * @author yhl
 *
 */
@Api(tags = "设备信息",value = "设备操作类")
@RestController
@RequestMapping(value = ClientApiFinal.version + "equipmentInfo/")
public class EquipmentInfoController {
    @Autowired
    IEquipmentInfoService equipmentInfoService;

    /**
     * 获取根据站点获取设备信息
     * @param stationId
     */
    @GetMapping(value = "/{stationId}")
    public DataResponseResult<List<EquipmentInfoVO>> getEquipmentInfo(@PathVariable("stationId") String stationId){

        DataResponseResult<List<EquipmentInfoVO>> dataResponseResult = new DataResponseResult<>();

        if (StringUtils.isBlank(stationId)){
            dataResponseResult.setCode(HttpStatus.SC_BAD_REQUEST);
            dataResponseResult.setMessage("请求站点为空");
        }
        List<EquipmentInfoVO> equipmentInfoVOList = this.equipmentInfoService.getEquipmentInfo(stationId);
        dataResponseResult.setData(equipmentInfoVOList);
        dataResponseResult.setCode(HttpStatus.SC_OK);
        dataResponseResult.setMessage("获取成功");
        return dataResponseResult;
    }
}
