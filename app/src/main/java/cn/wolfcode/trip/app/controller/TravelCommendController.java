package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 游记推荐
 */
@RestController
@RequestMapping("/travelCommends")
public class TravelCommendController {
    @Autowired
    private ITravelCommendService travelCommendService;

    /**
     * 获取推荐游记
     */
    @GetMapping
    public PageInfo getCommends(TravelCommendQueryObject qo) {
        qo.setOrderBy("tc.schedule desc");
        PageInfo pageInfo = travelCommendService.listCommendsByType(qo);
        return pageInfo;
    }
}
