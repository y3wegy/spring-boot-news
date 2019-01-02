package com.y3wegy.config.database;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.y3wegy.config.database.helper.MyBatiesConfigHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.y3wegy.config.prop.MyBatisProp;

/**
 * @author Rui
 */
@Configuration
@MapperScan(basePackages = {"com.y3wegy.mapper.master"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
@ConditionalOnClass({EnableTransactionManagement.class, EntityManager.class})
@AutoConfigureAfter(MyBatisProp.class)
public class MasterMybatisConfiguration {

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory buildMasterSqlSessionFactory(@Qualifier("masterDataSource") DataSource druidDataSource,
            MyBatisProp myBatisProp) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource);
        return MyBatiesConfigHelper.config(sqlSessionFactoryBean, myBatisProp);
    }

    @Bean(name = "masterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate buildMasterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager buildMasterTransactionManager(@Qualifier("masterDataSource") DataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }
}
