package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class NewsPage extends BaseDomain{

    //标题
    private String title;
    //副标题
    private String subTitle;
    //封面url
    private String coverUrl;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    //文本
    private NewsPageContent content;

    public String getJsonString(){
        Map<String,Object> map=new HashMap();
            map.put("id",id);
            map.put("title",title);
            map.put("subTitle",subTitle);
            map.put("coverUrl",coverUrl);
            map.put("createTime",createTime);
        return JSONUtils.toJSONString(map);
    }
}