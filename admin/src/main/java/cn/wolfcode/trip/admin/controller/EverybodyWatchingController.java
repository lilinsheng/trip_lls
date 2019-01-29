package cn.wolfcode.trip.admin.controller;


import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.StrategyCatalogQueryObject;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.service.ITravelService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/everybodyWatching")
public class EverybodyWatchingController {

    @Autowired
    private IStrategyCatalogService strategyCatalogService;
    @Autowired
    private IStrategyService strategyService;

    //注入游记
    @Autowired
    private ITravelService travelService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyCatalogQueryObject qo, Model model){
        qo.setOrderBy("sequence asc");
        PageInfo pageInfo = strategyCatalogService.query(qo);
        model.addAttribute("strategies",strategyService.listStrategyByState(Strategy.STATE_COMMON,Strategy.STATE_HOT));
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("travels",travelService.selectAlltravel());
        return "everybodyWatching/list";
    }

    /*//注入攻略内容
    @Autowired
    private IStrategyService strategyService;

    //注入游记
    @Autowired
    private ITravelService travelService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyDetailQueryObject qo, Model model){
        model.addAttribute("travels",travelService.selectAlltravel());
        model.addAttribute("strategies",strategyService.selectAllStrategy(qo));
        //qo.setOrderBy("");
        return "everybodyWatching/list";
    }*/



}
