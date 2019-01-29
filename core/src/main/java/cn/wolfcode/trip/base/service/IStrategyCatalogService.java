package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.StrategyCatalogQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyCatalogService {
    /**
     * 分页查询攻略分类
     * @param qo
     * @return
     */
    PageInfo query(StrategyCatalogQueryObject qo);

    /**
     * 获取最大的序号
     * @param id
     * @return
     */
    int getMaxSequence(Long id);

    /**
     * 保存或更新
     * @param strategyCatalog
     */
    void saveOrUpdate(StrategyCatalog strategyCatalog);

    /**
     * 根据大攻略id查询所有攻略分类
     * @param strategyId
     * @return
     */
    List<StrategyCatalog> selectCatalogByStrategyId(Long strategyId);
}
