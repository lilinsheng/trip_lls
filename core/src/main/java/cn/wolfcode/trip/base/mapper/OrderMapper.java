package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Order;
import java.util.List;

public interface OrderMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Order order);

    Order selectByPrimaryKey(Long id);

    List<Order> selectAll();

    void updateByPrimaryKey(Order order);

    List<Order> selectById(Long userId);
}