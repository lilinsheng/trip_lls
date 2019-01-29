package cn.wolfcode.trip.base.query;

import org.springframework.util.StringUtils;

public class StrategyCommentsQueryObject extends QueryObject{

    private String keyword;//关键字
    private Long authorId;//作者
    private String orderBy;//排序
    private Integer state;//状态
    private Boolean isPublic;//是否公开
    private Long travelId;//游记Id

    public String getOrderBy(){
        return StringUtils.hasLength(orderBy)?orderBy:null;
    }
    public String getKeyword(){
        return StringUtils.hasLength(keyword)?keyword:null;
    }

}
