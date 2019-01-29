package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter
@Getter
public class StrategyQueryObject extends QueryObject{
    private String keyword;//关键字

    private Integer state;//状态

    private Long placeId;//地区id

    public String getKeyword(){
        return StringUtils.hasLength(keyword)?keyword:null;
    }
}
