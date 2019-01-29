package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.NewsPage;
import cn.wolfcode.trip.base.domain.NewsPageContent;
import cn.wolfcode.trip.base.query.NewPagesQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import java.util.List;

public interface NewsPageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsPage record);

    NewsPage selectByPrimaryKey(Long id);

    List<NewsPage> selectAll();

    int updateByPrimaryKey(NewsPage record);

    List<NewsPage> query(QueryObject qo);

    int insertadd(NewsPageContent content);

    NewsPage selectNewsPageContent(Long id);

    int updatePrimaryById(NewsPageContent content);

    /**
     * 查日报总条数
     * @param qo
     */
    List<NewsPage> selectForList(NewPagesQueryObject qo);
}