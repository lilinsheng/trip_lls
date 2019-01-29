package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Friend;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.FriendMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IFriendService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FriendServiceImpl implements IFriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public PageInfo queryFriend(QueryObject qo, Long id) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<Friend> list = friendMapper.selectFriend(qo, id);
        System.out.println(list.get(0).getIsFabulousName());
        return new PageInfo(list);
    }

    public void save(Friend friend) {
        friend.setCreateDate(new Date());
        friendMapper.insert(friend);
    }

    @Override
    public void friendFabulous(Long friendId) {
        User user = UserContext.getUser();
        //保存朋友圈的关注信息
        friendMapper.insertFriendRelation(user.getId(),friendId);
    }

    @Override
    public List<User> getFabulousNames(Long friendId) {
        return friendMapper.selectFabulous(friendId);
    }

    @Override
    public void disFriendFabulous(Long friendId) {
        User user = UserContext.getUser();
        //移除朋友圈的关注信息
        friendMapper.deleteFriendRelation(user.getId(),friendId);
    }
}
