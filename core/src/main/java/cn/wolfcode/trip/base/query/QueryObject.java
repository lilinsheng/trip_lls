package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 3;
    private String orderBy;

    public int getStart() {
        return (currentPage - 1) * pageSize;
    }
    public String getOrderBy(){
        return StringUtils.hasLength(orderBy)?orderBy:null;
    }
}
