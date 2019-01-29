package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class MySession {
    // 说话者
    private User speaker;
    // 回话者
    private User responder;
    // 对话内容
    private String content;
    // 对话时间
    private Date createTime;
    // 对话类型
    private Integer type;
    // 有无查看
    private Integer state;

}