package com.hyp.blogmaster.config.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * //basePackages:接口文件的包路径
 * @author heyapei
 */
@Configuration
@MapperScan(basePackages = "com.hyp.blogmaster.mapper",
        sqlSessionFactoryRef = "PrimarySqlSessionFactory",
        sqlSessionTemplateRef = "PrimarySqlSessionTemplate")
public class PrimaryDataSourceConfig {

    @Bean(name = "PrimaryDataSource")
    // 表示这个数据源是默认数据源
    @Primary//这个一定要加，如果两个数据源都没有@Primary会报错
    @ConfigurationProperties(prefix = "spring.datasource.primary")//我们配置文件中的前缀
    public DataSource getPrimaryDateSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "PrimarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("PrimaryDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations( 
                new PathMatchingResourcePatternResolver().
                        getResources("classpath:mapper/*.xml"));
        return bean.getObject();// 设置mybatis的xml所在位置
    }

    @Bean
    public DataSourceTransactionManager db2TransactionManager(@Qualifier("PrimaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    
    @Bean("PrimarySqlSessionTemplate")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("PrimarySqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }

}

