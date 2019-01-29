package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 问答表
 */
@Getter
@Setter
@JsonIgnoreProperties("/handler")
public class Question extends BaseDomain{
    //问题标题
    private String problem;
    //封面
    private String coverUrl;
    //网友说明
    private String trouble;
    //提问时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    //网友信息
    private User user;

    private Integer answerNum;

   /* private List<QuestionAndComment> comments = new ArrayList<>();*/

}