package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ITravelService {

    /**
     * 查询我的游记
     * @param qo
     * @return
     */
    PageInfo queryTravel(TravelQueryObject qo);

    void saveOrUpdate(Travel travel);

    Travel getTravelById(Long id);

    /**
     * 更新游记状态
     * @param id
     * @param state
     */
    void updateState(Long id, Integer state);

    /**
     * 根据id获取游记内容
     * @param id
     * @return
     */
    TravelContent getTravelContentById(Long id);

    /**
     * 游记点赞
     * @param id
     */
    void fabulous(Long id);

    /**
     * 根据游记id查询点赞数
     * @param id
     * @return
     */
    int getFabulousByTravel(Long id);

    /**
     * 取消点赞
     * @param id
     */
    void reductFabulous(Long id);

    /**
     * 当前用户游记收藏
     * @param travelId
     */
    void collectTratel(Long travelId);

    /**
     * 获取游记的关注数量
     * @param travelId
     * @return
     */
    Integer getTravelCollection(Long travelId);

    /**
     * 取消游记收藏
     * @param travelId
     */
    void disCollectTratel(Long travelId);


    /**
     * 查全部游记
     * @param qo
     * @return
     */
    PageInfo getTraversCounts(TravelQueryObject qo);

    /**
     * 通知查看后,状态改为已查看
     * @param id
     */
    void changeState(Long id);
    /**
     * 查全部游记,不分页
     * @return
     */
    List<Travel> selectAlltravel();

    /**
     * 分页查询浏览记录
     */
    List<Travel> queryTravelReord(Long userId, QueryObject qo);
}
