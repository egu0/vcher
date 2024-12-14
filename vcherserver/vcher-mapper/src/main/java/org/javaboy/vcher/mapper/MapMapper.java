package org.javaboy.vcher.mapper;

import org.javaboy.vcher.model.Map;

import java.util.List;

public interface MapMapper {
    List<Map> selectByPage(Integer page, Integer size, String keyword);

    Long getTotal(String keyword);

    Integer insertOne(Map map);

    Integer updateOne(Map map);

    Integer deleteById(Integer id);

    Map getOneById(Integer id);
}
