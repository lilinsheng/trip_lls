package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 攻略评论
 */
@Setter
@Getter
@JsonIgnoreProperties("handler")
public class StrategyDetailComment extends BaseDomain{
    //评论人
    private User user;
    //评论时间
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private Date commentdate;
    //内容
    private String content;
    //评论父级
    private Long parentId;
    //攻略点评
    private StrategyComment strategycomment;

    //评论下面的评论
    private List<StrategyDetailComment> comments = new ArrayList<>();

}