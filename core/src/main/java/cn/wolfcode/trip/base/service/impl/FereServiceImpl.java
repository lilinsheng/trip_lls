package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Fere;
import cn.wolfcode.trip.base.mapper.FereMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IFereService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FereServiceImpl implements IFereService {

    @Autowired
    private FereMapper fereMapper;

    //分页
    @Override
    public PageInfo selectFeres(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Fere> list = fereMapper.selectForAll(qo);
        return new PageInfo(list);
    }
}
