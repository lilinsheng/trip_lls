package cn.wolfcode.trip.base.domain;

import java.util.Date;

public class Fabulous {
    public static final int STATE_UNREAD = 1;
    public static final int STATE_READ = 0;
    // 点赞的人
    private User user;
    // 朋友圈文章
    //private Friend friend;
    // 游记
    private Travel travel;
    // 查看与否
    private Integer state;
    // 创建时间
    private Date createTime;
}
