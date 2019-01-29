package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.FriendComment;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FriendCommentMapper {
    void deleteByPrimaryKey(Long id);

    void insert(FriendComment friendComment);

    FriendComment selectByPrimaryKey(Long id);

    List<FriendComment> selectAll();

    void updateByPrimaryKey(FriendComment friendComment);

    List<Map> selectFriendDiscuss(QueryObject qo, @Param("userId") Long userId);
}