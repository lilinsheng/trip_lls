package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.StrategyMapper;
import cn.wolfcode.trip.base.mapper.TagMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.query.TagQueryObject;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyServiceImpl implements IStrategyService {


    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public PageInfo query(StrategyQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Strategy> list = strategyMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public void saveOrUpdate(Strategy strategy) {
        if (strategy.getId()!=null){
            strategyMapper.updateByPrimaryKey(strategy);
        }else {
            strategyMapper.insert(strategy);
        }
    }

    @Override
    public List<Strategy> listStrategyByState(Integer... params) {
        return strategyMapper.selectStrategyByState(params);
    }

    @Override
    public List<Strategy> listStrategyByRegionId(StrategyQueryObject qo) {
        return strategyMapper.selectStrategyByRegionIdAndState(qo);
    }

    @Override
    public PageInfo listStrategyByRegion(StrategyQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),true,false,null);
        List<Strategy> list = strategyMapper.selectStrategyByRegionIdAndState(qo);
        return new PageInfo(list);
    }

    @Override
    public Strategy getByStrategyId(Long id) {
        return strategyMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo getTagsById(TagQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List list = tagMapper.selectTagsByStrategyId(qo);
        return new PageInfo(list);
    }

    @Override
    public void fabulous(Long id) {
        User user = UserContext.getUser();
        strategyMapper.updateFabulousNumber(id);
        strategyMapper.saveFabulous(user.getId(),id);
    }

    @Override
    public int getFabulousByStrategy(Long id) {
        return strategyMapper.selectFabulousByStrategy(id);
    }

    @Override
    public void disFabulous(Long id) {
        User user = UserContext.getUser();
        strategyMapper.updateReductFabulousNumber(id);
        strategyMapper.deleteFabulous(user.getId(),id);
    }


    /**
     * 获取全部攻略
     * @param qo
     * @return
     */
    @Override
    public PageInfo getStrategiesCounts(StrategyQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(), qo.getOrderBy());
        List<Strategy> list = strategyMapper.getStrategiesCounts(qo);
        return new PageInfo(list);
    }

    @Override
    public void collectStrategy(Long strategyId) {
        User user = UserContext.getUser();
        //攻略收藏数增加
        strategyMapper.updateAddCollectStrategy(strategyId);
        //保存攻略收藏的关系
        strategyMapper.insertCollectStrategyRelation(user.getId(),strategyId);
    }

    @Override
    public Integer getCollectStrategyNum(Long strategyId) {
        return strategyMapper.selectCollectStrategyNum(strategyId);
    }

    @Override
    public void disCollectStrategy(Long strategyId) {
        User user = UserContext.getUser();
        //攻略收藏数减少
        strategyMapper.updateReductCollectStrategy(strategyId);
        //移除攻略收藏的关系
        strategyMapper.deleteCollectStrategyRelation(user.getId(),strategyId);
    }

    //查询全部攻略,并排序
    @Override
    public List<Strategy> selectAllStrategy(StrategyDetailQueryObject qo) {
        return strategyMapper.selectAllStrategy(qo);
    }

}
