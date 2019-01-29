package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class QuestionAndComment extends BaseDomain{
    //评论信息
    private String content;
    //评论者信息
    private Question question;
    //评论者信息
    private User user;
    //评论时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date answerTime;
}