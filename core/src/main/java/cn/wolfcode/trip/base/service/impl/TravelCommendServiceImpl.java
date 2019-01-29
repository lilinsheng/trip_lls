package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.mapper.TravelCommendMapper;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TravelCommendServiceImpl implements ITravelCommendService {
    @Autowired
    private TravelCommendMapper travelCommendMapper;

    @Override
    public PageInfo query(TravelCommendQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<TravelCommend> list = travelCommendMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public void saveOuUpdate(TravelCommend travelCommend) {
        if (travelCommend.getId()!=null){
            travelCommendMapper.updateByPrimaryKey(travelCommend);
        }else {
            travelCommendMapper.insert(travelCommend);
        }
    }

    @Override
    public PageInfo listCommendsByType(TravelCommendQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<Map> list = travelCommendMapper.selectCommendsByType(qo);
        return new PageInfo(list);
    }

    /**
     * 查看通知后,把状态改为已查看
     * @param id
     */
    @Override
    public void changeState(Long id) {
        travelCommendMapper.updateState(id);
    }
}
