package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.query.StrategyCommentsQueryObject;
import com.github.pagehelper.PageInfo;

public interface IStrategyCommentService {
    /**
     * 根据攻略id分页查询攻略评论
     * @param qo
     * @return
     */
    PageInfo query(StrategyCommentQueryObject qo);

    /**
     * 根据评论id查询评论
     * @param strategyId
     * @return
     */
    StrategyComment getById(Long strategyId);

    /**
     * 新增攻略评论
     * @param strategyComment
     */
    void save(StrategyComment strategyComment);

    /**
     * 保存tag标签
     * @param tag
     */
    void saveTag(Tag tag);

    /**
     * 保存评论与标签的关系
     * @param commentId
     * @param tagId
     */
    void saveRelation(Long commentId, Long tagId);


    /**
     * 查询指定用户的评论
     * @param qo
     * @return
     */
    PageInfo queryStrategyComments(StrategyCommentQueryObject qo);
}
