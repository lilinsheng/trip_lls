package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyDetailComment;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.StrategyDetailCommentMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyDetailCommentService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyDetailCommentServiceImpl implements IStrategyDetailCommentService {
    @Autowired
    private StrategyDetailCommentMapper strategyDetailCommentMapper;

    @Override
    public PageInfo getAllCommentById(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List list = strategyDetailCommentMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public void save(StrategyDetailComment strategyDetailComment) {
        User user = UserContext.getUser();
        strategyDetailComment.setUser(user);
        strategyDetailCommentMapper.insert(strategyDetailComment);
    }
}
