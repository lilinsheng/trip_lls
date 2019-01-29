package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.QiniuUtil;
import cn.wolfcode.trip.base.util.UploadUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 *攻略详情
 */
@Controller
@RequestMapping("/strategyDetail")
public class StrategyDetailController {
    @Autowired
    private IStrategyDetailService strategyDetailService;
    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyDetailQueryObject qo, Model model){
        qo.setOrderBy("sequence asc");
        PageInfo pageInfo = strategyDetailService.query(qo);
        model.addAttribute("strategies",strategyService.listStrategyByState(Strategy.STATE_COMMON,Strategy.STATE_HOT));
        model.addAttribute("pageInfo",pageInfo);
        return "strategyDetail/list";
    }

    /**
     * 攻略文章添加
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StrategyDetail strategyDetail, MultipartFile file){
        if (file!=null){
            String fileName = QiniuUtil.QI_PATH+QiniuUtil.qiniuyunImage(file);
            strategyDetail.setCoverUrl(fileName);
        }
        strategyDetailService.saveOrUpdate(strategyDetail);
        return new JsonResult();
    }

    /**
     * 根据id查询攻略文章内容
     */
    @ResponseBody
    @RequestMapping("/getContentById")
    public StrategyContent getContentById(Long id){
        StrategyContent strategyContent = strategyDetailService.getContentById(id);
        return strategyContent;
    }
}
