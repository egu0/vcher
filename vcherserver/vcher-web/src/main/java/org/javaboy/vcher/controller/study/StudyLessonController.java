package org.javaboy.vcher.controller.study;

import org.javaboy.vcher.model.RespBean;
import org.javaboy.vcher.model.RespPageBean;
import org.javaboy.vcher.model.StudyLesson;
import org.javaboy.vcher.service.StudyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study/lesson")
public class StudyLessonController {

    public final static Logger logger = LoggerFactory.getLogger(StudyLessonController.class);

    @Autowired
    StudyService studyService;

    /**
     * 分页获取记录
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public RespPageBean getStudySrc(@RequestParam(defaultValue = "1", required = false) Integer page,
                                    @RequestParam(defaultValue = "", required = false) String keyword,
                                    @RequestParam(defaultValue = "5", required = false) Integer size) {
        return studyService.getStudyLessonByPage(page, size, keyword);
    }

    /**
     * 添加记录
     *
     * @param lesson
     * @param authentication
     * @return
     */
    @PostMapping("/")
    public RespBean addOne(@RequestBody StudyLesson lesson, Authentication authentication) {
        logger.info(lesson.toString());
        return studyService.insertLesson(lesson, authentication);
    }

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/")
    public RespBean deleteOne(@PathVariable("id") Integer id) {
        return studyService.deleteLesson(id);
    }

    /**
     * 更新记录的 total,finished,desp,link 字段
     *
     * @param lesson
     * @return
     */
    @PutMapping("/")
    public RespBean updateOne(@RequestBody StudyLesson lesson) {
        logger.info(lesson.toString());
        return studyService.updateLesson(lesson);
    }

}
