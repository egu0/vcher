package org.javaboy.vcher.mapper;

import org.javaboy.vcher.model.StudyLesson;

import java.util.List;

public interface StudyLessonMapper {

    List<StudyLesson> selectByPage(Integer page, Integer size, String keyword);

    Long getTotal(String keyword);

    Integer insertOne(StudyLesson lesson);

    Integer deleteById(Integer id);

    Integer updateOne(StudyLesson lesson);
}
