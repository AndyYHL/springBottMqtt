package com.tuyou.mqtt.producer;

import com.tuyou.mqtt.producer.pojo.entities.HttpClientResult;
import com.tuyou.mqtt.producer.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class ProducerApplicationTests {

    @Test
    public void contextLoads() {
        HttpClientResult httpClientResult = null;
        try {
            httpClientResult = HttpClientUtil.doPost("https://yltapi.mfhcd.com/contract/findApplicationFormInfo", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (httpClientResult.getCode().equals(HttpStatus.OK.value())){
            log.info(httpClientResult.getContent());
        }else{
            log.error("网络请求异常：{}",httpClientResult.getCode());
        }
    }

}
