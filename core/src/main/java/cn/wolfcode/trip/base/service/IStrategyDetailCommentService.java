package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyDetailComment;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IStrategyDetailCommentService {
    PageInfo getAllCommentById(QueryObject qo);

    /**
     * 保存评论
     * @param strategyDetailComment
     */
    void save(StrategyDetailComment strategyDetailComment);
}
