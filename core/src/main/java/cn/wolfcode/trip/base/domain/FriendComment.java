package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 朋友圈评论
 */
@Setter
@Getter
@JsonIgnoreProperties("handler")
public class FriendComment extends BaseDomain{
    //评论者
    private User user;
    //父级评论
    private User parent;
    //评论时间
    private Date createTime;
    //评论内容
    private String content;
    //好友动态
    private Friend friend;
    //状态
    private Integer state;
    //子级评论
    private List<FriendComment> sonFriendComments = new ArrayList<>();

}