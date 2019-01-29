package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Region region);

    Region selectByPrimaryKey(Long id);

    List<Region> selectAll();

    void updateByPrimaryKey(Region region);

    List<Region> selectByParentId(@Param("parentId") Long parentId);

    void updateStatus(Region region);

    List<Region> selectRegionsByState(Integer state);
}