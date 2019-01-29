package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Order;
import cn.wolfcode.trip.base.service.IOrderService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping
    public JsonResult order(Order order){
        orderService.saveOrder(order);
        return new JsonResult();
    }

    @GetMapping
    public List getOrders(){
        List list = orderService.getOrders();
        return list;
    }
}
