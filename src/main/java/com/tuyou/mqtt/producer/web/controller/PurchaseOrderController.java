package com.tuyou.mqtt.producer.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.pojo.dto.EquipmentInfoDTO;
import com.tuyou.mqtt.producer.pojo.vo.ExcelDataVO;
import com.tuyou.mqtt.producer.pojo.vo.TitlesTtempletVO;
import com.tuyou.mqtt.producer.util.PoiUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author yhl
 * 导出数据说明
 */
@ApiIgnore(value = "不显示")
@Api(tags = "导出操作类",value = "导出操作类",hidden = true)
@Slf4j
@RestController
@RequestMapping(value = ClientApiFinal.version + "actuator/")
public class PurchaseOrderController {

    @Autowired
    private Validator validator;
    
    @GetMapping("info")
    public String info() {
        return "服务器运行正常";
    }

    @GetMapping("health")
    public String health() {
        return "服务器运行正常";
    }

    /**
     * 导出事例
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/purchaseOrderExcelDownloads", method = RequestMethod.GET)
    public String purchaseOrderExcelDownloads(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        //设置要导出的文件的名字
        String fileName = "加油订单报表" + second + ".xls";
        response.setContentType("application/octet-stream");
        response.setContentType("application/msexcel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        PoiUtil poiUtil = new PoiUtil();
        ExcelDataVO excelData = new ExcelDataVO();
        //设置标头
        excelData.setFirstTitles("加油订单报表");
        //获取参数
        Map params = Maps.newHashMap();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        if (params.size() == 0) {
            return "请求参数不能为空";
        }
        log.info(JSON.toJSONString(params));
        ExcelDataVO excelDataVO1 = JSON.parseObject(JSON.toJSONString(params), ExcelDataVO.class);
        StringBuffer stringBuffer = new StringBuffer();
        String day = "2018年12月3日";
        if (StringUtils.isNotBlank(day)) {
            stringBuffer.append("统计时间：").append("2018年12月3日").append(" 至 ").append("2018年12月3日");
        } else {
            stringBuffer.append("统计时间：全部时间");
        }

        excelData.setRemark(stringBuffer.toString());
        //设置标头
        List<TitlesTtempletVO> titlesTtempletList = new ArrayList<>();
        String[] title = {"订单号", "第三方流水号", "班次号", "油枪", "油品", "油价",
                "升数（升）", "总金额", "等级优惠金额", "优惠金额", "实付金额", "会员姓名", "会员卡号", "会员手机",
                "支付方式", "收银员", "开始升数", "结束升数", "加油站点", "支付时间"};
        Integer[] columnWidth = {40, 40, 15, 10, 10, 15, 15, 10, 20, 15, 10, 10, 20, 15, 10, 20, 20, 20, 20, 20};
        for (int i = 0; i < title.length; i++) {
            TitlesTtempletVO titlesTtemplet = new TitlesTtempletVO();
            titlesTtemplet.setTitle(title[i]);
            titlesTtemplet.setColumnWidth(columnWidth[i]);
            titlesTtempletList.add(titlesTtemplet);
        }
        excelData.setTitles(titlesTtempletList);
        //设置模拟数据
        List<List<Object>> rows = new ArrayList<>();
        List<ExcelDataVO> orderDtoList = new ArrayList<>();

        List<ExcelDataVO> spOrderList = JSON.parseArray(JSON.toJSONString(orderDtoList), ExcelDataVO.class);
        for (ExcelDataVO excelDataVO : spOrderList) {
            List<Object> row = new ArrayList<>();
            row.add(excelDataVO.getFirstTitles());
            row.add(excelDataVO.getRemark());
            row.add(excelDataVO.getSheetName());
            row.add(excelDataVO.getTitles());
            rows.add(row);
        }
        excelData.setRows(rows);
        try {
            Workbook workbook = poiUtil.exportExcel(excelData, fileName);
            //输出Excel文件
            workbook.write(response.getOutputStream());
            response.getOutputStream().close();
            return "成功";
        } catch (Exception e) {
            return "失败" + e.getMessage();
        }
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(EquipmentInfoDTO equipmentInfoDTO){
        //校验参数
        Set<ConstraintViolation<EquipmentInfoDTO>> violationSet = validator.validate(equipmentInfoDTO);
        if(violationSet.size() > 0){
            Optional<ConstraintViolation<EquipmentInfoDTO>> couponDTOConstraintViolation = violationSet.stream().findFirst();
            return HttpStatus.SC_FAILED_DEPENDENCY+""+couponDTOConstraintViolation.get().getMessage();
        }
        return null;
    }
}
