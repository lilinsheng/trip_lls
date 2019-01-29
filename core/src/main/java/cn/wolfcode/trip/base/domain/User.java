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
 * 注册用户
 */
@Setter
@Getter
@JsonIgnoreProperties("handler")
public class User extends BaseDomain{
    public static final int MAN = 1;//男
    public static final int WOMAN = 0;//女
    public static final int SECRET = -1;//保密

    //邮箱
    private String email;
    //昵称
    private String nickName;
    //密码
    private String password;
    //所在地区
    private String place;
    //头像
    private String headImgUrl;
    //性别
    private Integer gender = SECRET;
    //封面
    private String coverImgUrl;
    //签名
    private String sign;
    //粉丝数
    private Integer fans;

    private Long score;

    private List<Travel> travels = new ArrayList<>();

    private Long travelNums;

    //获取评论者的id和昵称
    public String getCommentJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("nickName",nickName);
        return JSONUtils.toJSONString(map);
    }

    public String getGenderName(){
        if (gender==MAN){
            return "男";
        }else if (gender==WOMAN){
            return "女";
        }else {
            return "保密";
        }
    }
}