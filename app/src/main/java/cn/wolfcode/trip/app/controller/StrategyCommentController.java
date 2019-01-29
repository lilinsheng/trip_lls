package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCommentService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/strategyComments")
public class StrategyCommentController {
    @Autowired
    private IStrategyCommentService strategyCommentService;

    /**
     * 根据攻略id查询攻略评论
     */
    @GetMapping
    public PageInfo query(StrategyCommentQueryObject qo){
        qo.setOrderBy("sc.createTime desc");
        PageInfo pageInfo = strategyCommentService.query(qo);
        return pageInfo;
    }

    /**
     * 根据评论id查询攻略评论
     */
    @GetMapping("/{strategyId}")
    public StrategyComment getById(@PathVariable Long strategyId){
        return strategyCommentService.getById(strategyId);
    }

    /**
     * 新增攻略评论
     */
    @PostMapping
    public JsonResult save(StrategyComment strategyComment,String[] tagArr){
        strategyCommentService.save(strategyComment);
        if (tagArr!=null&&tagArr.length!=0){
            for (int i = 0;i<tagArr.length;i++){
                Tag tag = new Tag();
                tag.setName(tagArr[i]);
                strategyCommentService.saveTag(tag);
                strategyCommentService.saveRelation(strategyComment.getId(),tag.getId());
            }
        }
        return new JsonResult();
    }


}
