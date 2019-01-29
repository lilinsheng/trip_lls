package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.NewsPage;
import cn.wolfcode.trip.base.domain.NewsPageContent;
import cn.wolfcode.trip.base.mapper.NewsPageMapper;
import cn.wolfcode.trip.base.query.NewPagesQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.INewsPageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsPageServiceImpl implements INewsPageService {

    @Autowired
    private NewsPageMapper newsPageMapper;

    @Override
    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(),qo.getOrderBy());
        List<NewsPage> list = newsPageMapper.query(qo);
        return new PageInfo(list);
    }

    @Override
    public void saveOrUpdate(NewsPage newsPage) {
        if(newsPage.getId()!=null){
            newsPageMapper.updateByPrimaryKey(newsPage);
            NewsPageContent content = newsPage.getContent();
            content.setId(newsPage.getId());
            newsPageMapper.updatePrimaryById(newsPage.getContent());
        }else{
            newsPageMapper.insert(newsPage);
            NewsPageContent content = newsPage.getContent();
            content.setId(newsPage.getId());
            newsPageMapper.insertadd(newsPage.getContent());
        }
    }

    @Override
    public NewsPage NewsPageContent(Long id) {
        return newsPageMapper.selectNewsPageContent(id);
    }

    @Override
    public NewsPage getNewsPagesById(Long id) {
        NewsPage newsPage=newsPageMapper.selectNewsPageContent(id);
        return newsPage;
    }

    @Override
    public PageInfo getNewsPageCounts(NewPagesQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<NewsPage> list = newsPageMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
