package com.hyp.blogmaster.service.blog;

import com.hyp.blogmaster.pojo.blog.vo.BlogShowVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/2 10:56
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JournalServiceTest {


    @Autowired
    private JournalService journalService;

    @Test
    public void showBlogByPkId() {
        BlogShowVO blogShowVO = journalService.showBlogByPkId(1);
        log.info("查询结果：{}"+blogShowVO);

    }

    @Test
    public void selectPageInfoByBlogListQuery() {
    }

    @Test
    public void selectJournalById() {
    }

    @Test
    public void updateSelectiveByPkId() {
    }
}