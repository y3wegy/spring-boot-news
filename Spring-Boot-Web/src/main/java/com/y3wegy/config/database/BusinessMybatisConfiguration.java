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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.y3wegy.config.prop.MyBatisProp;

/**
 * @author Rui
 */
@Configuration
@MapperScan(basePackages = {"com.y3wegy.mapper.business"}, sqlSessionFactoryRef = "businessSqlSessionFactory")
@ConditionalOnClass({EnableTransactionManagement.class, EntityManager.class})
@AutoConfigureAfter(MyBatisProp.class)
public class BusinessMybatisConfiguration {

    @Bean(name = "businessSqlSessionFactory")
    public SqlSessionFactory buildBusinessSqlSessionFactory(@Qualifier("businessDataSource") DataSource druidDataSource,
            MyBatisProp myBatisProp) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource);
        return MyBatiesConfigHelper.config(sqlSessionFactoryBean, myBatisProp);
    }

    @Bean(name = "businessSqlSessionTemplate")
    public SqlSessionTemplate buildBusinessSqlSessionTemplate(@Qualifier("businessSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "businessTransactionManager")
    public DataSourceTransactionManager buildBusinessTransactionManager(@Qualifier("businessDataSource") DataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }
}
