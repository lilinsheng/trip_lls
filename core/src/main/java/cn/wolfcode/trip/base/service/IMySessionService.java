package cn.wolfcode.trip.base.service;


import cn.wolfcode.trip.base.domain.MySession;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IMySessionService {

    PageInfo getMyFriend(QueryObject qo);

    PageInfo getMyChats(QueryObject qo, Long interlocutorId);

    // 保存消息
    void saveMsg(MySession mySession);

    // 间隔时间查询别人新发的数据    并改变状态为已查看
    List<Map> getInterlocutorMsg(MySession mySession);

    // 改变状态
    void changeStateOnFirst(Long interlocutorId);
    // 改变状态
    void changeState(MySession mySession);
    // 获取未被查看的私信数
    int getUnreadMsgNumber();
    // 添加好友
    void addFriend(Long friendId);
    // 查询申请添加你的好友
    List<User> getFriendsForAdd();
    // 拒绝添加好友
    void deleteFriend(Long friendId);
}
