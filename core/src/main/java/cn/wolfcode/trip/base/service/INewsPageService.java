package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.NewsPage;
import cn.wolfcode.trip.base.domain.NewsPageContent;
import cn.wolfcode.trip.base.query.NewPagesQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface INewsPageService {

    PageInfo query(QueryObject qo);

    void saveOrUpdate(NewsPage newsPage);

    NewsPage NewsPageContent(Long id);

    NewsPage getNewsPagesById(Long id);

    /**
     * 查日报总条数
     * @param qo
     * @return
     */
    PageInfo getNewsPageCounts(NewPagesQueryObject qo);
}
