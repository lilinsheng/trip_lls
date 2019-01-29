package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.MySession;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IMySessionService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mySession")
public class MySessionController {
    @Autowired
    private IMySessionService mySessionService;

    /**
     * 查询出所有好友
     *
     * @param qo
     * @return
     */
    @GetMapping("/myFriends")
    public PageInfo getMyFriend(QueryObject qo) {
        qo.setPageSize(0);
        return mySessionService.getMyFriend(qo);
    }

    /**
     * 查询聊天记录
     *
     * @param qo
     * @param interlocutorId
     * @return
     */
    @GetMapping("/myChats")
    public PageInfo getMyChats(QueryObject qo, Long interlocutorId) {
        qo.setPageSize(10);
        PageInfo pageInfo = mySessionService.getMyChats(qo, interlocutorId);
        // 改变状态
        mySessionService.changeStateOnFirst(interlocutorId);
        return pageInfo;
    }

    /**
     * 保存消息
     *
     * @param mySession
     */
    @PostMapping("/saveMsg")
    public void saveMsg(MySession mySession) {
        mySessionService.saveMsg(mySession);
    }

    /**
     * 查询别人发送的数据,   并改变状态为已查看
     *
     * @return
     */
    @GetMapping("/getInterlocutorMsg")
    public List<Map> getInterlocutorMsg(MySession mySession) {
        List<Map> list = mySessionService.getInterlocutorMsg(mySession);
        // 改变状态
        mySessionService.changeState(mySession);
        return list;
    }

    /**
     * 获取未被查看的私信数
     * @return
     */
    @GetMapping("/unreadNumber")
    public int getUnreadMsgNumber(){
        return mySessionService.getUnreadMsgNumber();
    }

    /**
     * 添加好友
     * @param friendId
     */
    @PostMapping("/myFriend")
    public JsonResult addFriend(Long friendId){
        mySessionService.addFriend(friendId);
        return new JsonResult();
    }

    /**
     * 查询申请添加你的好友
     */
    @GetMapping("/myFriendsForAdd")
    public List<User> getFriendsForAdd(){
        return mySessionService.getFriendsForAdd();
    }

    /**
     * 拒绝添加好友
     * @param friendId
     */
    @DeleteMapping("/{friendId}")
    public void deleteFriend(@PathVariable Long friendId){
        mySessionService.deleteFriend(friendId);
    }

}
