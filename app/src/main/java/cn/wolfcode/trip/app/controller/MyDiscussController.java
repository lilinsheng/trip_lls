package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IFriendCommentService;
import cn.wolfcode.trip.base.service.ITravelCommentService;
import cn.wolfcode.trip.base.service.ITravelService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我的评论
 */
@RestController
@RequestMapping("/discuss")
public class MyDiscussController {
    @Autowired
    private ITravelCommentService travelCommentService;
    @Autowired
    private IFriendCommentService friendCommentService;

    @GetMapping("/{state}")
    public PageInfo getDiscuss(QueryObject qo,@PathVariable Long state){
        //state=1,查询游记
        PageInfo pageInfo = null;
        if(state==1){
            pageInfo = travelCommentService.getTravelDiscuss(qo);
        }else if (state==2){
            pageInfo = friendCommentService.getFriendDiscuss(qo);
        }
        return pageInfo;
    }
}
