package com.tuyou.mqtt.producer.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
/**
 * @author yhl
 */
public class DruidConfiguration {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private long maxWait;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private long removeAbandonedTimeoutMillis;
    private boolean removeAbandoned;
    private boolean logAbandoned;
    private boolean logDifferentThread;
    private String filters;
    private String connectionProperties;
    private boolean useGlobalDataSourceStat;
    private String druidRegistrationUrl;
    private boolean resetEnable;
    private String loginUsername;
    private String loginPassword;
    private String filtersUrlPatterns;
    private String exclusions;
    private int sessionStatMaxCount;
    private boolean sessionStatEnable;
    private String principalSessionName;
    private boolean profileEnable;

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    @Primary
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        dataSource.setRemoveAbandonedTimeoutMillis(removeAbandonedTimeoutMillis);
        dataSource.setRemoveAbandoned(removeAbandoned);
        dataSource.setLogDifferentThread(logDifferentThread);

        try {
            dataSource.setFilters(filters);
        }
        catch(SQLException e) {
            e.printStackTrace();
            log.error("Druid URL过滤设置失败", e);
        }
        dataSource.setConnectionProperties(connectionProperties);
        dataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);

        return dataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletBean = new ServletRegistrationBean(new StatViewServlet(), druidRegistrationUrl);
        servletBean.addInitParameter("resetEnable", String.valueOf(resetEnable));
        servletBean.addInitParameter("loginUsername", loginUsername);
        servletBean.addInitParameter("loginPassword", loginPassword);

        return servletBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns(filtersUrlPatterns);
        filterRegistrationBean.addInitParameter("exclusions", exclusions);
        filterRegistrationBean.addInitParameter("sessionStatMaxCount", String.valueOf(sessionStatMaxCount));
        filterRegistrationBean.addInitParameter("sessionStatEnable", String.valueOf(sessionStatEnable));
        filterRegistrationBean.addInitParameter("principalSessionName", principalSessionName);
        filterRegistrationBean.addInitParameter("profileEnable", String.valueOf(profileEnable));

        return filterRegistrationBean;
    }
}
