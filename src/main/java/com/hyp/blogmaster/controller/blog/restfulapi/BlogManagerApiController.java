package com.hyp.blogmaster.controller.blog.restfulapi;


import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.blog.query.BlogEditQuery;
import com.hyp.blogmaster.pojo.vo.result.MyResultVO;
import com.hyp.blogmaster.service.blog.JournalService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/1 13:44
 * @Description: TODO
 */
@RestController
@RequestMapping(value = {"/api/admin/manager/blog"})
@Slf4j
public class BlogManagerApiController {

    @Autowired
    private JournalService journalService;

    @ApiOperation("更新日志信息")
    @PostMapping("editOrUpdate")
    public MyResultVO showBlogList(@Validated BlogEditQuery blogEditQuery, BindingResult result) {

        if (result.hasErrors()) {
            //取一条错误信息
            ObjectError next = result.getAllErrors().iterator().next();
            //后边可以自己返回错误信息也可以自定义
            return MyResultVO.genFailResult400(next.getDefaultMessage());
        }
        try {
            Integer integer = journalService.addJournalByBlogEditQuery(blogEditQuery);
            if (integer != null) {
                if (blogEditQuery.getJournalId() != null) {
                    return MyResultVO.genSuccessResult("更新公告成功", integer);
                } else {
                    return MyResultVO.genSuccessResult("新增公告成功", integer);
                }
            } else {
                return MyResultVO.genFailResult500("操作失败");
            }
        } catch (MyDefinitionException e) {
            return MyResultVO.genFailResult400(e.getMessage());
        }

    }


    @ApiOperation("根据主键删除公告")
    @GetMapping("delete/{blogId}")
    public MyResultVO deleteBlogById(@PathVariable Integer blogId) {
        try {
            Integer pkId = journalService.deleteByPkId(blogId);
            if (pkId <= 0) {
                return MyResultVO.genFailResult400("未能删除成功");
            } else {
                return MyResultVO.genSuccessResult("删除成功");
            }
        } catch (MyDefinitionException e) {
            return MyResultVO.genFailResult500(e.getMessage());
        }
    }


}
