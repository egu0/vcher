package org.javaboy.vcher.mapper;

import org.javaboy.vcher.model.ThreeDModel;

import java.util.List;

public interface Map3DModelMapper {
    List<ThreeDModel> selectByPage(Integer page, Integer size, String keyword);

    Long getTotal(String keyword);

    Integer insertOne(ThreeDModel threeDModel);

    Integer updateOne(ThreeDModel threeDModel);

    Integer deleteById(Integer id);
}
