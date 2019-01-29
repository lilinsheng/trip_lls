package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Sign;
import cn.wolfcode.trip.base.service.ISignService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("signs")
public class SignController {

    @Autowired
    private ISignService signService;

    //查询用户连续签到的次数
    @GetMapping("{userId}")
    public List<Sign> getSign(@PathVariable("userId") Long id){
        return signService.selectSignByUserId(id);
    }
    //查询用户当前签到的状态
    @GetMapping("/state/{id}")
    public int getState(@PathVariable Long id){
        return signService.selectUserState(id);
    }

    //保存签到信息
    @PostMapping
    public JsonResult save(Sign sign){
        JsonResult result = new JsonResult();
        try {
            signService.save(sign);
        } catch (Exception e) {
            result.mark(e.getMessage());
        }
        return result;
    }



}
