package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 景区门票
 */
@Setter
@Getter
public class Scenic extends BaseDomain {

    private String title;//景区标题

    private String imgUrl;//景区封面

    private Integer price;//门票价格

    private String region;//景区位置

    public String getJsonString() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("imgUrl", imgUrl);
        map.put("price", price);
        map.put("region", region);
        return JSONUtils.toJSONString(map);
    }

}