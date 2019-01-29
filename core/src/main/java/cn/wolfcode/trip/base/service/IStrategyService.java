package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.query.TagQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyService {
    /**
     * 分页查询
     * @param qo
     * @return
     */
    PageInfo query(StrategyQueryObject qo);

    /**
     * 攻略添加或更新
     * @param strategy
     */
    void saveOrUpdate(Strategy strategy);

    /**
     * 根据状态查询大攻略
     * @param params
     * @return
     */
    List<Strategy> listStrategyByState(Integer...params);

    /**
     * 根据地区id查询攻略
     * @param qo
     * @return
     */
    List<Strategy> listStrategyByRegionId(StrategyQueryObject qo);

    /**
     * 根据地区id分页查询攻略
     * @param qo
     * @return
     */
    PageInfo listStrategyByRegion(StrategyQueryObject qo);

    /**
     * 根据id查询大攻略
     * @param id
     * @return
     */
    Strategy getByStrategyId(Long id);

    /**
     * 根据攻略id查询该攻略下的热门标签
     * @return
     */
    PageInfo getTagsById(TagQueryObject qo);

    /**
     * 攻略点赞
     * @param id
     */
    void fabulous(Long id);

    /**
     * 根据攻略id查询攻略点赞数
     * @param id
     * @return
     */
    int getFabulousByStrategy(Long id);

    /**
     * 攻取消略点赞
     * @param id
     */
    void disFabulous(Long id);

    /**
     * 获取全部大攻略
     * @param qo
     * @return
     */
    PageInfo getStrategiesCounts(StrategyQueryObject qo);

    /**
     * 收藏攻略
     * @param strategyId
     */
    void collectStrategy(Long strategyId);

    /**
     * 获取攻略的收藏数
     * @param strategyId
     * @return
     */
    Integer getCollectStrategyNum(Long strategyId);

    /**
     * 取消攻略收藏
     * @param strategyId
     */
    void disCollectStrategy(Long strategyId);

    /**
     * 查询全部攻略,并排序
     * @param qo
     */
    List<Strategy> selectAllStrategy(StrategyDetailQueryObject qo);
}
