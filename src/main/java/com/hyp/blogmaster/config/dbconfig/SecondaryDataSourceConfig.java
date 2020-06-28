package com.hyp.blogmaster.config.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * //basePackages:接口文件的包路径
 * @author heyapei
 */
@Configuration
@MapperScan(basePackages = "com.hyp.blogmaster.shiro.mapper",
        sqlSessionFactoryRef = "SecondSqlSessionFactory",
        sqlSessionTemplateRef = "SecondSqlSessionTemplate")
public class SecondaryDataSourceConfig {

    @Bean(name = "SecondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")//我们配置文件中的前缀
    public DataSource getPrimaryDateSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "SecondSqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("SecondaryDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations( 
                new PathMatchingResourcePatternResolver().
                        getResources("classpath:mapper/shiro/*.xml"));
        return bean.getObject();// 设置mybatis的xml所在位置
    }

    @Bean
    public DataSourceTransactionManager db2TransactionManager(@Qualifier("SecondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean("SecondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(
            @Qualifier("SecondSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }

}

