package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.QiniuUtil;
import cn.wolfcode.trip.base.util.UploadUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 游记推荐
 */
@Controller
@RequestMapping("/travelCommend")
public class TravelCommendController {
    @Autowired
    private ITravelCommendService travelCommendService;
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") TravelCommendQueryObject qo, Model model){
        PageInfo pageInfo = travelCommendService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "travelCommend/list";
    }

    /**
     * 游记推荐
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(TravelCommend travelCommend, MultipartFile file){
        if (file!=null){
            String path = QiniuUtil.QI_PATH+QiniuUtil.qiniuyunImage(file);
            travelCommend.setCoverUrl(path);
        }
        travelCommendService.saveOuUpdate(travelCommend);
        return new JsonResult();
    }

}
