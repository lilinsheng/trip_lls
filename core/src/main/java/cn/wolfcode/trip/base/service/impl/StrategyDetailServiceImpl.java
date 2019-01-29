package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.mapper.StrategyContentMapper;
import cn.wolfcode.trip.base.mapper.StrategyDetailMapper;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StrategyDetailServiceImpl implements IStrategyDetailService {
    @Autowired
    private StrategyDetailMapper strategyDetailMapper;
    @Autowired
    private StrategyContentMapper strategyContentMapper;

    @Override
    public PageInfo query(StrategyDetailQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<StrategyDetail> list = strategyDetailMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public void saveOrUpdate(StrategyDetail strategyDetail) {
        //如果是发布，则设置发布时间
        if (strategyDetail.getState()==StrategyDetail.STATE_RELEASE){
            strategyDetail.setReleaseTime(new Date());
        }

        StrategyContent content = strategyDetail.getContent();

        //如果没传入序号，则设置最大序号
        if (strategyDetail.getSequence()==null){
            //获取攻略分类最大的序号
            int max = strategyDetailMapper.getMaxSequence();
            strategyDetail.setSequence(max+1);
        }

        if (strategyDetail.getId()!=null){
            strategyDetailMapper.updateByPrimaryKey(strategyDetail);
            content.setId(strategyDetail.getId());
            //更新文章内容
            strategyContentMapper.updateByPrimaryKey(content);
        }else {
            strategyDetailMapper.insert(strategyDetail);
            content.setId(strategyDetail.getId());
            //保存文章内容
            strategyContentMapper.insert(content);
        }


    }

    @Override
    public StrategyContent getContentById(Long id) {
        return strategyContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public StrategyDetail getStrategyContentById(Long id) {
        return strategyDetailMapper.selectStrategyContentById(id);
    }
}
