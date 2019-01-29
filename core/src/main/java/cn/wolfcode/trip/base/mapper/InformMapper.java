package cn.wolfcode.trip.base.mapper;

import java.util.List;
import java.util.Map;

public interface InformMapper {
    // 查询所有通知
    List<Map> selectAll(Long userId);
    // 查询未被查看过的通知数  游记的
    int selectCountTravelNum(Long userId);
    // 查询未被查看过的通知数  攻略的
    int selectCountTravelCommendNum(Long userId);
}
