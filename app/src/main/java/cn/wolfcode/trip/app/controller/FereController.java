package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.query.FereContentQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.IFereContentService;
import cn.wolfcode.trip.base.service.IFereService;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feres")
public class FereController {

    //注入结伴
    @Autowired
    private IFereService fereService;

    //注入结伴内容
    @Autowired
    private IFereContentService fereContentService;

    //注入用户信息
    @Autowired
    private IUserService userService;

    //获取所有的结伴信息
    @GetMapping
    public PageInfo selectFeres(QueryObject qo){
        PageInfo pageInfo = fereService.selectFeres(qo);
        return pageInfo;
    }
}
