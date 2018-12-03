package com.tuyou.mqtt.producer.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"com.tuyou.mqtt.producer.dao"})

/**
 * @author yhl
 */
public class MybatisPlusConfig {
    @Value("${mybatisPlus.mapperLocations}")
    String mapperLocations;
    @Value("${mybatisPlus.typeEnumsPackage}")
    String typeEnumsPackage;
    @Value("${mybatisPlus.globalConfig.dbConfig.idType}")
    String idType;
    @Value("${mybatisPlus.globalConfig.dbConfig.tableUnderline}")
    Boolean tableUnderline;
    @Value("${mybatisPlus.globalConfig.dbConfig.logicDeleteValue}")
    String logicDeleteValue;
    @Value("${mybatisPlus.globalConfig.dbConfig.logicNotDeleteValue}")
    String logicNotDeleteValue;

    @Bean(name = "globalConfig")
    public GlobalConfig globalConfig() {
        log.info("Init GlobalConfiguration");
        final String ID_TYPE_INPUT = "input";
        final String ID_TYPE_UUID = "uuid";
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();

        if (this.idType != null) {
            if (ID_TYPE_INPUT.equals(this.idType)) {
                dbConfig.setIdType(IdType.INPUT);
            } else if (ID_TYPE_UUID.equals(this.idType)) {
                dbConfig.setIdType(IdType.UUID);
            } else {
                dbConfig.setIdType(IdType.AUTO);
            }
        }

        if (this.tableUnderline != null) {
            dbConfig.setTableUnderline(this.tableUnderline);
        }

        if (this.logicDeleteValue != null) {
            dbConfig.setLogicDeleteValue(this.logicDeleteValue);
        }

        if (logicNotDeleteValue != null) {
            dbConfig.setLogicNotDeleteValue(logicNotDeleteValue);
        }

        globalConfig.setDbConfig(dbConfig);
        return globalConfig;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "globalConfig")GlobalConfig globalConfig,
                                               @Qualifier(value = "dataSource")DruidDataSource dataSource) throws Exception {
        log.info("Init SqlSessionFactory");
        log.info("MapperLocations: {}", mapperLocations);
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setGlobalConfig(globalConfig);
        Interceptor[] interceptor = {new PaginationInterceptor()};
        sqlSessionFactory.setPlugins(interceptor);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactory.setMapperLocations(resolver.getResources(this.mapperLocations));
            sqlSessionFactory.setTypeEnumsPackage(this.typeEnumsPackage);
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            log.error("SqlSessionFactory创建失败", e);
            throw e;
        }
    }

    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
