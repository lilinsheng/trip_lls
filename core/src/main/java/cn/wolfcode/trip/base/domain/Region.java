package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Setter
@Getter
/**
 * 地区
 */
public class Region extends BaseDomain{
    public static final Integer STATE_COMMON = 0;//普通
    public static final Integer STATE_HOT = 1;//热门

    //名称
    private String name;
    //父级地区
    private Region parent;
    //状态
    private Integer state = STATE_COMMON;

    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",this.id);
        map.put("name",this.name);
        map.put("status",this.state);
        if (this.parent!=null){
            map.put("parentId",this.parent.getId());
            map.put("parentName",this.parent.getName());
        }
        return JSONUtils.toJSONString(map);
    }
}