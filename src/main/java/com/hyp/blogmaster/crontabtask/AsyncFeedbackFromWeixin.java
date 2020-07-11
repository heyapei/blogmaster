package com.hyp.blogmaster.crontabtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/12 0:56
 * @Description: TODO
 */
@Component
@Slf4j
@PropertySource("classpath:crontab-task-auto.properties")
public class AsyncFeedbackFromWeixin {


    /**
     * 从weixin数据库中同步数据到weixin_admin_manager数据库中
     * 每五分钟执行一次
     */
    @Scheduled(cron = "${crontab.task.loop.minute.five}")
    public void asyncFeedbackFromWeixin() {
        log.info("执行任务：{}", new Date());
    }
}
