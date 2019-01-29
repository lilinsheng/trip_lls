package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Mall;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

/**
 * 商城service
 */
public interface IMallService  {

    /**
     * 商城商品分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 商城商品添加或者更新
     * @param mall
     */
    void saveOrUpdate(Mall mall);

    /**
     * 更新用户积分
     * @param score
     */
    void updateScore(Long score,Long id);
}
