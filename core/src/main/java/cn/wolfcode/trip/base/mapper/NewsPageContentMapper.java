package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.NewsPageContent;
import java.util.List;

public interface NewsPageContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsPageContent record);

    NewsPageContent selectByPrimaryKey(Long id);

    List<NewsPageContent> selectAll();

    int updateByPrimaryKey(NewsPageContent record);
}