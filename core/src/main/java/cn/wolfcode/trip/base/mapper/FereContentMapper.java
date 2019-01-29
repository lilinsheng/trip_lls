package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.FereContent;
import cn.wolfcode.trip.base.query.FereContentQueryObject;
import java.util.List;

public interface FereContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FereContent record);

    FereContent selectByPrimaryKey(Long id);

    List<FereContent> selectAll();

    int updateByPrimaryKey(FereContent record);

    List<FereContent> selectForAll(FereContentQueryObject qo);

}