package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Sign;

import java.util.List;

public interface ISignService {

    /**
     * 添加操作
     *
     * @param sign 需要添加的签到信息
     */
    void save(Sign sign);

    /**
     * 获取签到信息
     *
     * @param id 用户的id
     * @return 当前用户的签到集合
     */
    List<Sign> selectSignByUserId(Long id);

    /**
     * 查询用户签到状态
     *
     * @param id 用户id
     * @return 签到状态
     */
    int selectUserState(Long id);

}
