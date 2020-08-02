package com.hyp.blogmaster.pojo.blog.model;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/1 12:11
 * @Description: TODO 日志类型
 */
public enum JournalModalJournalTypeEnum {


    QUTOUPIAO(1, "趣互动"),
    HEYAPEI(0, "何亚培");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述
     */
    private String msg;

    @Override
    public String toString() {
        return "JournalModalJournalTypeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    JournalModalJournalTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
