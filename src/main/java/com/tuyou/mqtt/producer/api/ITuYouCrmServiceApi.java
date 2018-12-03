package com.tuyou.mqtt.producer.api;

import com.tuyou.mqtt.producer.api.impl.TuYouCrmServiceApiImpl;
import com.tuyou.mqtt.producer.util.json.JsonUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yhl
 * 服务器内部请求
 * @FeignClient(name="tuyou-crm",url ="${crm.url}",fallback = TuYouCrmServiceApiImpl.class)
 * FeignClient(服务器名字，url 请求地址【可以写入配置文件】，熔断处理类)
 */
@FeignClient(name="tuyou-crm",fallback = TuYouCrmServiceApiImpl.class)
public interface ITuYouCrmServiceApi {
    /**
     * 根据会员id获取会员账户信息
     *
     * @param jsonUtil
     * @return
     */
    @PostMapping(value = "/user/getUserById")
    JsonUtil getUserAccountById(@RequestBody JsonUtil jsonUtil);
}
