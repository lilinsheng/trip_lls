package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.query.StrategyCommentsQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCommentService;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    //注入游记
    @Autowired
    private ITravelService travelService;

    //注入点评
    @Autowired
    private IStrategyCommentService strategyCommentService;

    /**
     * 用户注册
     * @param nickName
     * @param email
     * @param password
     * @return
     */
    @PostMapping
    public JsonResult register(String nickName,String email,String password){
        JsonResult jsonResult = new JsonResult();
        try {
            userService.register(nickName,email,password);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/{id}")
    public JsonResult update(User user){
        JsonResult jsonResult = new JsonResult();
        userService.update(user);
        jsonResult.setResult(user);
        return jsonResult;
    }

    /**
     * 我的游记
     */
    @GetMapping("/{authorId}/travels")
    public PageInfo queryTravel(TravelQueryObject qo){
        qo.setOrderBy("lastUpdateTime desc");
        PageInfo pageInfo = travelService.queryTravel(qo);
        return pageInfo;
    }

    /**
     * 我的点评
     */
    @GetMapping("/{StrategyCommentId}/strategyComments")
    public PageInfo queryStrategyComments(StrategyCommentQueryObject qo){
        PageInfo pageInfo = strategyCommentService.queryStrategyComments(qo);
        return pageInfo;
    }

    /**
     * 根据id获取用户信息
     */
    @GetMapping
    public User getUserById(Long id){
        User user = userService.getUserById(id);
        return user;
    }

    /**
     * 查看当前用户是否点赞了某篇游记
     */
    @GetMapping("/fabulous/{travelId}")
    public JsonResult getFabulousState(@PathVariable Long travelId){
        Object obj = userService.getFabulousState(travelId);
        JsonResult jsonResult = new JsonResult();
        if (obj!=null){
           jsonResult.setResult(true);//点了赞
        }else {
            jsonResult.setResult(false);//没点赞
        }
        return jsonResult;
    }

    /**
     * 查看当前用户是否点赞某篇攻略
     */
    @GetMapping("/fabulousStrategy/{strategyId}")
    public JsonResult getStrategyFabulous(@PathVariable Long strategyId){
        Object obj = userService.getStrategyFabulous(strategyId);
        JsonResult jsonResult = new JsonResult();
        if (obj!=null){
            jsonResult.setResult(true);//点了赞
        }else {
            jsonResult.setResult(false);//没点赞
        }
        return jsonResult;
    }

    /**
     * 用户的关注
     */
    @PostMapping("/attention/{focusId}")
    public JsonResult attentionUser(@PathVariable Long focusId){
        //好友关注关系维护
        userService.addUserAttiention(focusId);
        //获取粉丝数
        int number = userService.getFansNum(focusId);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }
    /**
     * 取消用户的关注
     */
    @DeleteMapping("/disAttention/{focusId}")
    public JsonResult disAttentionUser(@PathVariable Long focusId){
        //好友关注关系维护,取消关注
        userService.disUserAttiention(focusId);
        //获取粉丝数
        int number = userService.getFansNum(focusId);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(number);
        return jsonResult;
    }

    /**
     * 查看当前用户是否关注该用户
     */
    @GetMapping("/isAttention/{focusId}")
    public JsonResult isAttentionUser(@PathVariable Long focusId){
        //判断好友是否关注
        Object obj = userService.isAttentionUser(focusId);
        JsonResult jsonResult = new JsonResult();
        if (obj!=null){
            jsonResult.setResult(true);
        }else {
            jsonResult.setResult(false);
        }
        return jsonResult;
    }

    /**
     * 查看当前用户是否收藏了游记
     */
    @GetMapping("/collectionTravel/{travelId}")
    public JsonResult isCollectTravel(@PathVariable Long travelId){
        Object obj = userService.isCollectTravel(travelId);
        JsonResult jsonResult = new JsonResult();
        if (obj!=null){
            jsonResult.setResult(true);
        }else {
            jsonResult.setResult(false);
        }
        return jsonResult;
    }

    /**
     * 查看当前用户是否收藏了攻略
     */
    @GetMapping("/collectStrategy/{strategyId}")
    public JsonResult isCollectStrategy(@PathVariable Long strategyId){
        Object obj = userService.isCollectStrategy(strategyId);
        JsonResult jsonResult = new JsonResult();
        if (obj!=null){
            jsonResult.setResult(true);
        }else {
            jsonResult.setResult(false);
        }
        return jsonResult;
    }

    @GetMapping("/{email}/ByEmail")
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
}
