package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.query.StrategyCommentsQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StrategyCommentMapper {
    void deleteByPrimaryKey(Long id);

    void insert(StrategyComment strategyComment);

    StrategyComment selectByPrimaryKey(Long id);

    List<StrategyComment> selectAll();

    void updateByPrimaryKey(StrategyComment strategyComment);

    List<StrategyComment> selectForList(StrategyCommentQueryObject qo);

    void insertRelation(@Param("commentId") Long commentId, @Param("tagId") Long tagId);

    /**
     * 查指定用户的评论
     * @param qo
     * @return
     */
    List<StrategyComment> queryStrategyComments(StrategyCommentQueryObject qo);
}