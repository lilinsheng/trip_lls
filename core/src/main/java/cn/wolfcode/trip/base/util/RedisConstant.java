package cn.wolfcode.trip.base.util;

public class RedisConstant {

    /**
     * redis的签到key
     */
    public static final String daily = "dailySign:{0}";

    /**
     * 每月签到总数key
     */
    public static final String monthSighCount = "monthSighCount:{0}";

    /**
     * 游记浏览记录key
     * 占位符：用户id
     */
    public static final String TRAVEL_BROWSE = "travel_browse_userId:{0}";
}
