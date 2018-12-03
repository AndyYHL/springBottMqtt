package com.tuyou.mqtt.producer.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
/**
 * @author yhl
 * 事务监控
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({DruidConfiguration.class})
public class TransactionConfig implements TransactionManagementConfigurer {
    @Autowired
    private DruidDataSource druidDataSource;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(this.druidDataSource);
    }
}

