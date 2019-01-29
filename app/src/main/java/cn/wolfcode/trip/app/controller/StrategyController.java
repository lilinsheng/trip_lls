package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.query.TagQueryObject;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 大攻略
 */
@RestController
@RequestMapping("/strategies")
public class
StrategyController {
    @Autowired
    private IStrategyService strategyService;

    /**
     * 获取推荐的大攻略
     * @param qo
     * @return
     */
    @GetMapping
    public List<Strategy> getStrategiesByState(StrategyQueryObject qo){
        return strategyService.listStrategyByState(qo.getState());
    }

    /**
     * 根据id查询大攻略及其分类
     */
    @GetMapping("/{id}/catalogs")
    public Strategy getCatalogsByStrategyId(@PathVariable Long id){
        Strategy strategy = strategyService.getByStrategyId(id);
        return strategy;
    }

    /**
     * 获取某攻略的热门标签
     */
    @GetMapping("/{strategyId}/tags")
    public PageInfo getTags(TagQueryObject qo){
        qo.setPageSize(6);
        qo.setOrderBy("COUNT(t.id) DESC");
        return strategyService.getTagsById(qo);
    }

    /**
     * 攻略点赞
     */
    @PostMapping("{id}/fabulous")
    public JsonResult fabulous(@PathVariable Long id){
        strategyService.fabulous(id);
        Integer number = strategyService.getFabulousByStrategy(id);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }

    /**
     * 取消攻略点赞
     */
    @DeleteMapping("{id}/disFabulous")
    public JsonResult disFabulous(@PathVariable Long id){
        strategyService.disFabulous(id);
        Integer number = strategyService.getFabulousByStrategy(id);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }

    /**
     * 攻略收藏
     */
    @PatchMapping("/collect/{strategyId}")
    public JsonResult collectStrategy(@PathVariable Long strategyId){
        //收藏攻略
        strategyService.collectStrategy(strategyId);
        //获取当前的攻略收藏数
        int number = strategyService.getCollectStrategyNum(strategyId);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }

    /**
     * 取消攻略收藏
     */
    @PatchMapping("/disCollect/{strategyId}")
    public JsonResult disCollectStrategy(@PathVariable Long strategyId){
        //取消收藏攻略
        strategyService.disCollectStrategy(strategyId);
        //获取当前的攻略收藏数
        int number = strategyService.getCollectStrategyNum(strategyId);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }
}
