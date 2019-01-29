package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private IUserService userService;

    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    @PostMapping
    public JsonResult login(String email,String password){
        JsonResult jsonResult = new JsonResult();
        try {
            User user = userService.login(email,password);
            UserContext.setUser(user);
            //121212
            jsonResult.setResult(user);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark(e.getMessage());
        }
        return jsonResult;
    }

    @DeleteMapping
    public void logout(){
        UserContext.setUser(null);
    }
}
