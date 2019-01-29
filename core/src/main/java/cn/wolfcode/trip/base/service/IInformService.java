package cn.wolfcode.trip.base.service;

import java.util.List;
import java.util.Map;

public interface IInformService {
    // 查询所有通知
    List<Map> listAll();
    // 查询未被查看过的通知数
    int getUnreadNumber();
}
