package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IFabulousService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fabulous")
public class FabulousController {
    @Autowired
    private IFabulousService fabulousService;

    /**
     * 获取当前用户未被查看的点赞数
     * @return
     */
    @GetMapping("/fabulousNumber")
    public Map<String, Object> getUnreadFabulousNumber() {
        int number = fabulousService.getFabulousNumber();
        HashMap<String, Object> map = new HashMap<>();
        map.put("unreadFabulousNumber", number);
        return map;
    }

    /**
     * 获取点赞历史查询
     * @param qo
     * @return
     */
    @GetMapping("/fabulousHistory")
    public PageInfo getFabulousHistory(QueryObject qo) {
        PageInfo pageInfo = fabulousService.getFabulousHistory(qo);
        return pageInfo;
    }

    /**
     * 把未查看转换成已查看
     */
    @PatchMapping("/changeState")
    public void chageState(Long id,Integer type){
        fabulousService.changeState(id,type);
    }
}
