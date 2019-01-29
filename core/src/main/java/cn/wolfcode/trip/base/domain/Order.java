package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 门票订单
 */
@Setter
@Getter
public class Order extends BaseDomain{

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Scenic ticket;

    private User user;

    private String touristName;

    private String address;

    private String phone;

    private String orderNum;
}