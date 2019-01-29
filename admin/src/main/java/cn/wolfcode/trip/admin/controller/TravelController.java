package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/travel")
public class TravelController {
    @Autowired
    private ITravelService travelService;

    /**
     * 已发布游记
     */
    @RequestMapping("/releaseList")
    public String releaseList(@ModelAttribute("qo") TravelQueryObject qo, Model model){
        qo.setOrderBy("releaseTime asc");//按发布时间排序
        qo.setState(Travel.STATE_RELEASE);
        qo.setIsPublic(true);//公开
        PageInfo pageInfo = travelService.queryTravel(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "travel/releaseList";
    }

    /**
     *待审核游记
     */
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") TravelQueryObject qo, Model model){
        qo.setOrderBy("releaseTime asc");//按发布时间排序
        if (qo.getState()==null){
            qo.setState(Travel.STATE_DEFAULT);//待审核状态
        }
        qo.setIsPublic(true);//公开
        PageInfo pageInfo = travelService.queryTravel(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "travel/defaultList";
    }

    //更新游记状态
    @RequestMapping("/updateState")
    @ResponseBody
    public JsonResult updateState(Long id, Integer state){
        travelService.updateState(id,state);
        return new JsonResult();
    }

    /**
     * 获取游记内容
     */
    @RequestMapping("/getTravelContent")
    @ResponseBody
    public TravelContent getTravelContent(Long id){
        return travelService.getTravelContentById(id);
    }
}
