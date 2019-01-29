package cn.wolfcode.trip.base.mapper;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    // 查看未被查看的游记评论数
    int selectUnreadTravelCount(Long userId);

    // 查看未被查看的朋友圈动态评论数
    int selectUnreadFriendCount(Long userId);

    // 查询被评论的游记
    List<Map> selectTravelHistory(Long userId);
    // 查询被评论的朋友圈
    List<Map> selectFriendHistory(Long userId);

    // 把未查看状态改为已查看  游记的
    void updateTravelState(Long id); // 游记的
    // 把未查看状态改为已查看  朋友圈的
    void updateFriendState(Long id);
}
