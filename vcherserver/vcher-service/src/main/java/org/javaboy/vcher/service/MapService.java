package org.javaboy.vcher.service;

import org.javaboy.vcher.mapper.MapMapper;
import org.javaboy.vcher.model.Hr;
import org.javaboy.vcher.model.Map;
import org.javaboy.vcher.model.RespBean;
import org.javaboy.vcher.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MapService {

    @Autowired
    MapMapper mapMapper;

    public RespPageBean getAllMapInfoByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        } else {
            page = (page == null) ? 1 : page;
            size = (size == null) ? 5 : size;
        }
        List<Map> list = mapMapper.selectByPage(page, size, keyword);
        Long total = mapMapper.getTotal(keyword);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(list);
        pageBean.setTotal(total);
        return pageBean;
    }

    public RespBean insertMap(Map map, Authentication authentication) {
        if (map == null || map.getLat() == null || map.getLng() == null
                || map.getZoom() == null) {
            return RespBean.error("添加失败：基础信息不完整！");
        }

        if (map.getZoom() < 4 || map.getZoom() > 19) {
            return RespBean.error("添加失败：地图缩放等级不合法～");
        }

        map.setHrId(((Hr) authentication.getPrincipal()).getId());
        map.setInsertTime(LocalDateTime.now());
        map.setUpdateTime(LocalDateTime.now());
        if (map.getData() == null) map.setData("[]");
        if (map.getRemark() == null) map.setRemark("");

        try {
            Integer affectRows = mapMapper.insertOne(map);
            if (affectRows == 1) return RespBean.ok("添加成功！");
            else return RespBean.error("添加失败！");
        } catch (Exception e) {
            return RespBean.error("添加失败：" + e.getMessage() + "！");
        }
    }

    public RespBean updateMap(Map map) {
        if (map == null || map.getId() == null || map.getLat() == null
                || map.getLng() == null || map.getZoom() == null
                || map.getData() == null
                || map.getRemark() == null) {
            return RespBean.error("失败：缺少信息！");
        }

        if (map.getZoom() < 4 || map.getZoom() > 19) {
            return RespBean.error("失败：地图缩放等级不合法～");
        }

        map.setUpdateTime(LocalDateTime.now());

        try {
            Integer affectRows = mapMapper.updateOne(map);
            if (affectRows == 1) return RespBean.ok("成功！");
            else return RespBean.error("失败！");
        } catch (Exception e) {
            return RespBean.error("失败：" + e.getMessage() + "！");
        }
    }


    public RespBean deleteMap(Integer id) {
        if (id == null) {
            return RespBean.error("删除失败！");
        }

        try {
            Integer affectRows = mapMapper.deleteById(id);
            if (affectRows == 1) return RespBean.ok("删除成功！");
            else return RespBean.error("删除失败！");
        } catch (Exception e) {
            return RespBean.error("删除失败：" + e.getMessage() + "！");
        }
    }

    public RespBean getMapById(Integer id) {
        if (id == null) {
            return RespBean.error("删除失败！");
        }
        try {
            Map map = mapMapper.getOneById(id);
            return RespBean.ok("查询成功!", map);
        } catch (Exception e) {
            return RespBean.error("查询出错");
        }
    }
}
