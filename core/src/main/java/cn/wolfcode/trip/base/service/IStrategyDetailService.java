package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import com.github.pagehelper.PageInfo;

public interface IStrategyDetailService {
    /**
     * 分页查询攻略详情
     * @param qo
     * @return
     */
    PageInfo query(StrategyDetailQueryObject qo);

    /**
     * 新增或更新攻略文章
     * @param strategyDetail
     */
    void saveOrUpdate(StrategyDetail strategyDetail);

    /**
     * 根据id查询文章内容
     * @return
     */
    StrategyContent getContentById(Long id);

    /**
     * 获取攻略细节及内容
     * @param id
     * @return
     */
    StrategyDetail getStrategyContentById(Long id);
}
