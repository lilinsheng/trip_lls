package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.query.FereContentQueryObject;
import com.github.pagehelper.PageInfo;

public interface IFereContentService {

    /**
     * 查全部旅行内容
     * @param qo
     * @return
     */
    PageInfo selectListAllFereContent(FereContentQueryObject qo);
}
