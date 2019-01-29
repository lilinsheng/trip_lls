package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
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

/**
 *大攻略
 */
@Controller
@RequestMapping("/strategy")
public class StrategyController {
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IRegionService regionService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyQueryObject qo, Model model){
        PageInfo pageInfo = strategyService.query(qo);
        model.addAttribute("regions",regionService.listAll());
        model.addAttribute("pageInfo",pageInfo);
        return "strategy/list";
    }

    /**
     * 攻略添加
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Strategy strategy, MultipartFile file){
        if (file!=null){
            String upload = QiniuUtil.QI_PATH+QiniuUtil.qiniuyunImage(file);
            strategy.setCoverUrl(upload);
        }
        strategyService.saveOrUpdate(strategy);
        return new JsonResult();
    }
}
