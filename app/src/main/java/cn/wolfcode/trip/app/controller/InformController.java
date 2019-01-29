package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.service.IInformService;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.service.ITravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/informs")
public class InformController {
    @Autowired
    private IInformService informService;
    @Autowired
    private ITravelCommendService travelCommendService;
    @Autowired
    private ITravelService travelService;

    /**
     * 查询所有通知
     *
     * @return
     */
    @GetMapping
    public List<Map> listInform() {
        return informService.listAll();
    }

    /**
     * 查看通知后,把通知装填改为已查看
     */
    @PatchMapping
    public void changeState(Long id, Long inform) {
        if (inform == 2 || inform == 3) { // 攻略的
            travelCommendService.changeState(id);
        } else { // 游记的
            travelService.changeState(id);
        }
    }

    /**
     * 查询未被查看过的通知数
     * @return
     */
    @GetMapping("/unreadNumber")
    public int getUnreadNumber() {
        return informService.getUnreadNumber();
    }
}
