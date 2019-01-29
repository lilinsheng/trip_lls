package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interes")
public class InteresController {
    @Autowired
    private IUserService userService;

    @GetMapping("/{userId}")
    public List<User> getAllInteres(@PathVariable Long userId){
        List<User> list = userService.getAllInsteres(userId);
        return list;
    }
}
