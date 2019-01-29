package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.TravelComment;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelCommentMapper {
    void deleteByPrimaryKey(Long id);

    void insert(TravelComment travelComment);

    TravelComment selectByPrimaryKey(Long id);

    List<TravelComment> selectAll();

    void updateByPrimaryKey(TravelComment travelComment);

    List selectCommentsByTravelId(TravelQueryObject qo);

    List<TravelComment> selectMyTravelComment(QueryObject qo,@Param("userId") Long userId);
}