package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.TravelContent;
import java.util.List;

public interface TravelContentMapper {
    void deleteByPrimaryKey(Long id);

    void insert(TravelContent travelContent);

    TravelContent selectByPrimaryKey(Long id);

    List<TravelContent> selectAll();

    void updateByPrimaryKey(TravelContent travelContent);

    TravelContent selectTravelContentById(Long id);
}