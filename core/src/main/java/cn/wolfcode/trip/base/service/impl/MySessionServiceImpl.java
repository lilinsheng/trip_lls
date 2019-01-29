package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.MySession;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.MySessionMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IMySessionService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MySessionServiceImpl implements IMySessionService {
    @Autowired
    private MySessionMapper mySessionMapper;

    @Override
    public PageInfo getMyFriend(QueryObject qo) {
        Long userId = UserContext.getUser().getId();
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Map> list = mySessionMapper.selectMyFriend(userId);
        return new PageInfo(list);
    }

    @Override
    public PageInfo getMyChats(QueryObject qo, Long interlocutorId) {
        Long userId = UserContext.getUser().getId(); // 用户id
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Map> list = mySessionMapper.selectMyChats(userId, interlocutorId);
        Collections.reverse(list);

        return new PageInfo(list);
    }

    // 保存消息
    @Override
    public void saveMsg(MySession mySession) {
        mySession.setSpeaker(UserContext.getUser()); // 当前用户
        mySession.setState(1);
        mySession.setCreateTime(new Date());
        mySessionMapper.insert(mySession);
    }

    // 查询别人发送的数据    并改变状态为已查看
    @Override
    public List<Map> getInterlocutorMsg(MySession mySession) {
        mySession.setSpeaker(UserContext.getUser()); // 当前用户
        // 未被查看过的信息
        mySession.setState(1);
        // 查询出数据
        List<Map> list = mySessionMapper.selectInterlocutorMsg(mySession);
        // 查询后,把状态改为0
        // mySessionMapper.updateState(mySession);
        return list;
    }

    // 改变状态
    @Override
    public void changeStateOnFirst(Long interlocutorId) {
        Long userId = UserContext.getUser().getId(); // 用户id
        mySessionMapper.updateStateOnFirst(userId, interlocutorId);
    }

    // 改变状态
    @Override
    public void changeState(MySession mySession) {
        mySession.setSpeaker(UserContext.getUser()); // 当前用户
        // 未被查看过的信息
        mySession.setState(1);
        mySessionMapper.updateState(mySession);
    }
    // 获取未被查看的私信数
    @Override
    public int getUnreadMsgNumber() {
        Long userId = UserContext.getUser().getId();
        return mySessionMapper.selectCountUnreadMsg( userId);
    }
    // 添加好友
    @Override
    public void addFriend(Long friendId) {
        Long userId = UserContext.getUser().getId();
        mySessionMapper.insertMyFriend(userId,friendId);
    }
    // 查询申请添加你的好友
    @Override
    public List<User> getFriendsForAdd() {
        Long userId = UserContext.getUser().getId();
        return mySessionMapper.selectFriendsForAdd(userId);
    }
    // 拒绝添加好友
    @Override
    public void deleteFriend(Long friendId) {
        Long userId = UserContext.getUser().getId();
        mySessionMapper.deleteFriend(userId,friendId);
    }

}
