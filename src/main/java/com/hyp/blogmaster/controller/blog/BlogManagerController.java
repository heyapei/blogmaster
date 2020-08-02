package com.hyp.blogmaster.controller.blog;


import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.blog.model.Journal;
import com.hyp.blogmaster.pojo.blog.model.JournalModalJournalTypeEnum;
import com.hyp.blogmaster.pojo.blog.query.BlogListQuery;
import com.hyp.blogmaster.pojo.blog.vo.BlogShowVO;
import com.hyp.blogmaster.pojo.vo.result.MyError;
import com.hyp.blogmaster.service.blog.JournalService;
import com.hyp.blogmaster.utils.MyEnumUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/1 13:44
 * @Description: TODO
 */
@RestController
@RequestMapping(value = {"/admin/manager/blog"})
@Slf4j
public class BlogManagerController {

    @Autowired
    private JournalService journalService;

    @ApiOperation("按照日志类型，进入对应的日志首页")
    @GetMapping("{blogType}")
    public ModelAndView showBlogList(@PathVariable Integer blogType
            , ModelAndView modelAndView) {
        BlogListQuery blogListQuery = BlogListQuery.init();
        blogListQuery.setJournalType(blogType);

        PageInfo pageInfo = null;
        try {
            pageInfo = journalService.selectPageInfoByBlogListQuery(blogListQuery);
        } catch (MyDefinitionException e) {
            MyError myError = MyError.requestErrorInit();
            myError.setCodeMsg(e.getMessage());
            modelAndView.addObject("myError", myError);
            //设置视图名称
            modelAndView.setViewName("error");
            return modelAndView;
        }

        //向模型添加属性
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("blogTypeMsg", MyEnumUtil.getByIntegerTypeCode(JournalModalJournalTypeEnum.class, "getCode", blogType).getMsg());
        //设置视图名称
        modelAndView.setViewName("manage/blog/manageBlogList");
        return modelAndView;
    }


    @ApiOperation("修改日志的详细内容")
    @GetMapping("editBlogContent/{blogId}")
    public ModelAndView showBlogContent(@PathVariable Integer blogId, ModelAndView modelAndView) {
        Journal journal= null;
        try {
            journal   = journalService.selectJournalById(blogId);
        } catch (MyDefinitionException e) {
            MyError myError = MyError.requestErrorInit();
            myError.setCodeMsg(e.getMessage());
            modelAndView.addObject("myError", myError);
            //设置视图名称
            modelAndView.setViewName("error");
            return modelAndView;
        }
        //向模型添加属性
        modelAndView.addObject("journal", journal);
        //设置视图名称
        modelAndView.setViewName("manage/blog/editBlog");

        return modelAndView;
    }


    @ApiOperation("新增日志")
    @GetMapping("addBlogContent")
    public ModelAndView addBlogContent( ModelAndView modelAndView) {
        //设置视图名称
        modelAndView.setViewName("manage/blog/editBlog");

        return modelAndView;
    }


}
