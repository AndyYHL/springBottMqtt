package com.tuyou.mqtt.producer.web.controller;

import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentDataDTO;
import com.tuyou.mqtt.producer.pojo.param.EquipmentDataPARAM;
import com.tuyou.mqtt.producer.pojo.swagger2.DataResponseResult;
import com.tuyou.mqtt.producer.pojo.vo.EquipmentDataVO;
import com.tuyou.mqtt.producer.service.IEquipmentDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 设备采集数据
 *
 * @author yhl
 */
@RestController
@RequestMapping(value = ClientApiFinal.version + "equipmentData/")
@Api(value = "EquipmentDataController|用来管理和获取设备采集数据处理结果展示到前端页面", tags = {"用来管理和获取设备采集数据处理结果展示到前端页面"})
public class EquipmentDataController {
    @Autowired
    IEquipmentDataService equipmentDataService;
    @Autowired
    private Validator validator;

    @ApiOperation(value = "获取设备采集数据", notes = "根据分页，仅返回采集数据..")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.SC_OK, message = "查询成功!"),
            @ApiResponse(code = HttpStatus.SC_FAILED_DEPENDENCY, message = "参数不完整!"),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "请求异常!")
    })
    @RequestMapping(value = "findEquipmentDataLimit", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public DataResponseResult<List<EquipmentDataVO>> findEquipmentDataLimit(@RequestBody EquipmentDataPARAM equipmentDataPARAM) {
        //校验参数
        Set<ConstraintViolation<EquipmentDataPARAM>> violationSet = validator.validate(equipmentDataPARAM);
        if (violationSet.size() > 0) {
            Optional<ConstraintViolation<EquipmentDataPARAM>> equipmentDataPARAMConstraintViolation = violationSet.stream().findFirst();
            return new DataResponseResult<>(HttpStatus.SC_FAILED_DEPENDENCY, equipmentDataPARAMConstraintViolation.get().getMessage(), null);
        }

        EquipmentDataDTO equipmentDataDTO = new EquipmentDataDTO();
        BeanUtils.copyProperties(equipmentDataPARAM, equipmentDataDTO);

        List<EquipmentDataVO> equipmentDataVOList = equipmentDataService.findEquipmentDataLimit(equipmentDataDTO);
        return new DataResponseResult<>(HttpStatus.SC_OK, "获取成功", equipmentDataVOList);
    }
}
