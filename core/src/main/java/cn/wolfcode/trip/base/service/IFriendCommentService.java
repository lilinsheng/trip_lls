package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Friend;
import cn.wolfcode.trip.base.domain.FriendComment;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IFriendCommentService {
    /**
     * 评论朋友圈
     */
    void commentFriend(FriendComment friendComment);

    /**
     * 获取单个朋友圈下的所有信息
     * @param friendId
     */
    Friend getCommentByFriendId(Long friendId);

    /**
     * 查询评论的朋友圈
     * @param qo
     * @return
     */
    PageInfo getFriendDiscuss(QueryObject qo);
}
