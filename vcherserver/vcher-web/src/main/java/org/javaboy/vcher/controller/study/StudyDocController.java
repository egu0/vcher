package org.javaboy.vcher.controller.study;

import org.javaboy.vcher.model.RespBean;
import org.javaboy.vcher.model.RespPageBean;
import org.javaboy.vcher.model.StudyDoc;
import org.javaboy.vcher.service.StudyService;
import org.javaboy.vcher.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/study/doc")
public class StudyDocController {

    public final static Logger logger = LoggerFactory.getLogger(StudyLessonController.class);

    @Autowired
    StudyService studyService;

    @Value("${server.port}")
    Integer port;

    @Value("${proj.host}")
    String host;

    /**
     * 分页获取记录
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public RespPageBean getStudyDoc(@RequestParam(defaultValue = "1", required = false) Integer page,
                                    @RequestParam(defaultValue = "", required = false) String keyword,
                                    @RequestParam(defaultValue = "5", required = false) Integer size) {
        return studyService.getStudyDocByPage(page, size, keyword);
    }

    /**
     * 添加记录
     *
     * @param doc
     * @param authentication
     * @return
     */
    @PostMapping("/")
    public RespBean addOne(@RequestBody StudyDoc doc, Authentication authentication) {
        if (doc != null)
            logger.info(doc.toString());
        return studyService.insertDoc(doc, authentication);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public RespBean uploadFile(MultipartFile file) {
        if (file == null) {
            return RespBean.error("上传失败！");
        }
        try {
            String fileName = file.getOriginalFilename();
            String fileFlag = FileUtils.upload(file);
            String url = host + ":" + port + "/study/doc/" + fileFlag;
            StudyDoc doc = new StudyDoc(null, fileName, null, url, null, null, null, LocalDateTime.now(), LocalDateTime.now());
            return RespBean.ok("上传成功！", doc);
        } catch (Exception e) {
            return RespBean.error("上传失败！");
        }
    }

    /**
     * 下载文件
     *
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")

    public void getFile(@PathVariable String flag,
                        HttpServletResponse response) {
        try {
            FileUtils.download(flag, response);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/")
    public RespBean deleteOne(@PathVariable("id") Integer id) {
        return studyService.deleteDoc(id);
    }

    /**
     * 更新记录的 desp,fname,link,upload_time,hr_id 字段
     *
     * @return
     */
    @PutMapping("/")
    public RespBean updateOne(@RequestBody StudyDoc doc) {
        logger.info(doc.toString());
        return studyService.updateDoc(doc);
    }

}
