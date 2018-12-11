package com.tuyou.mqtt.producer.config;

import com.tuyou.mqtt.producer.constant.ClientApiFinal;
import com.tuyou.mqtt.producer.service.impl.PushCallback;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

//

/**
 * @author yhl
 * Component 不加这个注解的话, 使用@Autowired 就不能注入进去了
 * @PropertySource("classpath:application.yml") //指定要读取的配置文件
 * ConfigurationProperties 配置文件中的前缀
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "mqtt")
@Data
public class MqttConfiguration {
    private String host;
    private String username;
    private String password;
    private String topic;
    private Integer qos;
    private String[] hosts;
    private Integer connectionTimeout;
    private Integer keepAliveInterval;
    private String publishClientId;
    private String subscribeClientId;
    private boolean retained;
    private String dtopic;
    private int completionTimeout;

    /**
     * 消息通道 生产
     *
     * @return
     */
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * 消息通道 订阅
     *
     * @return
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 链接打开MQTT数据，配置链接
     *
     * @return
     */
    private MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(hosts);
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }

    /**
     * mqtt服务器配置
     *
     * @return
     */
    @Bean
    public MqttPahoClientFactory clientFactory() {
        DefaultMqttPahoClientFactory clientFactory = new DefaultMqttPahoClientFactory();
        clientFactory.setConnectionOptions(getMqttConnectOptions());
        return clientFactory;
    }

    /**
     * 通道适配器
     *
     * @param clientFactory
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MqttPahoMessageHandler mqttOutbound(MqttPahoClientFactory clientFactory) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(publishClientId, clientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(qos);
        messageHandler.setDefaultRetained(false);
        messageHandler.setAsyncEvents(false);
        return messageHandler;
    }

    /**
     * 通道适配器
     *
     * @param clientFactory
     * @param mqttInputChannel
     * @return
     */
    @Bean
    public MessageProducer inbound(MqttPahoClientFactory clientFactory, MessageChannel mqttInputChannel) {
        //clientId 客户端ID，生产端和消费端的客户端ID需不同，不然服务器会认为是同一个客户端，会接收不到信息
        //topic 订阅的主题
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(subscribeClientId, clientFactory,
                topic.split(","));
        //超时时间
        adapter.setCompletionTimeout(completionTimeout);
        //转换器
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(qos);
        adapter.setOutputChannel(mqttInputChannel);
        return adapter;
    }

    /**
     * 消息处理
     *
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return r -> {
            try {
                //这里拿到发布的消息内容，做具体的业务逻辑处理
                String string = r.getPayload().toString();
                System.out.println(string);
                String topic = r.getHeaders().get(ClientApiFinal.mqttReceivedTopic).toString();
                String type = topic.substring(topic.lastIndexOf("/") + 1, topic.length());
                log.info("订阅的主题:{}",type);
                if ("yes".equalsIgnoreCase(topic)) {
                    System.out.println("yes,fuckXX," + r.getPayload().toString());
                } else if ("good".equalsIgnoreCase(topic)) {
                    System.out.println("good,fuckXX," + r.getPayload().toString());
                } else if ("test".equalsIgnoreCase(topic)) {
                    System.out.println("test,fuckXX," + r.getPayload().toString());
                } else if ("wiz_publish".equalsIgnoreCase(topic)){
                    System.out.println("wiz_publish,周静测试发送：" + r.getPayload().toString());
                }
            } catch (MessagingException ex) {
                // 消息订阅异常
                log.info("消息订阅异常:{}",ex.toString());
            }
        };
    }
}
