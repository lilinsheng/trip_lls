package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.NewsPage;
import cn.wolfcode.trip.base.domain.NewsPageContent;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.INewsPageService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.QiniuUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/newsPages")
public class NewsPageController {

    @Autowired
    private INewsPageService newsPageService;

    @RequestMapping("/list")
    public String getQuery(@ModelAttribute("qo")QueryObject qo, Model model){
        model.addAttribute(newsPageService.query(qo));
        return "newspage/list";
    }

    @RequestMapping("/input")
    @ResponseBody
    public NewsPage getNewsPagesById(Long id){
        NewsPage  newspage= newsPageService.getNewsPagesById(id);
        return newspage;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult getSaveOrUpdate(NewsPage newsPage, MultipartFile file){
        if (file!=null){
            String url = QiniuUtil.QI_PATH+QiniuUtil.qiniuyunImage(file);
            newsPage.setCoverUrl(url);
        }
        newsPageService.saveOrUpdate(newsPage);
        return new JsonResult();
    }
}