package com.y3wegy.config.database;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.y3wegy.config.prop.DruidProp;
import com.y3wegy.exception.RuiDBException;

/**
 * @author Rui
 * 
 * Druid的DataResource配置类
 * 凡是被Spring管理的类，实现接口 EnvironmentAware 重写方法 setEnvironment 可以在工程启动时，
 * 获取到系统环境变量和application配置文件中的变量。 还有一种方式是采用注解的方式获取 @value("${变量的key值}")
 * @Value("${spring.datasource.initialSize}")
 * 获取application配置文件中的变量。 这里采用第一种要方便些
 * Created by sun on 2017-1-20.
 */
@Configuration
@AutoConfigureAfter(DruidProp.class)
@AutoConfigureBefore({MasterMybatisConfiguration.class, BusinessMybatisConfiguration.class})
public class DataBaseConfiguration {

    @Autowired
    private DruidProp druidProp;

    private static Logger logger = LoggerFactory.getLogger(DataBaseConfiguration.class);

    @Bean(name = "masterDataSourceProp")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSourceProperties readDataSourceProp() {
        return new DataSourceProperties();
    }

    @Bean(name = "businessDataSourceProp")
    @ConfigurationProperties(prefix = "spring.datasource.business")
    @Primary
    public DataSourceProperties writeDataSourceProp() {
        return new DataSourceProperties();
    }

    @Bean(name = "masterDataSource")
    public DataSource buildMasterDataSources(@Qualifier("masterDataSourceProp") DataSourceProperties dataSourceProperties) throws RuiDBException {
        logger.debug("Configure master DataSource");
        DataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().build();
        return getDruidDataSource(dataSource, dataSourceProperties);
    }

    @Bean(name = "businessDataSource")
    @Primary
    public DataSource buildBusinessDataSource(@Qualifier("businessDataSourceProp") DataSourceProperties dataSourceProperties) throws RuiDBException {
        logger.debug("Configure business DataSource");
        DataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().build();
        return getDruidDataSource(dataSource, dataSourceProperties);
    }

    private DruidDataSource getDruidDataSource(DataSource dataSource, DataSourceProperties dataSourceProperties) throws RuiDBException {
        DruidDataSource druiDataSource = (DruidDataSource) dataSource;
        try {
            druiDataSource.setName(dataSourceProperties.getName());
            druiDataSource.setInitialSize(druidProp.getInitialSize());
            druiDataSource.setMaxActive(druidProp.getMaxActive());
            druiDataSource.setMinIdle(druidProp.getMinIdle());
            druiDataSource.setMaxWait(druidProp.getMaxWait());
            druiDataSource.setTimeBetweenEvictionRunsMillis(druidProp.getTimeBetweenEvictionRunsMillis());
            druiDataSource.setMinEvictableIdleTimeMillis(druidProp.getMinEvictableIdleTimeMillis());
            druiDataSource.setValidationQuery(druidProp.getValidationQuery());
            druiDataSource.setTestOnBorrow(druidProp.getTestOnBorrow());
            druiDataSource.setTestWhileIdle(druidProp.getTestWhileIdle());
            druiDataSource.setTestOnReturn(druidProp.getTestOnReturn());
            druiDataSource.setPoolPreparedStatements(druidProp.getPoolPreparedStatements());
            druiDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidProp.getMaxOpenPreparedStatements());
            //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
            druiDataSource.setFilters(druidProp.getFilters());
            return druiDataSource;
        } catch (Exception e) {
            throw new RuiDBException("create DruidDataSource failed", e);
        }
    }
}
