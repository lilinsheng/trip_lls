package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Friend;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IFriendService {

    /**
     * 根据用户id查朋友圈
     *
     * @param qo
     * @param id
     * @return
     */
    PageInfo queryFriend(QueryObject qo,Long id);

    /**
     * 发表朋友圈
     */
    void save(Friend friend);

    /**
     * 朋友圈关注
     * @param friendId
     */
    void friendFabulous(Long friendId);

    /**获取关注该条朋友圈的所有人
     * @param friendId
     * @return
     */
    List<User> getFabulousNames(Long friendId);

    /**
     * 取消朋友圈的关注
     * @param friendId
     */
    void disFriendFabulous(Long friendId);
}
