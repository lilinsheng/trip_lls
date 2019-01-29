package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Mall;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Mall record);

    Mall selectByPrimaryKey(Long id);

    List<Mall> selectAll();

    int updateByPrimaryKey(Mall record);

    List<Mall> selectMall(QueryObject qo);

    void updateScore(@Param("score") Long score, @Param("userId") Long userId);
}