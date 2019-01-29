package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Scenic;
import cn.wolfcode.trip.base.domain.Scenic;
import cn.wolfcode.trip.base.query.QueryObject;

import java.util.List;

public interface ScenicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Scenic record);

    Scenic selectByPrimaryKey(Integer id);

    List<Scenic> selectAll();

    int updateByPrimaryKey(Scenic record);

    List<Scenic> selectScenic(QueryObject qo);
}