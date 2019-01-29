package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 攻略分类
 */
@Setter
@Getter
@JsonIgnoreProperties("handler")
public class StrategyCatalog extends BaseDomain{
    //攻略分类名称
    private String name;
    //攻略
    private Strategy strategy;
    //序号
    private Integer sequence;
    //状态
    private Boolean state;
    //分类细节
    private List<StrategyDetail> details = new ArrayList<>();


    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        if(strategy!=null){
            map.put("strategyId",strategy.getId());
            map.put("strategyTitle",strategy.getTitle());
        }

        map.put("sequence",sequence);
        map.put("state",state);
        return JSONUtils.toJSONString(map);
    }
}