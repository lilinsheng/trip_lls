package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.domain.QuestionAndComment;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IQuestionService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.QiniuUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @GetMapping
    public PageInfo getList(QueryObject qo){
        return questionService.query(qo);
    }

    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(Question question, MultipartFile file){
        if (file!=null){
            String url = QiniuUtil.QI_PATH+QiniuUtil.qiniuyunImage(file);
            question.setCoverUrl(url);
        }
        questionService.saveOrUpdate(question);
        return new JsonResult();
    }

    @GetMapping("/{id}")
    public Question selectCommentById(@PathVariable Long id){
        Question question = questionService.selectCommentById(id);
        return question;
    }

    @GetMapping("/QuestionAndComment/{id}")
    public PageInfo selectComment(@PathVariable Long id){
        PageInfo pageInfo = questionService.selectComment(id);
        return pageInfo;
    }

    @PostMapping
    public JsonResult add(QuestionAndComment qc){
        questionService.add(qc);
        return new JsonResult();
    }

    @GetMapping("/count/{id}")
    public int selectCount(@PathVariable Long id){
        int count=questionService.selectCount(id);
        return count;
    }

}