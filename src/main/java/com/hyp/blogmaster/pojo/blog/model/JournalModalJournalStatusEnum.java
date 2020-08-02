package com.hyp.blogmaster.pojo.blog.model;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/8/1 12:11
 * @Description: TODO 日志状态
 */
public enum JournalModalJournalStatusEnum {


    ONLINE(0, "上线"),
    OFFLINE(1, "下线");

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
        return "JournalModalJournalStatusEnum{" +
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

    JournalModalJournalStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
