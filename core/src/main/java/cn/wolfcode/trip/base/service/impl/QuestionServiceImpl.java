package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.domain.QuestionAndComment;
import cn.wolfcode.trip.base.mapper.QuestionAndCommentMapper;
import cn.wolfcode.trip.base.mapper.QuestionMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IQuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService{

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionAndCommentMapper questionAndCommentMapper;

    @Override
    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Question> list=questionMapper.query(qo);
        return new PageInfo(list);
    }

    @Override
    public void deleteByIdQuestion(Long id) {
        questionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public Question selectCommentById(Long id) {
        Question question=questionMapper.selectCommentById(id);
        return question;
    }

    @Override
    public PageInfo selectComment(Long id) {
        PageHelper.startPage(1,0);
        List<QuestionAndCommentMapper> list=questionAndCommentMapper.selectComment(id);
        return new PageInfo(list);
    }

    @Override
    public void add(QuestionAndComment qc) {
        questionAndCommentMapper.add(qc);
    }

    @Override
    public int selectCount(Long id) {
        return questionAndCommentMapper.selectCount(id);
    }

}
