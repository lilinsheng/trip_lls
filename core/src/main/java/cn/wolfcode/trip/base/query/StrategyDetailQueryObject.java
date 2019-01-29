package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter
@Getter
public class StrategyDetailQueryObject extends QueryObject{
    private String keyword;

    public String getKeyword(){
        return StringUtils.hasLength(keyword)?keyword:null;
    }
}
