package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.query.QueryObject;
import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    Question selectByPrimaryKey(Long id);

    List<Question> selectAll();

    int updateByPrimaryKey(Question record);

    List<Question> query(QueryObject qo);

    Question selectCommentById(Long id);
}