package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.TagQueryObject;

import java.util.List;

public interface TagMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Tag tag);

    Tag selectByPrimaryKey(Long id);

    List<Tag> selectAll();

    void updateByPrimaryKey(Tag tag);

    List<Tag> selectTagsByStrategyId(TagQueryObject qo);
}