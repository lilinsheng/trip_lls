package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IQuestionService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @RequestMapping("/list")
    public String getList(@ModelAttribute("qo") QueryObject qo, Model model){
        Model model1 = model.addAttribute(questionService.query(qo));
        System.out.println(model1);
        return "question/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        questionService.deleteByIdQuestion(id);
        return "redirect:/questions/list.do";
    }
}