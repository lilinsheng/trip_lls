package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter
@Getter
public class TravelCommendQueryObject extends QueryObject{
    private String keyword;//关键字
    private Integer state = 0;//状态

    public String getKeyword(){
        return StringUtils.hasLength(keyword)?keyword:null;
    }
}
