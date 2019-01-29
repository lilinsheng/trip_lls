package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.mapper.CommentMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ICommentService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements ICommentService{
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 获取当前用户未被查看的评论数
     * @return
     */
    @Override
    public int getUnreadCommentNumber() {
        Long userId = UserContext.getUser().getId(); // 当前用户id
        int travels = commentMapper.selectUnreadTravelCount(userId); // 查看当前用户未被查看的游记点赞数
        int friends = commentMapper.selectUnreadFriendCount(userId); // 查看当前用户未被查看的朋友圈动态点赞数
        return travels + friends;
    }

    /**
     * 获取被评论的游记数据
     * @param qo
     * @return
     */
    @Override
    public PageInfo getTravelHistory(QueryObject qo) {
        Long userId = UserContext.getUser().getId(); // 当前用户id
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Map> list = commentMapper.selectTravelHistory(userId);
        return new PageInfo(list);
    }
    /**
     * 获取被评论的朋友圈
     * @param qo
     * @return
     */
    @Override
    public PageInfo getFriendHistory(QueryObject qo) {
        Long userId = UserContext.getUser().getId(); // 当前用户id
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Map> list = commentMapper.selectFriendHistory(userId);
        return new PageInfo(list);
    }

    /**
     * 把未查看状态改为已查看  游记的
     */
    @Override
    public void changeTravelState(Long id) {
        commentMapper.updateTravelState(id);
    }
    /**
     * 把未查看状态改为已查看  朋友圈的
     */
    @Override
    public void changeFriendState(Long id) {
        commentMapper.updateFriendState(id);
    }
}
