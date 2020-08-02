package com.hyp.blogmaster.service.blog.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.mapperblog.JournalMapper;
import com.hyp.blogmaster.pojo.blog.model.Journal;
import com.hyp.blogmaster.pojo.blog.model.JournalModalJournalTypeEnum;
import com.hyp.blogmaster.pojo.blog.query.BlogEditQuery;
import com.hyp.blogmaster.pojo.blog.query.BlogListQuery;
import com.hyp.blogmaster.pojo.blog.vo.BlogListVO;
import com.hyp.blogmaster.pojo.blog.vo.BlogShowVO;
import com.hyp.blogmaster.service.blog.JournalService;
import com.hyp.blogmaster.shiro.pojo.modal.UserSupplyInfo;
import com.hyp.blogmaster.shiro.service.UserSupplyInfoService;
import com.hyp.blogmaster.utils.MyEntityUtil;
import com.hyp.blogmaster.utils.MyEnumUtil;
import com.hyp.blogmaster.utils.dateutil.MyDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/1 14:08
 * @Description: TODO
 */
@Service
@Slf4j
public class JournalServiceImpl implements JournalService {


    @Autowired
    private JournalMapper journalMapper;

    @Autowired
    private UserSupplyInfoService userSupplyInfoService;

    private static final String SEMICOLON_SEPARATOR = ";";

    /**
     * 新增数据
     * 1. 如果存在 则更新
     * 2. 如果不存在则新建
     *
     * @param blogEditQuery
     * @return 主键信息
     * @throws MyDefinitionException
     */
    @Override
    public Integer addJournalByBlogEditQuery(BlogEditQuery blogEditQuery) throws MyDefinitionException {
        log.info("canshu 的主键：{}", blogEditQuery.getJournalId());

        Integer userSessionId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");

        if (blogEditQuery == null) {
            throw new MyDefinitionException("日志新增参数不能为空");
        }

        if (blogEditQuery.getJournalId() == null) {

            UserSupplyInfo userSupplyInfo = null;
            try {
                userSupplyInfo = userSupplyInfoService.selectByUserId(userSessionId);
            } catch (MyDefinitionException e) {
                throw new MyDefinitionException(e.getMessage());
            }
            if (userSupplyInfo == null) {
                throw new MyDefinitionException("没有找到当前您的补充信息无法进行新增操作，请先补充个人信息");
            }

            /*新增*/
            Journal journal = Journal.init();
            journal.setCreateUserId(userSessionId);
            journal.setExplainWord(blogEditQuery.getExplainWord());
            journal.setJournalClassify(blogEditQuery.getJournalClassify());
            journal.setJournalCoverImg(blogEditQuery.getJournalCoverImg());
            journal.setTitle(blogEditQuery.getTitle());
            journal.setJournalType(JournalModalJournalTypeEnum.QUTOUPIAO.getCode());
            journal.setJournalContent(blogEditQuery.getSubmitArticleContent());
            journal.setShowOrder(blogEditQuery.getShowOrder());
            try {
                Integer pkId = insertReturnPkId(journal);
                return pkId;
            } catch (MyDefinitionException e) {
                throw new MyDefinitionException(e.getMessage());
            }
        } else {

            log.info("当前的主键：{}", blogEditQuery.getJournalId());
            /*更新*/
            Journal journal = null;
            try {
                journal = selectJournalById(blogEditQuery.getJournalId());
            } catch (MyDefinitionException e) {
                throw new MyDefinitionException(e.getMessage());
            }
            if (journal == null) {
                throw new MyDefinitionException("未能查询到要更改的公告内容");
            }
            if (!blogEditQuery.getAdminUserId().equals(userSessionId)) {
                throw new MyDefinitionException("公告修改人必须为当前管理员");
            }
            journal.setUpdateTime(new Date());
            journal.setExplainWord(blogEditQuery.getExplainWord());
            journal.setJournalClassify(blogEditQuery.getJournalClassify());
            journal.setJournalCoverImg(blogEditQuery.getJournalCoverImg());
            journal.setTitle(blogEditQuery.getTitle());
            journal.setJournalType(JournalModalJournalTypeEnum.QUTOUPIAO.getCode());
            journal.setJournalContent(blogEditQuery.getSubmitArticleContent());
            journal.setShowOrder(blogEditQuery.getShowOrder());

            try {
                Integer pkId = updateSelectiveByPkId(journal);
                return pkId;
            } catch (MyDefinitionException e) {
                throw new MyDefinitionException(e.getMessage());
            }
        }
    }

    /**
     * 浏览日志信息
     *
     * @param pkId
     * @return
     * @throws MyDefinitionException
     */
    @Override
    public BlogShowVO showBlogByPkId(Integer pkId) throws MyDefinitionException {
        if (pkId == null) {
            throw new MyDefinitionException("必须指定日志");
        }

        Journal journal = selectJournalById(pkId);
        if (journal == null) {
            throw new MyDefinitionException("没能查询到日志信息");
        }

        //BlogShowVO
        BlogShowVO blogShowVO = MyEntityUtil.entity2VM(journal, BlogShowVO.class);
        blogShowVO.setJournalClassify(journal.getJournalClassify().split(SEMICOLON_SEPARATOR));

        UserSupplyInfo userSupplyInfo = null;
        try {
            userSupplyInfo = userSupplyInfoService.selectByUserId(journal.getCreateUserId());
            blogShowVO.setAvatarImg(userSupplyInfo.getAvatarImg());
            blogShowVO.setName(userSupplyInfo.getName());
        } catch (MyDefinitionException e) {
            // do nothing
        }

        blogShowVO.setDateDiff(MyDateUtil.formatTwoDateFormat(blogShowVO.getCreateTime(), new Date()));

        blogShowVO.setBlogTypeMsg(MyEnumUtil.getByIntegerTypeCode(JournalModalJournalTypeEnum.class, "getCode", journal.getJournalType()).getMsg());

        /*计入观看人数*/
        Integer viewNum = journal.getViewNum();
        journal.setViewNum(++viewNum);
        try {
            updateSelectiveByPkId(journal);
        } catch (MyDefinitionException e) {
            // do nothing
        }

        return blogShowVO;
    }

    /**
     * 根据查询条件 分页查询
     *
     * @param blogListQuery
     * @return
     * @throws MyDefinitionException
     */
    @Override
    public PageInfo selectPageInfoByBlogListQuery(BlogListQuery blogListQuery) throws MyDefinitionException {

        if (blogListQuery == null) {
            throw new MyDefinitionException("查询参数必填");
        }

        Boolean enumKeyRight = MyEnumUtil.enumKeyRight(blogListQuery.getJournalType(), JournalModalJournalTypeEnum.class);
        //log.error("判断结果：{}", enumKeyRight);
        if (!enumKeyRight) {
            throw new MyDefinitionException("我们还没有您想要的日志内容");
        }

        /*条件查询*/
        Example example = new Example(Journal.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("journalType", blogListQuery.getJournalType());
        /*排序*/
        example.orderBy("showOrder").desc();
        example.orderBy("createTime").desc();
        example.orderBy("updateTime").desc();
        PageHelper.startPage(blogListQuery.getPageNum(), blogListQuery.getPageSize());
        List<Journal> journalList = null;
        try {
            journalList = journalMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据查询条件BlogListQuery分页查询操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("根据查询条件分页查询操作过程错误");
        }
        // 如果这里需要返回VO，那么这里一定先把查询值放进去，让分页信息存储成功。然后再setList加入VO信息
        PageInfo pageInfo = new PageInfo(journalList);


        List<Journal> list = pageInfo.getList();

        List<BlogListVO> blogListVOSTemp = MyEntityUtil.vm2EntityList(list, BlogListVO.class);
        List<BlogListVO> blogListVOList = new ArrayList<>();
        for (BlogListVO blogListVO : blogListVOSTemp) {
            try {
                UserSupplyInfo userSupplyInfo = userSupplyInfoService.selectByUserId(blogListVO.getCreateUserId());
                blogListVO.setAvatarImg(userSupplyInfo.getAvatarImg());
                blogListVO.setName(userSupplyInfo.getName());
            } catch (MyDefinitionException e) {
                // do nothing
            }
            blogListVOList.add(blogListVO);
        }

        pageInfo.setList(blogListVOList);

        return pageInfo;
    }

    /**
     * 获取日志详细信息
     *
     * @param pkId
     * @return
     * @throws MyDefinitionException
     */
    @Override
    public Journal selectJournalById(Integer pkId) throws MyDefinitionException {
        if (pkId == null) {
            throw new MyDefinitionException("必须指定日志ID");
        }

        Journal journal = null;
        try {
            journal = journalMapper.selectByPrimaryKey(pkId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取日志详细信息操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("获取日志详细信息操作过程错误");
        }

        return journal;
    }

    /**
     * 根据主键更新日志信息
     *
     * @param journal 日志信息（含主键）
     * @return 影响行数
     * @throws MyDefinitionException
     */
    @Override
    public Integer updateSelectiveByPkId(Journal journal) throws MyDefinitionException {

        if (journal == null) {
            throw new MyDefinitionException("更新信息不能为空");
        }

        int i = 0;
        try {
            i = journalMapper.updateByPrimaryKeySelective(journal);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据主键更新日志信息操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("根据主键更新日志信息操作过程错误");
        }
        return i;
    }

    /**
     * 新增数据
     *
     * @param journal 日志信息（不含主键）
     * @return 主键信息
     * @throws MyDefinitionException
     */
    @Override
    public Integer insertReturnPkId(Journal journal) throws MyDefinitionException {
        if (journal == null) {
            throw new MyDefinitionException("更新信息不能为空");
        }

        Integer pkId = null;
        try {
            int i = journalMapper.insertUseGeneratedKeys(journal);
            if (i > 0) {
                pkId = journal.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("新增数据操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("新增数据操作过程错误");
        }
        return pkId;
    }

    /**
     * 通过主键删除
     *
     * @param pkId 主键
     * @return 影响行数
     * @throws MyDefinitionException
     */
    @Override
    public Integer deleteByPkId(Integer pkId) throws MyDefinitionException {

        if (pkId == null) {
            throw new MyDefinitionException("必须置顶内容");
        }
        try {
            int i = journalMapper.deleteByPrimaryKey(pkId);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("通过主键删除操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("操作过程错误");
        }

    }
}
