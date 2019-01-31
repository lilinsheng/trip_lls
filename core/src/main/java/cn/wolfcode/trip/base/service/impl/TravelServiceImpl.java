package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.TravelContentMapper;
import cn.wolfcode.trip.base.mapper.TravelMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.RedisConstant;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TravelServiceImpl implements ITravelService {
    @Autowired
    private TravelMapper travelMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TravelContentMapper travelContentMapper;

    /**
     * 我的游记
     * @param qo
     * @return
     */
    @Override
    public PageInfo queryTravel(TravelQueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<Travel> list = travelMapper.selectTravel(qo);
        return new PageInfo(list);
    }

    @Override
    public void saveOrUpdate(Travel travel) {
        Date date = new Date();
        travel.setLastUpdateTime(date);
        if (travel.getId()!=null){
            travelMapper.updateByPrimaryKey(travel);

            //更新游记内容
            TravelContent travelContent = travel.getTravelContent();
            travelContent.setId(travel.getId());
            travelContentMapper.updateByPrimaryKey(travelContent);
        }else {
            travel.setCreateTime(date);//创建时间
            travel.setAuthor(UserContext.getUser());//作者
            travelMapper.insert(travel);

            //更新游记内容
            TravelContent travelContent = travel.getTravelContent();//游记内容
            travelContent.setId(travel.getId());
            travelContentMapper.insert(travelContent);
        }
    }

    @Override
    public Travel getTravelById(Long id) {
        return travelMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateState(Long id, Integer state) {
        Date date = null;
        if (state == Travel.STATE_RELEASE) {
            date = new Date();
        }
        Integer inform = state;
        travelMapper.updateState(id, state, date, inform);
    }

    @Override
    public TravelContent getTravelContentById(Long id) {
        return travelContentMapper.selectTravelContentById(id);
    }

    @Override
    public void fabulous(Long id) {
        User user = UserContext.getUser();
        travelMapper.updateFabulousNumber(id);
        travelMapper.saveFabulous(user.getId(),id);
    }

    @Override
    public int getFabulousByTravel(Long id) {
        return travelMapper.selectFabulousByTravel(id);
    }

    @Override
    public void reductFabulous(Long id) {
        User user = UserContext.getUser();
        travelMapper.updateReductFabulous(id);
        travelMapper.deleteFabulous(user.getId(),id);
    }

    @Override
    public void collectTratel(Long travelId) {
        User user = UserContext.getUser();
        //攻略收藏数增加
        travelMapper.updateAddCollectTravel(travelId);
        //保存攻略收藏的关系
        travelMapper.insertCollectionRelation(user.getId(), travelId);
    }

    @Override
    public Integer getTravelCollection(Long travelId) {
        return travelMapper.selectTravelCollection(travelId);
    }

    @Override
    public void disCollectTratel(Long travelId) {
        User user = UserContext.getUser();
        //攻略收藏数减少
        travelMapper.updateReductCollectTravel(travelId);
        //移除攻略收藏的关系
        travelMapper.deleteCollectionRelation(user.getId(), travelId);
    }


    /**
     * 查游记
     * @param qo
     * @return
     */
    @Override
    public PageInfo getTraversCounts(TravelQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Travel> list = travelMapper.getTraversCounts(qo);
        return new PageInfo(list);
    }

    /**
     * 通知查看后,状态改为已查看
     * @param id
     */
    @Override
    public void changeState(Long id) {
        travelMapper.updateInform(id);
    }

    /**
     * 查全部游记不分页
     * @return
     */
    @Override
    public List<Travel> selectAlltravel() {
        return travelMapper.selectAlltravel();
    }

    @Override
    public List<Travel> queryTravelReord(Long userId, QueryObject qo) {
        String key = MessageFormat.format(RedisConstant.TRAVEL_BROWSE,userId);
        int start = qo.getStart();
        int end = start+qo.getPageSize()-1;
        List<Long> range = redisTemplate.opsForList().range(key, start, end);
        List<Travel> list = new ArrayList<>();
        for (Long travelId : range) {
            Travel travel = travelMapper.selectByPrimaryKey(travelId);
            list.add(travel);
        }
        return list;
    }

}
