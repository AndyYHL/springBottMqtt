package com.tuyou.mqtt.producer.api.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tuyou.mqtt.producer.api.ITuYouCrmServiceApi;
import com.tuyou.mqtt.producer.util.json.JsonUtil;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * 熔断处理
 */
@Component
public class TuYouCrmServiceApiImpl implements ITuYouCrmServiceApi {

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @Override
    public JsonUtil getUserAccountById(JsonUtil jsonUtil) {
        jsonUtil.getInfo().setStatus(HttpStatus.SC_NOT_FOUND);
        jsonUtil.getInfo().setMessage("服务器宕机了");
        return jsonUtil;
    }
}
