package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Friend;
import cn.wolfcode.trip.base.domain.FriendComment;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.FriendCommentMapper;
import cn.wolfcode.trip.base.mapper.FriendMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IFriendCommentService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FriendCommentServiceImpl implements IFriendCommentService {
    @Autowired
    private FriendCommentMapper friendCommentMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public void commentFriend(FriendComment friendComment) {
        User user = UserContext.getUser();
        friendComment.setUser(user);
        friendComment.setCreateTime(new Date());
        friendComment.setState(1);
        friendCommentMapper.insert(friendComment);
    }

    @Override
    public Friend getCommentByFriendId(Long friendId) {
        return friendMapper.selectByPrimaryKey(friendId);
    }

    @Override
    public PageInfo getFriendDiscuss(QueryObject qo) {
        User user = UserContext.getUser();
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Map> list = friendCommentMapper.selectFriendDiscuss(qo,user.getId());
        return new PageInfo(list);
    }
}
