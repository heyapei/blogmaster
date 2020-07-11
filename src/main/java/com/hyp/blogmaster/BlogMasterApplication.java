package com.hyp.blogmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.concurrent.Executor;

/**
 * 2020年5月31日
 * @author heyapei
 */
@SpringBootApplication
//扫描 mybatis mapper 包路径
/*@MapperScan(basePackages = {"com.hyp.blogmaster.mapper","com.hyp.blogmaster.shiro.mapper"})*/
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"com.hyp"})
//开启定时任务
@EnableScheduling
//开启异步调用方法
@EnableAsync
public class BlogMasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogMasterApplication.class, args);
    }



    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }


}
