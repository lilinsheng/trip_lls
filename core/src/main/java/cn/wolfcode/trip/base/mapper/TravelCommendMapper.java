package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import java.util.List;
import java.util.Map;

public interface TravelCommendMapper {
    void deleteByPrimaryKey(Long id);

    void insert(TravelCommend travelCommend);

    TravelCommend selectByPrimaryKey(Long id);

    List<TravelCommend> selectAll();

    void updateByPrimaryKey(TravelCommend travelCommend);

    List<TravelCommend> selectForList(TravelCommendQueryObject qo);

    List<Map> selectCommendsByType(TravelCommendQueryObject qo);
    // 查看通知后,把状态改为已查看
    void updateState(Long id);
}