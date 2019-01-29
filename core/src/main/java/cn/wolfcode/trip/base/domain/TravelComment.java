package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
/**
 * 游记评论
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties("handler")
public class TravelComment extends BaseDomain{

    private User user;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String content;

    private Travel travel;

    private Long parentId;

    private Integer state;

    private List<TravelComment> comments = new ArrayList<>();

}