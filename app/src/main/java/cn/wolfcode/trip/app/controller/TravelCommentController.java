package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.StrategyDetailComment;
import cn.wolfcode.trip.base.domain.TravelComment;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommentService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 游记评论
 */
@RestController
@RequestMapping("travelComments")
public class TravelCommentController {
    @Autowired
    private ITravelCommentService travelCommentService;

    /**
     * 查询当前游记下的所有评论
     */
    @GetMapping("/{travelId}/comments")
    public PageInfo getCommentsByTravelId(TravelQueryObject qo){
        PageInfo pageInfo = travelCommentService.getCommentsByTravelId(qo);
        return pageInfo;
    }

    /**
     * 保存攻略点评下的评论
     */
    @PostMapping
    public JsonResult commentStrategy(TravelComment travelComment){
        travelCommentService.save(travelComment);
        return new JsonResult();
    }
}
