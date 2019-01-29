package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface ICommentService {

    /**
     * 获取当前用户未被查看的评论
     * @return
     */
    int getUnreadCommentNumber();
    /**
     * 获取被评论的游记数据
     * @param qo
     * @return
     */
    PageInfo getTravelHistory(QueryObject qo);
    /**
     * 获取被评论的朋友圈
     * @param qo
     * @return
     */
    PageInfo getFriendHistory(QueryObject qo);

    /**
     * 把为查看状态改成已查看  游记的
     */
    void changeTravelState(Long id);
    /**
     * 把为查看状态改成已查看  朋友圈的
     */
    void changeFriendState(Long id);
}
