package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.mapper.RegionMapper;
import cn.wolfcode.trip.base.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> listByParentId(Long parentId) {
        return regionMapper.selectByParentId(parentId);
    }

    @Override
    public void update(Region region) {
        regionMapper.updateByPrimaryKey(region);
    }

    @Override
    public void save(Region region) {
        regionMapper.insert(region);
    }

    @Override
    public void updateStatus(Region region) {
        regionMapper.updateStatus(region);
    }

    @Override
    public List<Region> listAll() {
        return regionMapper.selectAll();
    }

    @Override
    public List<Region> getRegionsByState(Integer state) {
        return regionMapper.selectRegionsByState(state);
    }
}
