package cn.wolfcode.trip.base.mapper;

import java.util.List;
import java.util.Map;

public interface FabulousMapper {
    // 查看未被查看的游记点赞数
    int selectUnreadTravelCount(Long userId);

    // 查看未被查看的朋友圈动态点赞数
    int selectUnreadFriendCount(Long userId);

    // 查询点赞历史:点赞者的名称,点赞内容的内容,点赞的类型
    List<Map> selectFabulousHistory(Long userId);

    // 把未查看状态改为已查看
    void updateTravelState(Long id); // 游记的

    void updateFriendState(Long id); // 朋友圈动态的
}