package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Order;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.OrderMapper;
import cn.wolfcode.trip.base.service.IOrderService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void saveOrder(Order order) {
        User user = UserContext.getUser();
        order.setUser(user);
        order.setOrderNum(String.valueOf(System.currentTimeMillis())+user.getId());
        orderMapper.insert(order);
    }

    @Override
    public List getOrders() {
        User user = UserContext.getUser();
        return orderMapper.selectById(user.getId());
    }
}
