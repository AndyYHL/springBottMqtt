package com.tuyou.mqtt.producer.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 生成校验签名
 *
 * @author yhl
 */
@Slf4j
public class CheckCodeUtil {

    /**
     * 生成校验签名
     *
     * @param dataMap    入参Map
     * @param privateKey 密钥
     * @return 返回加密的签名
     * @throws Exception MD5加密出错
     */
    public static String getCheckCode(Map<String, String> dataMap, String privateKey) {
        Map<String, String> sMap = sortMapByKeyObject(dataMap);
        StringBuffer encryptStr = new StringBuffer();
        for (Map.Entry<String, String> entry : sMap.entrySet()) {
            if (entry.getKey() != null && StringUtils.isNotEmpty(String.valueOf(entry.getValue()))) {
                encryptStr.append(entry.getKey())
                        .append("=")
                        .append(String.valueOf(entry.getValue()))
                        .append("&");
            }
        }
        String signStr = encryptStr.toString().substring(0,encryptStr.toString().length() - 1);
        log.info("第一次MD5签名的字符串:{}", signStr);
        //对排序的数据进行加密
        String sign = DigestUtils.md5Hex(signStr).concat(privateKey);
        log.info("第二次MD5签名的字符串:{}", sign);
        return DigestUtils.md5Hex(sign).toUpperCase();
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKeyObject(Map<String, String> map) {
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }
        });
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 生成检验码
     *
     * @return
     */
    private static String getSign(Map map, String privateKay) {
        Map<String, String> sMap = sortMapByKeyObject(map);
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : sMap.entrySet()) {
            sb.append(entry.getValue());
        }
        sb.append(privateKay);
        log.info("加密的字符串是:{}", sb.toString());
        //对排序的数据进行加密
        String acecssToken = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        log.info("签名:{}", acecssToken);
        return acecssToken;
    }

    /**
     * 比对Sign是否正确
     *
     * @param map        加密的数据
     * @param privateKay 加密的私钥
     * @param sign       签名
     * @return true 正确 false 验证失败
     */
    public static boolean globalSign(Map map, String privateKay, String sign) {
        if (getSign(map, privateKay).equals(sign)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("enterpriseId", 100056);
        map.put("outTradeNo", "123456789");
        map.put("payId", 5);
        map.put("phoneNumber", "18200556077");
        map.put("rechargeAmount", 200.0);
        map.put("source", 3);
        map.put("stationId", "10005600001");
        String sign = null;
        try {
            sign = getCheckCode(map, "QIQ4XNKLF&E1UKOZ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sign.toUpperCase());
    }
}
