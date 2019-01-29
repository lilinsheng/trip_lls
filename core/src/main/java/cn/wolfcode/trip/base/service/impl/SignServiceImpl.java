package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Sign;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.SignMapper;
import cn.wolfcode.trip.base.service.ISignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SignServiceImpl implements ISignService {
    @Autowired
    SignMapper signMapper;

    public void save(Sign sign) {
        int state = signMapper.signByUserId(sign.getUser().getId());
        //0 表示该用户当天没有签到
        if (state == 0) {
            //保存签到数据
            sign.setSignTime(new Date());
            //进行签到次数的添加
            List<Sign> signCount = signMapper.selectAllUserSign(sign.getUser().getId());
            if (signCount.size() > 0) {
                if (signCount.get(0).getSignCount() == 0) {
                    sign.setSignCount(1);
                } else {
                    sign.setSignCount(signCount.get(0).getSignCount() + 1);
                }
                sign.setCount(signCount.get(0).getCount() + 1);
            } else if (signCount.size() == 0) {
                sign.setSignCount(1);
                sign.setCount(1);
            }
            //在进行签到保存后进行积分的添加与修改
            User user = signMapper.getByUserId(sign.getUser().getId());

            if (user.getScore() == null) {
                //进行积分的新增
                user.setId(sign.getUser().getId());
                user.setScore(Sign.SIGN_IN);
                signMapper.updateByUserId(user);
                signMapper.insert(sign);
            } else {
                //增加用户积分
                user.setId(sign.getUser().getId());
                user.setScore(Sign.SIGN_IN);
                signMapper.updateByUserId(user);
                signMapper.insert(sign);
            }
            //满足三次签到
            if (sign.getSignCount() % 3 >= 1) {
               user.setScore(Sign.SIGN_THIRD);
                signMapper.updateByUserId(user);
                signMapper.insert(sign);
            }
            signMapper.insert(sign);
        } else {
            throw new RuntimeException("今天已经签到了");
        }

    }

    //查询用户签到数据
    public List<Sign> selectSignByUserId(Long id) {
        //用户今天签到状态
        int state = signMapper.signByUserId(id);
        //用户昨天签到时间
        int last = signMapper.selectByUserId(id);

        if (state == 0) {
            if (last == 0) {
                //根据用户id获取对应的签到信息
                List<Sign> signCount = signMapper.selectAllUserSign(id);
                System.out.println(signCount);
                //判断是否有签到
                if (signCount.size() > 0) {
                    if (signCount.get(0).getSignCount() != 0) {
                        Sign sign = new Sign();
                        User user = signMapper.getByUserId(id);
                        sign.setUser(user);
                        sign.setSignCount(0);
                        signMapper.updateByPrimaryKey(sign);
                    }
                }
            }
        }
        return signMapper.selectAllUserSign(id);
    }

    //查询今天是否签到
    @Override
    public int selectUserState(Long id) {
        return signMapper.signByUserId(id);
    }
}
