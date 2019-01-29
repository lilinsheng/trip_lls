
package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.NewsPage;
import cn.wolfcode.trip.base.domain.NewsPageContent;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.INewsPageContentService;
import cn.wolfcode.trip.base.service.INewsPageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newspage")
public class NewsPageController {

    @Autowired
    private INewsPageService newsPageService;

    @GetMapping
    public PageInfo getNewsPage(QueryObject qo){
        qo.setOrderBy("createTime desc");
        PageInfo pageInfo=newsPageService.query(qo);
        return pageInfo;
    }

    @GetMapping("/{id}")
    public NewsPage getNewsPageContent(@PathVariable Long id){
        NewsPage newsPageContent = newsPageService.NewsPageContent(id);
        return newsPageContent;
    }
}