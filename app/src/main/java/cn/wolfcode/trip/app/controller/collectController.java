package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.NewsPage;
import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/collects")
public class collectController {

    //注入用户
    @Autowired
    private IUserService userService;

    //查收藏攻略
    @GetMapping("/getAllStravel")
    public List<Strategy> getAllStravel(){

        User user = UserContext.getUser();
        List<Strategy>  list = userService.getAllStravel(user.getId());
        return list;
    }

    //查收藏游记
    @GetMapping("/getTravelCollect")
    public List<Travel> getTravelCollect(){

        User user = UserContext.getUser();
        List<Travel> list = userService.getTravelCollect(user.getId());
        return list;
    }

    //查收藏日报
    @GetMapping("/getNewsPageCollect")
    public List<NewsPage> getNewsPageCollect(){

        User user = UserContext.getUser();
        List<NewsPage> list = userService.getNewsPageCollect(user.getId());
        return list;
    }


}
