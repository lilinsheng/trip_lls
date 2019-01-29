package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IFereService {

    /**
     * 查询全部结伴信息,并分页
     * @param qo
     * @return
     */
    PageInfo selectFeres(QueryObject qo);
}
