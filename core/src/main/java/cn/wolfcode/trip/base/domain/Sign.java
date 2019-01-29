package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.BAD_CONTEXT;
import org.omg.CORBA.INTERNAL;

import java.util.Date;

@Setter
@Getter
public class Sign extends BaseDomain{
        public static final Long SIGN_IN =10L;//签到加10积分
    public static final Long SIGN_THIRD =20L;//连续签到3次加20积分

    private User user;//用户

    private Date signTime;//签到时间

    private int signCount;//连续签到次数

    private int count;//当月累计签到次数

}