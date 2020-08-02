package com.hyp.blogmaster.config.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * //basePackages:接口文件的包路径
 *
 * @author heyapei
 */
@Configuration
@MapperScan(basePackages = "com.hyp.blogmaster.mapperblog",
        sqlSessionFactoryRef = "ThirdSqlSessionFactory",
        sqlSessionTemplateRef = "ThirdSqlSessionTemplate")
public class ThirdDataSourceConfig {

    @Bean(name = "ThirdDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.third")//我们配置文件中的前缀
    public DataSource getPrimaryDateSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "ThirdSqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("ThirdDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().
                        getResources("classpath:mapper/blog/*.xml"));
        return bean.getObject();// 设置mybatis的xml所在位置
    }

    @Bean
    public DataSourceTransactionManager db2TransactionManager(@Qualifier("ThirdDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("ThirdSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(
            @Qualifier("ThirdSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }

}

