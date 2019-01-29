package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Mall;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IMallService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.QiniuUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *大攻略
 */
@Controller
@RequestMapping("/mall")
public class MallController {
    @Autowired
    private IMallService mallService;
    

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model){
        qo.setPageSize(5);
        PageInfo pageInfo = mallService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "/mall/list";
    }

    /**
     * 攻略添加
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Mall mall, MultipartFile file){
        if (file!=null){
            String upload = QiniuUtil.QI_PATH+QiniuUtil.qiniuyunImage(file);
            mall.setImageUrl(upload);
        }
        mallService.saveOrUpdate(mall);
        return new JsonResult();
    }
}
