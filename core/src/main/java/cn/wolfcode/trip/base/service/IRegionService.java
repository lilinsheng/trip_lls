package cn.wolfcode.trip.base.service;


import cn.wolfcode.trip.base.domain.Region;

import java.util.List;

public interface IRegionService {
    List<Region> listByParentId(Long parentId);

    void update(Region region);

    void save(Region region);

    void updateStatus(Region region);

    /**
     * 获取所有的地区
     * @return
     */
    List<Region> listAll();

    /**
     * 根据状态查询地区
     * @param state
     * @return
     */
    List<Region> getRegionsByState(Integer state);
}
