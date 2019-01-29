package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Friend;
import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Friend record);

    Friend selectByPrimaryKey(Long id);

    List<Friend> selectAll();

    int updateByPrimaryKey(Friend record);

    List<Friend> selectFriend(QueryObject qo, @Param("id") Long id);

    void insertFriendRelation(@Param("userId") Long userId, @Param("friendId") Long friendId);

    List<User> selectFabulous(Long friendId);

    void deleteFriendRelation(@Param("userId") Long userId, @Param("friendId") Long friendId);
}