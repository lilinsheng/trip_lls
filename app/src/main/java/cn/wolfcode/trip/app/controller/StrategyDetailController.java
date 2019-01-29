package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 攻略文章
 */
@RestController
@RequestMapping("/strategyDetails")
public class StrategyDetailController {
    @Autowired
    private IStrategyDetailService strategyDetailService;


    /**
     * 通过id获取攻略内容
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public StrategyDetail getDetailsById(@PathVariable Long id){
        return strategyDetailService.getStrategyContentById(id);
    }
}
