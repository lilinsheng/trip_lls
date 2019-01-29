package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 攻略文章
 */
@Setter
@Getter
public class StrategyDetail extends BaseDomain{
    public static final int STATE_RELEASE = 0;//发布
    public static final int STATE_DRAFT = -1;//草稿

    //标题
    private String title;
    //创建时间
    private Date createTime;
    //发布时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseTime;
    //序号
    private Integer sequence;
    //分类
    private StrategyCatalog catalog;
    //封面
    private String coverUrl;
    //状态
    private Integer state = STATE_RELEASE;
    //攻略文章
    private StrategyContent content;

    public String getStateName(){
        if (state==STATE_RELEASE){
            return "发布";
        }else {
            return "草稿";
        }
    }

    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("releaseTime",releaseTime);
        map.put("sequence",sequence);
        if (catalog!=null){
            map.put("catalogId",catalog.getId());
            map.put("catalogName",catalog.getName());
            if (catalog.getStrategy()!=null){
                map.put("strategyId",catalog.getStrategy().getId());
            }
        }
        map.put("coverUrl",coverUrl);
        map.put("state",state);
        return JSONUtils.toJSONString(map);
    }
}