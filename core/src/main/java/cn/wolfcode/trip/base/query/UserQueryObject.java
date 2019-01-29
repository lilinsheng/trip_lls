package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
@Setter
@Getter
public class UserQueryObject{
    private String keyword;
    private int currentPage = 1;
    private int pageSize = 7;

    public int getStart() {
        return (currentPage - 1) * pageSize;
    }

    public String getKeyword(){
        return StringUtils.hasLength(keyword)?keyword:null;
    }
}
