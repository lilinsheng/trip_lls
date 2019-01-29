package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StrategyMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Strategy strategy);

    Strategy selectByPrimaryKey(Long id);

    List<Strategy> selectAll();

    void updateByPrimaryKey(Strategy strategy);

    List<Strategy> selectForList(StrategyQueryObject qo);

    List<Strategy> selectStrategyByState(Integer[] params);

    List<Strategy> selectStrategyByRegionIdAndState(StrategyQueryObject qo);

    void updateFabulousNumber(Long id);

    void saveFabulous(@Param("userId") Long userId, @Param("strategyId") Long strategyId);

    Integer selectFabulousByStrategy(Long id);

    void updateReductFabulousNumber(Long id);

    void deleteFabulous(@Param("userId") Long userId, @Param("strategyId") Long strategyId);

    /**
     * 查询全部攻略
     * @param qo
     * @return
     */
    List<Strategy> getStrategiesCounts(StrategyQueryObject qo);

    void updateAddCollectStrategy(Long strategyId);

    void insertCollectStrategyRelation(@Param("userId") Long userId, @Param("strategyId") Long strategyId);

    Integer selectCollectStrategyNum(Long strategyId);

    void updateReductCollectStrategy(Long strategyId);

    void deleteCollectStrategyRelation(@Param("userId") Long userId, @Param("strategyId") Long strategyId);

    /**
     * 查询全部攻略,并排序
     * @param qo
     * @return
     */
    List<Strategy> selectAllStrategy(StrategyDetailQueryObject qo);

}