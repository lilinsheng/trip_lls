package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.NewsPage;
import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.UserQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    void deleteByPrimaryKey(Long id);

    void insert(User user);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    void updateByPrimaryKey(User user);

    User selectByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    List<User> selectForList(UserQueryObject qo);

    Object selectFabulous(@Param("id") Long id,@Param("travelId") Long travelId);

    Object selectStrategyFabulous(@Param("userId") Long userId, @Param("strategyId") Long strategyId);

    void updateAddUserAttention(@Param("focusId") Long focusId);

    void insertUserAttentionRelation(@Param("userId") Long userId, @Param("focusId") Long focusId);


    Integer selectFansByUserId(Long focusId);

    Object selectIsAttentionUser(@Param("userId") Long userId, @Param("focusId") Long focusId);

    void updateReductUserAttention(Long focusId);

    void deleteUserAttentionRelation(@Param("userId") Long userId, @Param("focusId") Long focusId);

    Object selectIsCollectTravel(@Param("userId") Long userId, @Param("travelId") Long travelId);

    Object selectIsCollectStrategy(@Param("userId") Long userId, @Param("strategyId") Long strategyId);

    /**
     * 查所有收藏攻略
     * @param id
     * @return
     */
    List getAllStravel(@Param("id") Long id);

    /**
     * 查所有收藏游记
     * @param id
     * @return
     */
    List<Travel> getAllTravel(Long id);

    /**
     * 查所有收藏日报
     * @param id
     * @return
     */
    List<NewsPage> getAllNewsPage(Long id);

    // 实时聊天
    List<Map<String,Object>> listAllForChat();

    List<User> selectInsteres(Long userId);


}