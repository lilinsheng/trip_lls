package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.StrategyCatalogQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StrategyCatalogMapper {
    void deleteByPrimaryKey(Long id);

    void insert(StrategyCatalog strategyCatalog);

    StrategyCatalog selectByPrimaryKey(Long id);

    List<StrategyCatalog> selectAll(@Param("strategyId") Long strategyId);

    void updateByPrimaryKey(StrategyCatalog strategyCatalog);

    List<StrategyCatalog> selectForList(StrategyCatalogQueryObject qo);

    int selectMaxSequence(Long id);
}