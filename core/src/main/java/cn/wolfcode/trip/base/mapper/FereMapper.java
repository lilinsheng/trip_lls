package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Fere;
import cn.wolfcode.trip.base.query.QueryObject;
import java.util.List;

public interface FereMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fere record);

    Fere selectByPrimaryKey(Long id);

    List<Fere> selectAll();

    int updateByPrimaryKey(Fere record);

    /**
     * 查询全部结伴信息
     * @param qo
     * @return
     */
    List<Fere> selectForAll(QueryObject qo);
}