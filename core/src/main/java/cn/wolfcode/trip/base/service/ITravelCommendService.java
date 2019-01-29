package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import com.github.pagehelper.PageInfo;

public interface ITravelCommendService {
    PageInfo query(TravelCommendQueryObject qo);

    /**
     * 新增或修改推荐游记
     * @param travelCommend
     */
    void saveOuUpdate(TravelCommend travelCommend);

    /**
     * 根据推荐类型查询推荐的游记
     * @return
     */
    PageInfo listCommendsByType(TravelCommendQueryObject qo);

    /**
     * 查看通知后,把状态改为已查看
     * @param id
     */
    void changeState(Long id);
}
