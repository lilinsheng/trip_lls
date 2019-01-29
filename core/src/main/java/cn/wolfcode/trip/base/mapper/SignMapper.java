package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Sign;
import cn.wolfcode.trip.base.domain.User;

import java.util.List;

public interface SignMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Sign record);

    Sign selectByPrimaryKey(Long id);

    List<Sign> selectAll();

    List<Sign> selectAllUserSign(Long id);

    int updateByPrimaryKey(Sign sign);

    //判断当天用户是否已经签到 返回0 表示没有签到
    int signByUserId(Long userId);

    //判断用户在当前时间的前一天是否已签到过
    int selectByUserId(Long userId);

    void updateByUserId(User user);

    User getByUserId(Long userId);
}