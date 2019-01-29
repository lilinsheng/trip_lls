package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Scenic;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IScenicService;
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
 * 全部景区门票
 */
@Controller
@RequestMapping("/scenic")
public class ScenicController {
    @Autowired
    private IScenicService scenicService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) {
        qo.setPageSize(5);
        PageInfo pageInfo = scenicService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "scenic/list";
    }

    /**
     * 景区门票添加或更新
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Scenic scenic, MultipartFile file) {
        if (file != null) {
            String upload = QiniuUtil.QI_PATH + QiniuUtil.qiniuyunImage(file);
            scenic.setImgUrl(upload);
        }
        scenicService.saveOrUpdate(scenic);
        return new JsonResult();
    }
}
