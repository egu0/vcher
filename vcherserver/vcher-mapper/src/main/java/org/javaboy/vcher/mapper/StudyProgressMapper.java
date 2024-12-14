package org.javaboy.vcher.mapper;

import org.javaboy.vcher.model.StudyProgress;

import java.util.List;

public interface StudyProgressMapper {
    List<StudyProgress> selectByPage(Integer page, Integer size, String keyword);

    Long getTotal(String keyword);

    Integer insertOne(StudyProgress progress);

    Integer updateOne(StudyProgress progress);

    Integer deleteById(Integer id);
}
