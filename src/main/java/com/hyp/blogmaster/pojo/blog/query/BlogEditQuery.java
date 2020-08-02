package com.hyp.blogmaster.pojo.blog.query;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/2 13:24
 * @Description: TODO
 */
@Data
public class BlogEditQuery {

    private Integer journalId;
    private String journalCoverImg = "";
    @NotNull(message = "标题必填")
    @NotEmpty(message = "标题必填")
    private String title;
    private String explainWord = "";
    private String journalClassify = "";
    private Integer adminUserId;
    @NotNull
    private Integer showOrder = 0;
    @NotNull
    private String submitArticleContent;


}
