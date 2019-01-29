package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.mapper.InformMapper;
import cn.wolfcode.trip.base.service.IInformService;
import cn.wolfcode.trip.base.util.UserContext;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InformServiceImpl implements IInformService {
    @Autowired
    private InformMapper informMapper;
    // 查询所有通知
    @Override
    public List<Map> listAll() {
        Long userId = UserContext.getUser().getId();
        return informMapper.selectAll(userId);
    }
    // 查询未被查看过的通知数
    @Override
    public int getUnreadNumber() {
        Long userId = UserContext.getUser().getId();
        int travelNum = informMapper.selectCountTravelNum(userId);
        int travelCommendNum = informMapper.selectCountTravelCommendNum(userId);
        return travelNum + travelCommendNum;
    }
}
