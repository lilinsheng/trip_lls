package cn.wolfcode.trip.base.domain;

import cn.wolfcode.trip.base.util.FabulousUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 朋友圈
 */

@Getter
@Setter
@JsonIgnoreProperties("handler")
public class Friend extends BaseDomain {

    private String imgUrls;//图片内容

    private String content;//文字内容

    private User user;//用户

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;//朋友圈创建时间
    //一级评论
    private List<FriendComment> friendComments = new ArrayList<>();

    //获取关注人的信息
    private List<User> fabulous = new ArrayList<>();

    //当前用户是否点赞该朋友圈
    private Long isFabulousName;

    public String getIconClass(){

        if (isFabulousName!=null&&isFabulousName>0){
            return "fa heartImg fa-heart-o";
        }else {
            return "fa heartImg fa-heart";
        }
    }

    public String getFabulousName(){
        return FabulousUtil.getFabulousNames(fabulous);
    }

    public String[] getImgArr() {
        if (imgUrls==null){
            return null;
        }
        return imgUrls.split(";");
    }

}