package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Friend;
import cn.wolfcode.trip.base.domain.FriendComment;
import cn.wolfcode.trip.base.service.IFriendCommentService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 朋友圈评论
 */
@RestController
@RequestMapping("/friendComments")
public class FriendCommentController {

    @Autowired
    private IFriendCommentService friendCommentService;

    /**
     * 评论朋友圈
     */
    @PostMapping("/friend")
    public JsonResult friendComment(FriendComment friendComment){
        JsonResult jsonResult = new JsonResult();
        friendCommentService.commentFriend(friendComment);
        Friend friend = friendCommentService.getCommentByFriendId(friendComment.getFriend().getId());
        jsonResult.setResult(friend);
        return jsonResult;
    }
}
