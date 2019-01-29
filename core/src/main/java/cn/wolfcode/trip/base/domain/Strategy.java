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
 * 大攻略
 */
@Setter
@Getter
@JsonIgnoreProperties("handler")
public class Strategy extends BaseDomain{

    public static final Integer STATE_COMMON = 0;//普通
    public static final Integer STATE_HOT = 1;//热门
    public static final Integer STATE_DISABLED = -1;//禁用

    //所属地区
    private Region place;
    //攻略标题
    private String title;
    //攻略副标题
    private String subTitle;
    //封面
    private String coverUrl;
    //状态
    private Integer state;
    //攻略分类
    private List<StrategyCatalog> catalogs = new ArrayList<>();
    //大攻略点赞数
    private Long fabulousNum;
    //大攻略收藏数
    private Long collectNum;

    public String getStateName(){
        if (state==STATE_COMMON){
            return "正常";
        }else if (state==STATE_HOT){
            return "推荐";
        }else {
            return "禁用";
        }
    }

    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        if (place!=null){
            map.put("placeId",place.getId());
        }
        map.put("title",title);
        map.put("subTitle",subTitle);
        map.put("coverUrl",coverUrl);
        map.put("state",state);
        return JSONUtils.toJSONString(map);
    }

}