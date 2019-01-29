package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.mapper.FabulousMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IFabulousService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FabulousServiceImpl implements IFabulousService {
    @Autowired
    private FabulousMapper fabulousMapper;

    /**
     * 获取当前用户未被查看的点赞数
     *
     * @return
     */
    @Override
    public int getFabulousNumber() {
        Long userId = UserContext.getUser().getId(); // 当前用户id
        int travels = fabulousMapper.selectUnreadTravelCount(userId); // 查看当前用户未被查看的游记点赞数
        int friends = fabulousMapper.selectUnreadFriendCount(userId); // 查看当前用户未被查看的朋友圈动态点赞数
        return travels + friends;
    }

    /**
     * 获取被点赞的历史数据
     * @param qo
     * @return
     */
    @Override
    public PageInfo getFabulousHistory(QueryObject qo) {
        Long userId = UserContext.getUser().getId(); // 当前用户id
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Map> list = fabulousMapper.selectFabulousHistory(userId);
        return new PageInfo(list);
    }

    /**
     * 把未查看状态改为已查看
     */
    @Override
    public void changeState(Long id,Integer type) {
        if(type == 2){ // 游记的
            fabulousMapper.updateTravelState(id);
        }else{ // 朋友圈的
            fabulousMapper.updateFriendState(id);
        }

    }
}
