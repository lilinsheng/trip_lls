package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.domain.QuestionAndComment;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IQuestionService {
    PageInfo query(QueryObject qo);

    void deleteByIdQuestion(Long id);

    void saveOrUpdate(Question question);

    Question selectCommentById(Long id);

    PageInfo selectComment(Long id);

    void add(QuestionAndComment qc);

    int selectCount(Long id);
}
