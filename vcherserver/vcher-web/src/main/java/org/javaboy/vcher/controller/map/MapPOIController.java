package org.javaboy.vcher.controller.map;

import org.javaboy.vcher.controller.study.StudyLessonController;
import org.javaboy.vcher.model.Map;
import org.javaboy.vcher.model.RespBean;
import org.javaboy.vcher.model.RespPageBean;
import org.javaboy.vcher.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map/poi")
public class MapPOIController {

    public final static Logger logger = LoggerFactory.getLogger(StudyLessonController.class);

    @Autowired
    MapService mapService;

    @GetMapping("/")
    public RespPageBean getAllMapInfo(@RequestParam(defaultValue = "1", required = false) Integer page,
                                      @RequestParam(defaultValue = "", required = false) String keyword,
                                      @RequestParam(defaultValue = "7", required = false) Integer size) {
        return mapService.getAllMapInfoByPage(page, size, keyword);
    }

    @PostMapping("/")
    public RespBean addOne(@RequestBody Map map, Authentication authentication) {
        logger.info("insert="+map.toString());
        return mapService.insertMap(map, authentication);
    }

    @PutMapping("/")
    public RespBean updateOne(@RequestBody Map map) {
        logger.info("update="+map.toString());
        return mapService.updateMap(map);
    }

    @DeleteMapping("/{id}/")
    public RespBean deleteOne(@PathVariable("id") Integer id) {
        return mapService.deleteMap(id);
    }

    @GetMapping("/{id}/")
    public RespBean getOne(@PathVariable("id") Integer id) {
        return mapService.getMapById(id);
    }
}
