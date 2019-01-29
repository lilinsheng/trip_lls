package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@JsonIgnoreProperties("handler")
public class StrategyComment extends BaseDomain{
    public static final Integer STATE_COMMON = 0;//普通
    public static final Integer STATE_HOT = 1;//推荐
    public static final Integer STATE_DISABLED = -1;//禁用

    //评论人
    private User user;
    //评论时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    //评论内容
    private String content;
    //评论图片
    private String imgUrls;
    //星星数量
    private Integer starNum;
    //攻略
    private Strategy strategy;
    //状态
    private Integer state = STATE_COMMON;
    //头条推荐时间
    private Date commendTime;
    //评论数
    private Long commentNum;

    public String[] getImgArr(){
        if (imgUrls!=null){
            return imgUrls.split(";");
        }
        return null;
    }
}