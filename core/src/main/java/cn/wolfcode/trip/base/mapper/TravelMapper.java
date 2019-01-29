package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TravelMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Travel travel);

    Travel selectByPrimaryKey(Long id);

    List<Travel> selectAll();

    void updateByPrimaryKey(Travel travel);

    /**
     * 分页查询游记
     * @param qo
     * @return
     */
    List<Travel> selectTravel(TravelQueryObject qo);

    void updateState(@Param("id") Long id, @Param("state") Integer state, @Param("releaseTime") Date date, @Param("inform") Integer inform);

    void updateFabulousNumber(Long id);

    void saveFabulous(@Param("userId") Long userId, @Param("travelId") Long travelId);

    int selectFabulousByTravel(Long id);

    void updateReductFabulous(Long id);

    void deleteFabulous(@Param("userId") Long userId, @Param("travelId") Long travelId);

    void updateAddCollectTravel(Long travelId);

    void insertCollectionRelation(@Param("userId") Long userId, @Param("travelId") Long travelId);

    Integer selectTravelCollection(Long travelId);

    void updateReductCollectTravel(Long travelId);

    void deleteCollectionRelation(@Param("userId") Long userId, @Param("travelId") Long travelId);

    /**
     * 查游记
     * @param qo
     * @return
     */
    List<Travel> getTraversCounts(TravelQueryObject qo);

    /**
     * 通知查看后,状态改为已查看
     * @param id
     */
    void updateInform(Long id);


    /**
     * 查全部游记不分页
     * @return
     */
    List  selectAlltravel();

}