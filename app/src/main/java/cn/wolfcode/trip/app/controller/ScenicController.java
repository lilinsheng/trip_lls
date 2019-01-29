package cn.wolfcode.trip.app.controller;


import cn.wolfcode.trip.base.domain.Scenic;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IScenicService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scenics")
public class ScenicController {


    @Autowired
    private IScenicService scenicService;

    /**
     * 获取所有的门票
     */
    @GetMapping
    public PageInfo listScenic(QueryObject qo) {
        qo.setPageSize(0);
        PageInfo pageInfo = scenicService.query(qo);
        return pageInfo;
    }


    /**
     * 根据id获取门票
     */
    @GetMapping("/{id}")
    public Scenic getTravelById(@PathVariable Integer id) {
        return scenicService.getTravelById(id);
    }

    /**
     * 提交订单
     */
}
