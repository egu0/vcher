package org.javaboy.vcher.service;

import org.javaboy.vcher.mapper.Map3DModelTypeMapper;
import org.javaboy.vcher.model.RespPageBean;
import org.javaboy.vcher.model.ThreeDModelType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Map3DModelTypeService {

    //@Autowired
    @Resource
    Map3DModelTypeMapper map3DModelTypeMapper;

    public RespPageBean getAll() {
        List<ThreeDModelType> types = map3DModelTypeMapper.getAllTypes();
        RespPageBean bean = new RespPageBean();
        bean.setData(types);
        bean.setTotal((long) types.size());
        return bean;
    }
}
