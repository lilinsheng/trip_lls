package cn.wolfcode.trip.base.service;


import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IFabulousService {

    /**
     * 获取当前用户未被查看的点赞数
     *
     * @return
     */
    int getFabulousNumber();

    /**
     * 获取被点赞的历史数据
     * @param qo
     * @return
     */
    PageInfo getFabulousHistory(QueryObject qo);

    /**
     * 把为查看状态改成已查看
     */
    void changeState(Long id,Integer type);
}
