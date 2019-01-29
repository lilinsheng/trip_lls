package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyContent;
import java.util.List;

public interface StrategyContentMapper {
    void deleteByPrimaryKey(Long id);

    void insert(StrategyContent strategyContent);

    StrategyContent selectByPrimaryKey(Long id);

    List<StrategyContent> selectAll();

    void updateByPrimaryKey(StrategyContent strategyContent);
}