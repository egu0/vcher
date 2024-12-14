package org.javaboy.vcher.service;

import org.javaboy.vcher.mapper.StudyDocMapper;
import org.javaboy.vcher.mapper.StudyLessonMapper;
import org.javaboy.vcher.mapper.StudyProgressMapper;
import org.javaboy.vcher.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudyService {

    //@Autowired
    @Resource
    StudyLessonMapper studyLessonMapper;

    @Resource
    StudyDocMapper studyDocMapper;

    @Resource
    StudyProgressMapper studyProgressMapper;

    // region study-lesson

    /**
     * 分页获取数据
     *
     * @param page
     * @param size
     * @param keyword
     * @return
     */
    public RespPageBean getStudyLessonByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        } else {
            page = (page == null) ? 1 : page;
            size = (size == null) ? 5 : size;
        }
        List<StudyLesson> list = studyLessonMapper.selectByPage(page, size, keyword);
        Long total = studyLessonMapper.getTotal(keyword);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(list);
        pageBean.setTotal(total);
        return pageBean;
    }

    /**
     * 添加记录
     *
     * @param lesson         课程信息，非null
     * @param authentication 当前操作员
     * @return
     */
    public RespBean insertLesson(StudyLesson lesson, Authentication authentication) {
        if (!StringUtils.hasLength(lesson.getDesp())
                || !StringUtils.hasLength(lesson.getLink())
                || lesson.getTotal() == null) {
            return RespBean.error("添加失败：信息不完整！");
        }
        lesson.setHrId(((Hr) authentication.getPrincipal()).getId());
        lesson.setInsertTime(LocalDateTime.now());
        lesson.setUpdateTime(LocalDateTime.now());
        try {
            Integer affectRows = studyLessonMapper.insertOne(lesson);
            if (affectRows == 1) return RespBean.ok("添加成功！");
            else return RespBean.error("添加失败！");
        } catch (Exception e) {
            return RespBean.error("添加失败：" + e.getMessage() + "！");
        }
    }

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    public RespBean deleteLesson(Integer id) {
        if (id == null) {
            return RespBean.error("删除失败！");
        }
        try {
            Integer affectRows = studyLessonMapper.deleteById(id);
            if (affectRows == 1) return RespBean.ok("删除成功！");
            else return RespBean.error("删除失败！");
        } catch (Exception e) {
            return RespBean.error("删除失败：" + e.getMessage() + "！");
        }
    }

    /**
     * 更新记录
     *
     * @param lesson
     * @return
     */
    public RespBean updateLesson(StudyLesson lesson) {
        if (lesson == null || lesson.getId() == null) return RespBean.error("更新失败！");
        try {
            lesson.setUpdateTime(LocalDateTime.now());
            if (studyLessonMapper.updateOne(lesson) == 1) {
                return RespBean.ok("更新成功！");
            } else {
                return RespBean.error("更新失败！");
            }
        } catch (Exception e) {
            return RespBean.ok("更新失败：" + e.getMessage() + "！");
        }
    }

    // endregion

    // region study-doc

    public RespPageBean getStudyDocByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        } else {
            page = (page == null) ? 1 : page;
            size = (size == null) ? 5 : size;
        }
        List<StudyDoc> list = studyDocMapper.selectByPage(page, size, keyword);
        Long total = studyDocMapper.getTotal(keyword);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(list);
        pageBean.setTotal(total);
        return pageBean;
    }

    public RespBean insertDoc(StudyDoc doc, Authentication authentication) {
        if (doc == null) {
            return RespBean.error("添加失败：请选择要上传的文档");
        }
        Hr hr = (Hr) authentication.getPrincipal();
        doc.setHrId(hr.getId());
        doc.setInsertTime(LocalDateTime.now());
        doc.setUpdateTime(LocalDateTime.now());
        try {
            Integer affectRows = studyDocMapper.insertOne(doc);
            if (affectRows == 1) return RespBean.ok("添加成功！");
            else return RespBean.error("添加失败！");
        } catch (Exception e) {
            return RespBean.error("添加失败：" + e.getMessage() + "！");
        }
    }

    public RespBean deleteDoc(Integer id) {
        if (id == null) {
            return RespBean.error("删除失败！");
        }
        try {
            Integer affectRows = studyDocMapper.deleteById(id);
            if (affectRows == 1) return RespBean.ok("删除成功！");
            else return RespBean.error("删除失败！");
        } catch (Exception e) {
            return RespBean.error("删除失败：" + e.getMessage() + "！");
        }
    }

    public RespBean updateDoc(StudyDoc doc) {
        if (doc == null || doc.getId() == null) return RespBean.error("更新失败！");
        try {
            doc.setUpdateTime(LocalDateTime.now());
            if (studyDocMapper.updateOne(doc) == 1) {
                return RespBean.ok("更新成功！");
            } else {
                return RespBean.error("更新失败！");
            }
        } catch (Exception e) {
            return RespBean.ok("更新失败：" + e.getMessage() + "！");
        }
    }

    // endregion

    // region study-progress

    public RespPageBean getStudyProgressByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        } else {
            page = (page == null) ? 1 : page;
            size = (size == null) ? 7 : size;
        }
        List<StudyProgress> list = studyProgressMapper.selectByPage(page, size, keyword);
        Long total = studyProgressMapper.getTotal(keyword);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(list);
        pageBean.setTotal(total);
        return pageBean;
    }

    public RespBean insertProgress(StudyProgress progress, Authentication authentication) {
        if (progress == null || progress.gettDate() == null
                || progress.gettProgress() == null) {
            return RespBean.error("添加失败：缺少信息！");
        }
        if (progress.gettProgress() > 100 || progress.gettProgress() < 0) {
            return RespBean.error("插入失败：进度必须在0～100之间！");
        }
        progress.setHrId(((Hr) authentication.getPrincipal()).getId());
        progress.setLastUpdateTime(LocalDateTime.now());
        if (progress.getRemark() == null) progress.setRemark("");
        try {
            Integer affectRows = studyProgressMapper.insertOne(progress);
            if (affectRows == 1) return RespBean.ok("添加成功！");
            else return RespBean.error("添加失败！");
        } catch (Exception e) {
            return RespBean.error("添加失败：当天记录已存在或存在其他错误!");
        }
    }

    public RespBean updateProgress(StudyProgress progress) {
        if (progress == null || progress.getId() == null
                || progress.gettDate() == null || progress.gettProgress() == null) {
            return RespBean.error("更新失败：缺少信息！");
        }
        try {
            if (progress.gettProgress() > 100 || progress.gettProgress() < 0) {
                return RespBean.error("插入失败：进度必须在0～100之间！");
            }
            if (!StringUtils.hasLength(progress.getRemark())) progress.setRemark("");
            progress.setLastUpdateTime(LocalDateTime.now());
            if (studyProgressMapper.updateOne(progress) == 1) {
                return RespBean.ok("更新成功！");
            } else {
                return RespBean.error("更新失败！");
            }
        } catch (Exception e) {
            return RespBean.ok("更新失败：" + e.getMessage() + "！");
        }
    }

    public RespBean deleteProgress(Integer id) {
        if (id == null) {
            return RespBean.error("删除失败！");
        }
        try {
            Integer affectRows = studyProgressMapper.deleteById(id);
            if (affectRows == 1) return RespBean.ok("删除成功！");
            else return RespBean.error("删除失败！");
        } catch (Exception e) {
            return RespBean.error("删除失败：" + e.getMessage() + "！");
        }
    }

    public RespBean getStudyProgressForEchartsByPage(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        } else {
            page = (page == null) ? 1 : page;
            size = (size == null) ? 7 : size;
        }
        List<StudyProgress> list = studyProgressMapper.selectByPage(page, size, "");
        Long total = studyProgressMapper.getTotal("");
        Integer len = list.size();
        String[] xarr = new String[len];
        Integer[] yarr = new Integer[len];
        for (int i = 0; i < list.size(); i++) {
            xarr[len - i - 1] = list.get(i).gettDate().toString();
            yarr[len - i - 1] = list.get(i).gettProgress();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xarr", xarr);
        map.put("yarr", yarr);
        map.put("total", total);
        return RespBean.ok("查询成功！", map);
    }

    // endregion

}
