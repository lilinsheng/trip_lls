package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyService;
import com.github.pagehelper.PageInfo;
import freemarker.core.Configurable;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/regions")
public class RegionController {
    @Autowired
    private IRegionService regionService;
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private ServletContext context;

    @GetMapping
    public List<Region> getAllRegions(){
        List<Region> list = regionService.listAll();
        return list;
    }

    /**
     * 根据状态查询地区
     * @param state
     * @return
     */
    @GetMapping("/{state}")
    public List<Region> getRegionsByState(@PathVariable Integer state){
        return regionService.getRegionsByState(state);
    }

    /**
     * 获取当前地区下的所有推荐和全部推荐，并以freemarker模板返回
     */
    @GetMapping(value = "/{placeId}/strategies",produces = "text/html")
    public void getStrategyByRegionsId(StrategyQueryObject qo, HttpServletResponse response) throws Exception {
        //获取地区下的所有攻略
        qo.setPageSize(4);
        PageInfo all = strategyService.listStrategyByRegion(qo);

        //获取地区下的所有热门攻略
        qo.setState(Region.STATE_HOT);
        qo.setPageSize(0);
        PageInfo hot = strategyService.listStrategyByRegion(qo);

        //创建freemarkerd对象
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        //获取模板
        String realPath = context.getRealPath("/template");
        config.setDirectoryForTemplateLoading(new File(realPath));
        config.setDefaultEncoding("utf-8");
        Template template = config.getTemplate("template.ftl");

        Map<String,Object> map = new HashMap<>();
        map.put("all",all.getList());
        map.put("hot",hot.getList());

        response.setContentType("text/html;charset=utf-8");
        template.process(map,response.getWriter());
    }

    @GetMapping(value = "/{placeId}/strategies",produces = "application/json")
    public PageInfo getStrategyByRegionsId(StrategyQueryObject qo){
        //获取地区下的所有攻略
        qo.setPageSize(4);
        PageInfo all = strategyService.listStrategyByRegion(qo);
        return all;
    }
}
