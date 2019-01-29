package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private IRegionService regionService;

    @RequestMapping("/list")
    public String list(){
        return "region/list";
    }

    @RequestMapping("/listByParentId")
    @ResponseBody
    public List listByParentId(Long parentId,String type){
        List<Region> list = regionService.listByParentId(parentId);
        if ("table".equals(type)){
            return list;
        }
        List<Map<String,Object>> tree = new ArrayList<>();
        for (Region region : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",region.getId());
            map.put("text",region.getName());
            map.put("lazyLoad",true);

            if (region.getState()==1){
                map.put("tags",new String[]{"推荐"});
            }
            tree.add(map);
        }
        return tree;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Region region){
        JsonResult jsonResult = new JsonResult();
        System.out.println(region);
        if (region.getId()!=null){
            regionService.update(region);
        }else {
            regionService.save(region);
        }
        return jsonResult;
    }
    @RequestMapping("/updateStatus")
    @ResponseBody
    public JsonResult updateStatus(Region region){
        if (region.getState()==1){
            region.setState(0);
        }else {
            region.setState(1);
        }
        regionService.updateStatus(region);
        return new JsonResult();
    }
}
