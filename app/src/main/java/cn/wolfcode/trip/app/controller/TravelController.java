package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 游记
 */
@RestController
@RequestMapping("/travels")
public class TravelController {
    @Autowired
    private ITravelService travelService;

    @PostMapping
    public JsonResult save(Travel travel){
        travelService.saveOrUpdate(travel);
        return new JsonResult();
    }

    @PutMapping("/{id}")
    public JsonResult update(Travel travel){
        travelService.saveOrUpdate(travel);
        return new JsonResult();
    }

    /**
     * 根据id获取游记
     */
    @GetMapping("/{id}")
    public Travel getTravelById(@PathVariable Long id){
        return travelService.getTravelById(id);
    }

    /**
     * 获取所有的已发布的游记
     */
    @GetMapping
    public PageInfo getAllTravers(TravelQueryObject qo){
        qo.setState(Travel.STATE_RELEASE);//已发布
        qo.setIsPublic(true);//公开
        qo.setOrderBy("lastUpdateTime desc");//按最后更新时间排序
        PageInfo pageInfo = travelService.queryTravel(qo);
        return pageInfo;
    }

    /**
     * 游记点赞
     */
    @PostMapping("{id}/like")
    public JsonResult fabulous(@PathVariable Long id){
        travelService.fabulous(id);
        Integer number = travelService.getFabulousByTravel(id);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }

    /**
     * 游记取消点赞
     */
    @DeleteMapping("{id}/disLike")
    public JsonResult disFabulous(@PathVariable Long id){
        travelService.reductFabulous(id);
        Integer number = travelService.getFabulousByTravel(id);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }

    /**
     * 游记收藏
     */
    @PatchMapping("/collect/{travelId}")
    public JsonResult collectStrategy(@PathVariable Long travelId){
        //游记收藏
        travelService.collectTratel(travelId);
        //获取当前游记收藏的数量
        int number = travelService.getTravelCollection(travelId);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }

    /**
     * 取消游记收藏
     */
    @PatchMapping("/disCollect/{travelId}")
    public JsonResult disCollectTravel(@PathVariable Long travelId){
        //取消游记收藏
        travelService.disCollectTratel(travelId);
        //获取当前游记收藏的数量
        int number = travelService.getTravelCollection(travelId);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }


}
