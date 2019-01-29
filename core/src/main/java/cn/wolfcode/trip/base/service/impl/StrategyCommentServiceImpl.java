package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.mapper.StrategyCommentMapper;
import cn.wolfcode.trip.base.mapper.StrategyMapper;
import cn.wolfcode.trip.base.mapper.TagMapper;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.query.StrategyCommentsQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCommentService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StrategyCommentServiceImpl implements IStrategyCommentService {
    @Autowired
    private StrategyCommentMapper strategyCommentMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public PageInfo query(StrategyCommentQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<StrategyComment> list = strategyCommentMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public StrategyComment getById(Long strategyId) {
        return strategyCommentMapper.selectByPrimaryKey(strategyId);
    }

    @Override
    public void save(StrategyComment strategyComment) {
        strategyComment.setCreateTime(new Date());
        strategyComment.setUser(UserContext.getUser());
        strategyCommentMapper.insert(strategyComment);
    }

    @Override
    public void saveTag(Tag tag) {
        tagMapper.insert(tag);
    }

    @Override
    public void saveRelation(Long commentId, Long tagId) {
        strategyCommentMapper.insertRelation(commentId,tagId);
    }

    //查指定用户的评论
    @Override
    public PageInfo queryStrategyComments(StrategyCommentQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<StrategyComment> list = strategyCommentMapper.queryStrategyComments(qo);
        return new PageInfo(list);
    }


}
