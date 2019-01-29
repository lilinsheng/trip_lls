package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.query.NewPagesQueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.query.UserQueryObject;
import cn.wolfcode.trip.base.service.INewsPageService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class searchController {

    //注入大攻略
    @Autowired
    private IStrategyService strategyService;

    //注入游记
    @Autowired
    private ITravelService travelService;

    //注入日报
    @Autowired
    private INewsPageService newsPageService;

    //注入用户
    @Autowired
    private IUserService userService;


    //查攻略
    @GetMapping("/strategies")
    public PageInfo getSearchCounts(StrategyQueryObject qo) {
        PageInfo pageInfo = strategyService.getStrategiesCounts(qo);
        return pageInfo;
    }

    //获取所有的游记
    @GetMapping("/travels")
    public PageInfo getTraversCounts(TravelQueryObject qo){
        PageInfo pageInfo = travelService.getTraversCounts(qo);
        return pageInfo;
    }
    //获取所有的日报
    @GetMapping("/newPages")
    public PageInfo getNewsPageCounts(NewPagesQueryObject qo){
        PageInfo pageInfo = newsPageService.getNewsPageCounts(qo);
        return pageInfo;
    }


    //查用户
    @GetMapping("/users")
    public PageInfo getUserNickNameCounts(UserQueryObject qo){
        PageInfo pageInfo = userService.getUserNickNameCounts(qo);
        return pageInfo;
    }




}
