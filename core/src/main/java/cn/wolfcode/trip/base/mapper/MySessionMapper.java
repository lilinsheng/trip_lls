package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.MySession;
import cn.wolfcode.trip.base.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MySessionMapper {
    int insert(MySession mySession);

    List<MySession> selectAll();

    List<Map> selectMyFriend(Long userId);

    List<Map> selectMyChats(@Param("userId") Long userId, @Param("interlocutorId") Long interlocutorId);

    List<Map> selectInterlocutorMsg(MySession mySession);

    void updateState(MySession mySession);

    void updateStateOnFirst(@Param("userId") Long userId, @Param("interlocutorId") Long interlocutorId);
    // 获取未被查看的私信数
    int selectCountUnreadMsg(Long userId);
    // 添加好友
    void insertMyFriend(@Param("userId") Long userId, @Param("friendId") Long friendId);
    // 查询申请添加你的好友
    List<User> selectFriendsForAdd(Long userId);
    // 拒绝添加好友
    void deleteFriend(@Param("userId") Long userId, @Param("friendId") Long friendId);
}