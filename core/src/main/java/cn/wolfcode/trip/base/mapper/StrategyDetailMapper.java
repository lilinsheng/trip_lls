package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import java.util.List;

public interface StrategyDetailMapper {
    void deleteByPrimaryKey(Long id);

    void insert(StrategyDetail strategyDetail);

    StrategyDetail selectByPrimaryKey(Long id);

    List<StrategyDetail> selectAll();

    void updateByPrimaryKey(StrategyDetail strategyDetail);

    List<StrategyDetail> selectForList(StrategyDetailQueryObject qo);

    int getMaxSequence();

    StrategyDetail selectStrategyContentById(Long id);
}