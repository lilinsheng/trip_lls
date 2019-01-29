package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.TravelComment;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import com.github.pagehelper.PageInfo;

public interface ITravelCommentService {
    /**
     * 查询该游记下的评论
     * @param qo
     * @return
     */
    PageInfo getCommentsByTravelId(TravelQueryObject qo);


    void save(TravelComment travelComment);

    /**
     * 查询本人的所有评论
     * @param qo
     * @return
     */
    PageInfo getTravelDiscuss(QueryObject qo);
}
