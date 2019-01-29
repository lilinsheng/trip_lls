package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 游记
 */
@Setter
@Getter
@JsonIgnoreProperties("handler")
public class Travel extends BaseDomain{

    public static final int STATE_DEFAULT = 0;//待发布
    public static final int STATE_RELEASE = 1;//已发布
    public static final int STATE_DRAFT = 2;//草稿
    public static final int STATE_REFUSE = -1;//已拒绝

    //标题
    private String title;
    //出发时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date travelTime;
    //人均消费
    private String perExpends;
    //出行天数
    private Integer days;
    //参与人物
    private Integer person;
    //作者
    private User author;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    //发布时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseTime;
    //是否公开
    private Boolean isPublic;
    //地区
    private Region place;
    //封面图片url
    private String coverUrl;
    //最后更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastUpdateTime;
    //状态
    private Integer state = STATE_DEFAULT;
    //游记内容
    private TravelContent travelContent;
    //评论数
    private Long fabulousNum;
    //游记收藏数
    private Long collectionNum;
    //游记评论数
    private Long commentNum;



    public String getStateName(){
        String str = null;
        switch (state){
            case STATE_RELEASE:
                str = "已发布";break;
            case STATE_DRAFT:
                str = "草稿";break;
            case STATE_REFUSE:
                str = "已拒绝";break;
            default:str = "待发布";
        }
        return str;
    }

    public String getPersonName(){
        String str = null;
        switch (state){
            case 1:
                str = "一个人的旅行";break;
            case 2:
                str = "和父母";break;
            case 3:
                str = "和朋友";break;
            case 4:
                str = "和同事";break;
            case 5:
                str = "和爱人";break;
            default:str = "和其他";
        }
        return str;
    }

    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("coverUrl",coverUrl);
        return JSONUtils.toJSONString(map);
    }

}