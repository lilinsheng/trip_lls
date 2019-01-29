package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Fere extends BaseDomain{

    //封面
    private String coverUrl;
    //标题
    private String title;
    //旅行天数
    private String days;
    //出发时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date goTime;
    //作者信息
    private User user;
    //结伴内容
    private String content;
    //出发地点
    private Region region;

}