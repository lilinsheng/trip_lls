package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Scenic;
import cn.wolfcode.trip.base.mapper.ScenicMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IScenicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicServiceImpl implements IScenicService {

    @Autowired
    private ScenicMapper scenicMapper;

    @Override
    public PageInfo query(QueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<Scenic> list = scenicMapper.selectScenic(qo);
        return new PageInfo(list);
    }

    @Override
    public void saveOrUpdate(Scenic scenic) {
        if (scenic.getId() != null) {
            scenicMapper.updateByPrimaryKey(scenic);
        } else {
            scenicMapper.insert(scenic);
        }
    }

    @Override
    public Scenic getTravelById(Integer id) {
        return scenicMapper.selectByPrimaryKey(id);
    }
}
