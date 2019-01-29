package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.StrategyCatalogQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *攻略分类
 */
@Controller
@RequestMapping("/strategyCatalog")
public class StrategyCatalogController {
    @Autowired
    private IStrategyCatalogService strategyCatalogService;
    @Autowired
    private IStrategyService strategyService;
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyCatalogQueryObject qo, Model model){
        qo.setOrderBy("sequence asc");
        PageInfo pageInfo = strategyCatalogService.query(qo);
        model.addAttribute("strategies",strategyService.listStrategyByState(Strategy.STATE_COMMON,Strategy.STATE_HOT));
        model.addAttribute("pageInfo",pageInfo);
        return "strategyCatalog/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StrategyCatalog strategyCatalog){
        if (strategyCatalog.getSequence()==null){
            int max = strategyCatalogService.getMaxSequence(strategyCatalog.getStrategy().getId());
            strategyCatalog.setSequence(max+1);
        }
        strategyCatalogService.saveOrUpdate(strategyCatalog);
        return new JsonResult();
    }

    /**
     * 根据大攻略id查询攻略分类
     */
    @RequestMapping("/getCatalogByStrategyId")
    @ResponseBody
    public List<StrategyCatalog> getCatalogByStrategyId(Long strategyId){
        List<StrategyCatalog> list = strategyCatalogService.selectCatalogByStrategyId(strategyId);
        return list;
    }
}
