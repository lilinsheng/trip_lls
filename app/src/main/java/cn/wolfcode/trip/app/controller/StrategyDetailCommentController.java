package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.StrategyDetailComment;
import cn.wolfcode.trip.base.query.CommentQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyDetailCommentService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 攻略点评评论
 */

@RestController
@RequestMapping("/strategydetailcomments")
public class StrategyDetailCommentController {
    @Autowired
    private IStrategyDetailCommentService strategyDetailCommentService;

    /**
     * 获取当前攻略点评下的所有评论
     * @param qo
     * @return
     */
    @GetMapping("/{strategyCommentId}")
    public PageInfo getAllCommentById(CommentQueryObject qo){
        PageInfo pageInfo = strategyDetailCommentService.getAllCommentById(qo);
        return pageInfo;
    }

    /**
     * 保存攻略点评下的评论
     */
    @PostMapping
    public JsonResult commentStrategy(StrategyDetailComment strategyDetailComment){
        strategyDetailCommentService.save(strategyDetailComment);
        return new JsonResult();
    }
}
