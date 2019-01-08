package com.tuyou.mqtt.producer.web.controller;

import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentInfoVO;
import com.tuyou.mqtt.producer.service.IEquipmentInfoService;
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
    public PagingResponseResult<EquipmentInfoVO> getEquipmentInfo(@PathVariable("stationId") String stationId){

        PagingResponseResult<EquipmentInfoVO> pagingResponseResult = new PagingResponseResult<>();
        PagingResponse<EquipmentInfoVO> pagingResponse = new PagingResponse<>();

        if (StringUtils.isBlank(stationId)){
            pagingResponseResult.setCode(HttpStatus.SC_BAD_REQUEST);
            pagingResponseResult.setMessage("请求站点为空");
        }
        List<EquipmentInfoVO> equipmentInfoVOList = this.equipmentInfoService.getEquipmentInfo(stationId);
        pagingResponse.setList(equipmentInfoVOList);

        pagingResponseResult.setPaging(pagingResponse);
        pagingResponseResult.setCode(HttpStatus.SC_OK);
        pagingResponseResult.setMessage("获取成功");

        return pagingResponseResult;
    }
}
