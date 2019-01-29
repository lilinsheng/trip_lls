package cn.wolfcode.trip.base.util;

import cn.wolfcode.trip.base.domain.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public  class UserContext {
    public static void setUser(User user){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        requestAttributes.getRequest().getSession().setAttribute("USER_IN_SESSION",user);
    }

    public static User getUser(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (User) requestAttributes.getRequest().getSession().getAttribute("USER_IN_SESSION");
    }
}
