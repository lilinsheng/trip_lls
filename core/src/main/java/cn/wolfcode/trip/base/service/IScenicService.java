package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Scenic;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

/**
 * 商城service
 */
public interface IScenicService {

    /**
     * 景区门票分页
     *
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 景区门票添加或者更新
     *
     * @param scenic
     */
    void saveOrUpdate(Scenic scenic);


    /**
     * 根据id获取门票
     */
    Scenic getTravelById(Integer id);
}
