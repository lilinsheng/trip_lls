package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.TravelComment;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.TravelCommentMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommentService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelCommentServiceImpl implements ITravelCommentService {
    @Autowired
    private TravelCommentMapper travelCommentMapper;

    @Override
    public PageInfo getCommentsByTravelId(TravelQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List list = travelCommentMapper.selectCommentsByTravelId(qo);
        return new PageInfo(list);
    }

    @Override
    public void save(TravelComment travelComment) {
        User user = UserContext.getUser();
        travelComment.setUser(user);
        travelCommentMapper.insert(travelComment);
    }

    @Override
    public PageInfo getTravelDiscuss(QueryObject qo) {
        User user = UserContext.getUser();
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<TravelComment> list = travelCommentMapper.selectMyTravelComment(qo,user.getId());
        return new PageInfo(list);
    }
}
