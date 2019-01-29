package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.QuestionAndComment;

import java.util.List;

public interface QuestionAndCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionAndComment record);

    QuestionAndComment selectByPrimaryKey(Long id);

    List<QuestionAndComment> selectAll();

    int updateByPrimaryKey(QuestionAndComment record);

    List<QuestionAndCommentMapper> selectComment(Long id);

    void add(QuestionAndComment qc);

    int selectCount(Long id);
}