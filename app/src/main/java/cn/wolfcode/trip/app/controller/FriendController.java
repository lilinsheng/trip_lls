package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Friend;
import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IFriendService;
import cn.wolfcode.trip.base.util.FabulousUtil;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private IFriendService friendService;

    /**
     * 根据用户id查朋友圈
     *
     * @param qo
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public PageInfo getAllTravers(QueryObject qo, @PathVariable Long id) {
        qo.setOrderBy("createDate desc");//按最后更新时间排序
        qo.setPageSize(10);
        PageInfo pageInfo = friendService.queryFriend(qo, id);
        return pageInfo;
    }


    /**
     * 发表朋友圈
     */
    @PostMapping
    public JsonResult save(Friend friend) {
        friendService.save(friend);
        return new JsonResult();
    }

    /**
     * 朋友圈关注
     */
    @PostMapping("/fabulous/{friendId}")
    public JsonResult friendFabulous(@PathVariable Long friendId){
        //朋友圈关注
        friendService.friendFabulous(friendId);
        //查询关注的人
        List<User> list = friendService.getFabulousNames(friendId);
        String names = FabulousUtil.getFabulousNames(list);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(names);
        return jsonResult;
    }

    /**
     * 取消朋友圈关注
     */
    @PostMapping("/disFabulous/{friendId}")
    public JsonResult disFriendFabulous(@PathVariable Long friendId){
        //取消朋友圈关注
        friendService.disFriendFabulous(friendId);
        //查询关注的人
        List<User> list = friendService.getFabulousNames(friendId);
        String names = FabulousUtil.getFabulousNames(list);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(names);
        return jsonResult;
    }
}
