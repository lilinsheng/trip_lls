package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.FereContent;
import cn.wolfcode.trip.base.mapper.FereContentMapper;
import cn.wolfcode.trip.base.query.FereContentQueryObject;
import cn.wolfcode.trip.base.service.IFereContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FereContentServiceImpl implements IFereContentService {

    @Autowired
    private FereContentMapper fereContentMapper;

    @Override
    public PageInfo selectListAllFereContent(FereContentQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
       List<FereContent> list =  fereContentMapper.selectForAll(qo);
        return new PageInfo(list);
    }
}
