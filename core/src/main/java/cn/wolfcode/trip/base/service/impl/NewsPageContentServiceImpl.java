package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.NewsPageContent;
import cn.wolfcode.trip.base.mapper.NewsPageContentMapper;
import cn.wolfcode.trip.base.service.INewsPageContentService;
import cn.wolfcode.trip.base.service.INewsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsPageContentServiceImpl implements INewsPageContentService{

    @Autowired
    private NewsPageContentMapper newsPageContentMapper;

}
