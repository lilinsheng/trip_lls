package cn.wolfcode.trip.app.controller;


import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IMallService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/malls")
public class MallController {


    @Autowired
    private IMallService mallService;
    @Autowired
    private IUserService userService;
    /**
     * 获取所有的商城商品
     */
    @GetMapping
    public PageInfo listMall(QueryObject qo){
        qo.setPageSize(0);
        PageInfo pageInfo = mallService.query(qo);
        return pageInfo;
    }

    /**
     * 更新用户积分
     * @param score
     * @param userId
     * @return
     */
    @PutMapping
    public JsonResult updateScore(Long score,Long userId){
        JsonResult jsonResult = new JsonResult();
        mallService.updateScore(score,userId);
        User user = userService.getUserById(userId);
        UserContext.setUser(user);
        jsonResult.setResult(user);
        return  jsonResult;
    }
}
