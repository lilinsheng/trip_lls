package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 游记推荐
 */
@Setter
@Getter
public class TravelCommend extends BaseDomain{

    public static final int TYPE_WEEK = 1;//每周推荐
    public static final int TYPE_MONTH = 2;//每月推荐
    public static final int TYPE_STRATEGY = 3;//攻略推荐

    //游记
    private Travel travel;
    //主标题
    private String title;
    //副标题
    private String subTitle;
    //封面
    private String coverUrl;
    //推荐时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date schedule;
    //类型
    private Integer type;

    public String getTypeName(){
        if (type==TYPE_WEEK){
            return "每周推荐";
        }else if (type==TYPE_MONTH){
            return "每月推荐";
        }else {
            return "攻略推荐";
        }
    }

    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("travelId",travel.id);
        map.put("title",title);
        map.put("subTitle",subTitle);
        map.put("coverUrl",coverUrl);
        map.put("schedule",new SimpleDateFormat("yyyy-MM-dd").format(schedule));
        map.put("type",type);
        return JSONUtils.toJSONString(map);
    }
}