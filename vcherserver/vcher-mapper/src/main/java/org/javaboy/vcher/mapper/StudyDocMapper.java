package org.javaboy.vcher.mapper;

import org.javaboy.vcher.model.StudyDoc;

import java.util.List;

public interface StudyDocMapper {
    Integer insertOne(StudyDoc doc);

    List<StudyDoc> selectByPage(Integer page, Integer size, String keyword);

    Long getTotal(String keyword);

    Integer deleteById(Integer id);

    Integer updateOne(StudyDoc doc);
}
