package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Mall extends BaseDomain {

    //商品名称
    private String productName;

    //商品图片
    private String imageUrl;

    //商品售价
    private BigDecimal salePrice;

    //商品描述
    private String productDescript;


    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("productName",productName);
        map.put("imageUrl",imageUrl);
        map.put("salePrice",salePrice);
        map.put("productDescript",productDescript);
        return JSONUtils.toJSONString(map);
    }

}