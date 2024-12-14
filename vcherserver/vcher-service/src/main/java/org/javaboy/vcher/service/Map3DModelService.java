package org.javaboy.vcher.service;

import org.javaboy.vcher.mapper.Map3DModelMapper;
import org.javaboy.vcher.model.Hr;
import org.javaboy.vcher.model.RespBean;
import org.javaboy.vcher.model.RespPageBean;
import org.javaboy.vcher.model.ThreeDModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class Map3DModelService {

    @Resource
    Map3DModelMapper map3DModelMapper;

    public RespPageBean get3dModelByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        } else {
            page = (page == null) ? 1 : page;
            size = (size == null) ? 7 : size;
        }
        List<ThreeDModel> list = map3DModelMapper.selectByPage(page, size, keyword);
        Long total = map3DModelMapper.getTotal(keyword);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(list);
        pageBean.setTotal(total);
        return pageBean;
    }

    // 插入。必需 type,src,dirName
    public RespBean insert3DModel(ThreeDModel threeDModel, Authentication authentication) {
        if (threeDModel == null || threeDModel.getType() == null
                || !StringUtils.hasLength(threeDModel.getSrc())
                || !StringUtils.hasLength(threeDModel.getDirName())) {
            return RespBean.error("添加失败：缺少信息！");
        }

        threeDModel.setHrId(((Hr) authentication.getPrincipal()).getId());
        threeDModel.setInsertTime(LocalDateTime.now());
        threeDModel.setUpdateTime(LocalDateTime.now());
        if (threeDModel.getDesp() == null) threeDModel.setDesp("");
        if (threeDModel.getMtl() == null) threeDModel.setMtl("");

        try {
            Integer affectRows = map3DModelMapper.insertOne(threeDModel);
            if (affectRows == 1) return RespBean.ok("添加成功！");
            else return RespBean.error("添加失败！");
        } catch (Exception e) {
            return RespBean.error("添加失败！");
        }
    }

    // 修改。必需 type,src,dirName
    public RespBean update3DModel(ThreeDModel threeDModel) {
        if (threeDModel == null || threeDModel.getId() == null
                || threeDModel.getType() == null
                || !StringUtils.hasLength(threeDModel.getSrc())
                || !StringUtils.hasLength(threeDModel.getDirName())) {
            return RespBean.error("更新失败：缺少信息！");
        }

        threeDModel.setUpdateTime(LocalDateTime.now());

        try {
            Integer affectRows = map3DModelMapper.updateOne(threeDModel);
            if (affectRows == 1) return RespBean.ok("修改成功！");
            else return RespBean.error("修改失败！");
        } catch (Exception e) {
            return RespBean.error("修改失败！");
        }
    }

    public RespBean delete3DModel(Integer id) {
        if (id == null) {
            return RespBean.error("删除失败！");
        }
        try {
            Integer affectRows = map3DModelMapper.deleteById(id);
            if (affectRows == 1) return RespBean.ok("删除成功！");
            else return RespBean.error("删除失败！");
        } catch (Exception e) {
            return RespBean.error("删除失败：" + e.getMessage() + "！");
        }
    }
}
