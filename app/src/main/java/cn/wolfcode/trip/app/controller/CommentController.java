package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ICommentService;
import cn.wolfcode.trip.base.service.IFabulousService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    /**
     * 获取当前用户未被查看的评论数
     *
     * @return
     */
    @GetMapping("/commentNumber")
    public Map<String, Object> getUnreadCommentNumber() {
        int number = commentService.getUnreadCommentNumber();
        HashMap<String, Object> map = new HashMap<>();
        map.put("unreadCommentNumber", number);
        return map;
    }

    /**
     * 获取被评论的游记
     * @param qo
     * @return
     */
    @GetMapping("/travelCommentHistory")
    public PageInfo getTravelCommentHistory(QueryObject qo) {
        PageInfo pageInfo = commentService.getTravelHistory(qo);
        return pageInfo;
    }

    /**
     * 获取被评论的朋友圈
     * @param qo
     * @return
     */
    @GetMapping("/friendCommentHistory")
    public PageInfo getFriendCommentHistory(QueryObject qo) {
        PageInfo pageInfo = commentService.getFriendHistory(qo);
        return pageInfo;
    }

    /**
     * 把未查看转换成已查看  游记的
     */
    @PatchMapping("/changeTravelState")
    public void changeTravelState(Long id){
        commentService.changeTravelState(id);
    }

    /**
     * 把未查看转换成已查看  朋友圈的
     */
    @PatchMapping("/changeFriendState")
    public void changeFriendState(Long id){
        commentService.changeFriendState(id);
    }
}
