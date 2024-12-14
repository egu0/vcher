package org.javaboy.vcher.controller.study;

import org.javaboy.vcher.model.RespBean;
import org.javaboy.vcher.model.RespPageBean;
import org.javaboy.vcher.model.StudyProgress;
import org.javaboy.vcher.service.StudyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study/progress")
public class StudyProgressController {

    public final static Logger logger = LoggerFactory.getLogger(StudyLessonController.class);

    @Autowired
    StudyService studyService;

    @GetMapping("/")
    public RespPageBean getStudyProgress(@RequestParam(defaultValue = "1", required = false) Integer page,
                                         @RequestParam(defaultValue = "", required = false) String keyword,
                                         @RequestParam(defaultValue = "7", required = false) Integer size) {
        return studyService.getStudyProgressByPage(page, size, keyword);
    }

    /**
     * 返回 Echarts 展示所需的分页数据
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/echarts/")
    public RespBean getStudyProgressForEcharts(@RequestParam(defaultValue = "1", required = false) Integer page,
                                               @RequestParam(defaultValue = "7", required = false) Integer size) {
        return studyService.getStudyProgressForEchartsByPage(page, size);
    }

    @PostMapping("/")
    public RespBean addOne(@RequestBody StudyProgress progress, Authentication authentication) {
        logger.info(progress.toString());
        return studyService.insertProgress(progress, authentication);
    }

    @PutMapping("/")
    public RespBean updateOne(@RequestBody StudyProgress progress) {
        return studyService.updateProgress(progress);
    }

    @DeleteMapping("/{id}/")
    public RespBean deleteOne(@PathVariable("id") Integer id) {
        return studyService.deleteProgress(id);
    }
}
