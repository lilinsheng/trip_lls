package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyDetailComment;
import cn.wolfcode.trip.base.query.QueryObject;
import java.util.List;

public interface StrategyDetailCommentMapper {
    void deleteByPrimaryKey(Long id);

    void insert(StrategyDetailComment strategyDetailComment);

    StrategyDetailComment selectByPrimaryKey(Long id);

    List<StrategyDetailComment> selectAll();

    void updateByPrimaryKey(StrategyDetailComment strategyDetailComment);

    List selectForList(QueryObject qo);
}