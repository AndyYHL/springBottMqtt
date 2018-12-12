package com.tuyou.mqtt.producer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuyou.mqtt.producer.pojo.domain.OiltankDO;
import com.tuyou.mqtt.producer.pojo.dto.OiltankDTO;
import com.tuyou.mqtt.producer.pojo.vo.OiltankVO;
import com.tuyou.mqtt.producer.repository.OiltankMapper;
import com.tuyou.mqtt.producer.service.IOiltankService;
import com.tuyou.mqtt.producer.util.json.ExtLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class OiltankServiceImpl extends ServiceImpl<OiltankMapper, OiltankDO> implements IOiltankService {
/**
     * @Description 新增油罐信息
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应Integer数据
     */
    @Override
    public Integer saveOiltank(OiltankDTO oiltankDTO){
    	return 0;
    }

    /**
     * 保存非空值
     * @param oiltankDTO
     * @return 响应Integer数据
     */
    @Override
    public Integer saveOiltankNotNull(OiltankDTO oiltankDTO){
    	return 0;
    }

    /**
     * @Description 修改油罐信息
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应boolean数据
     */
    @Override
    public boolean uptOiltank(OiltankDTO oiltankDTO){
    	return false;
    }

    /**
     * @Description 删除油罐信息
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应Integer数据
     */
    @Override
    public Integer delOiltank(OiltankDTO oiltankDTO){
    	return 0;
    }

    /**
     * @Description 查询油罐信息详情
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应OiltankVO数据
     */
    @Override
    public OiltankVO findOiltank(OiltankDTO oiltankDTO){
        OiltankVO oiltankVO = new OiltankVO();
        // 获取油罐信息
        QueryWrapper<OiltankDO> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<OiltankDO> lambdaQueryWrapper = queryWrapper.lambda();
        lambdaQueryWrapper.eq(OiltankDO::getStationId,oiltankDTO.getStationId());
        lambdaQueryWrapper.eq(OiltankDO::getEnterpriseId,oiltankDTO.getEnterpriseId());
        lambdaQueryWrapper.eq(OiltankDO::getOiltankNo,oiltankDTO.getOiltankNo());

        OiltankDO oiltankDO = super.baseMapper.selectOne(queryWrapper);
        if (null == oiltankDO){
            log.info("没有查询到油罐，进行新增:{}",oiltankDTO.getOiltankNo());
            // 没有查询到油罐信息，进行新增 并且返回
            oiltankDO = new OiltankDO();
            oiltankDO.setOiltankNo(oiltankDTO.getOiltankNo());
            oiltankDO.setOiltankName(oiltankDTO.getOiltankName());
            oiltankDO.setStationId(oiltankDTO.getStationId());
            oiltankDO.setStationName(oiltankDTO.getStationName());
            oiltankDO.setEnterpriseId(oiltankDTO.getEnterpriseId());
            oiltankDO.setEnterpriseName(oiltankDTO.getEnterpriseName());
            oiltankDO.setCreatedTime(new Date());
            Integer addOiltank = super.baseMapper.insert(oiltankDO);
            if (addOiltank == 0){
                log.info("查询时候，新增油罐失败");
            }else{
                log.info("查询时候,新增油罐成功:{},油罐号:{}",oiltankDO.getOiltankId(),oiltankDO.getOiltankNo());
                BeanUtils.copyProperties(oiltankDO,oiltankVO);
                return oiltankVO;
            }
        }
        log.info("返回油罐:{},油罐号:{}",oiltankDO.getOiltankId(),oiltankDO.getOiltankNo());
        BeanUtils.copyProperties(oiltankDO,oiltankVO);
    	return oiltankVO;
    }

    /**
     * @Description 查询列表油罐信息列表 分页可有可无
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应List<oiltankVO>数据
     */
    @Override
    public List<OiltankVO> findOiltankList(OiltankDTO oiltankDTO){
    	return null;
    }

    /**
     * @Description 查询列表油罐信息(带分页)
     * @param oiltankDTO 请求oiltankDTO数据
     * @return 响应List<oiltankVO>数据
     */
    @Override
    public List<OiltankVO> findOiltankLimit(OiltankDTO oiltankDTO, ExtLimit extLimit){
    	return null;
    }
}