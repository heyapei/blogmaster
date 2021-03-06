package com.hyp.blogmaster.pojo.vo.page.weixin;

import lombok.Data;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/14 12:43
 * @Description: TODO WeixinVoteWork展示的简单数据
 */

@Data
public class VoteDetailSimpleVO {

    private Integer id;
    private String voteWorkUserName;
    private Integer voteWorkCountNum;
    private String voteWorkImg;
    private Integer voteWorkCountViewNum;
    private Integer voteWorkOr;
}
