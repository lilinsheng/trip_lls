package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Mall;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.MallMapper;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IMallService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallServiceImpl implements IMallService {

    @Autowired
    private MallMapper mallMapper;

    @Override
    public PageInfo query(QueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<Mall> list = mallMapper.selectMall(qo);
        return new PageInfo(list);
    }

    @Override
    public void saveOrUpdate(Mall mall) {
        if (mall.getId()!=null){
            mallMapper.updateByPrimaryKey(mall);
        }else {
            mallMapper.insert(mall);
        }
    }

    @Override
    public void updateScore(Long score,Long userId) {
        User user = UserContext.getUser();
        userId  = user.getId();
        Long userScore = user.getScore();
        if (userScore >= score){
            mallMapper.updateScore(user.getScore()-score,userId);
        }
    }
}
