package cn.wolfcode.trip.base.service;


import cn.wolfcode.trip.base.domain.NewsPage;
import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.query.UserQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {
    void register(String nickName,String email,String password);

    User login(String email, String password);

    PageInfo<User> query(UserQueryObject qo);

    void update(User user);

    User getUserById(Long id);

    /**
     * 查看当前用户是否点赞了指定游记
     * @param travelId
     * @return
     */
    Object getFabulousState(Long travelId);

    /**
     * 查看当前用户是否点赞了指定攻略
     * @param strategyId
     * @return
     */
    Object getStrategyFabulous(Long strategyId);

    /**
     * 用户关注
     * @param focusId
     */
    void addUserAttiention(Long focusId);

    /**
     * 获取粉丝数
     * @return
     */
    Integer getFansNum(Long focusId);

    /**
     * 查看当前用户是否关注用户
     * @param focusId
     * @return
     */
    Object isAttentionUser(Long focusId);

    /**
     * 用户的取消关注
     * @param focusId
     */
    void disUserAttiention(Long focusId);

    /**
     * 查看当前用户是否收藏该游记
     * @param travelId
     * @return
     */
    Object isCollectTravel(Long travelId);

    /**
     * 获取所有用户
     * @param qo
     * @return
     */
    PageInfo getUserNickNameCounts(UserQueryObject qo);

    /**
     * 查看当前用户是否收藏了该攻略
     * @param strategyId
     * @return
     */
    Object isCollectStrategy(Long strategyId);

    /**
     * 查所有收藏游记
     * @param id
     * @return
     */
    List<Strategy> getAllStravel(Long id);

    /**
     * 查全部收藏的
     * @param id
     * @return
     */
    List<Travel> getTravelCollect(Long id);


    /**
     * 查全部收藏的日报
     * @param id
     * @return
     */
    List<NewsPage> getNewsPageCollect(Long id);

    /**
     * 根据邮箱查用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    List<User> getAllInsteres(Long userId);

    /**
     * 每日签到
     */
    int dailySign(Long userId);

    /**
     * 保存浏览记录
     */
    void browse(Long userId, Long travelId);
}
