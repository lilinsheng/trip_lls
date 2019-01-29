package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IOrderService {
    void saveOrder(Order order);

    List getOrders();
}
