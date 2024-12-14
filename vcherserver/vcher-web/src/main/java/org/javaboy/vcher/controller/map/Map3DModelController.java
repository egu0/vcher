package org.javaboy.vcher.controller.map;

import org.javaboy.vcher.model.RespBean;
import org.javaboy.vcher.model.RespPageBean;
import org.javaboy.vcher.model.ThreeDModel;
import org.javaboy.vcher.service.Map3DModelService;
import org.javaboy.vcher.service.Map3DModelTypeService;
import org.javaboy.vcher.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/map/3d")
public class Map3DModelController {

    public final static Logger logger = LoggerFactory.getLogger(Map3DModelController.class);
    @Autowired
    Map3DModelTypeService map3DModelTypeService;
    @Autowired
    Map3DModelService map3DModelService;

    /**
     * 返回所有 3d 模型类型
     *
     * @return
     */
    @GetMapping("/types")
    public RespPageBean getAll() {
        return map3DModelTypeService.getAll();
    }

    @GetMapping("/")
    public RespPageBean get3DModels(@RequestParam(defaultValue = "1", required = false) Integer page,
                                    @RequestParam(defaultValue = "", required = false) String keyword,
                                    @RequestParam(defaultValue = "5", required = false) Integer size) {
        return map3DModelService.get3dModelByPage(page, size, keyword);
    }

    @PostMapping("/")
    public RespBean addOne(@RequestBody ThreeDModel threeDModel, Authentication authentication) {
        logger.info(threeDModel.toString());
        return map3DModelService.insert3DModel(threeDModel, authentication);
    }

    @PutMapping("/")
    public RespBean updateOne(@RequestBody ThreeDModel threeDModel) {
        logger.info(threeDModel.toString());
        return map3DModelService.update3DModel(threeDModel);
    }

    @DeleteMapping("/{id}/")
    public RespBean deleteOne(@PathVariable("id") Integer id) {
        return map3DModelService.delete3DModel(id);
    }

    /**
     * 下载模型资源：/map/3d/model/{dirName}/{asset}
     *
     * @param dirName
     * @param asset
     * @param response
     */
    @GetMapping("/model/{dirName}/{asset}")
    public void downloadModelAssets(@PathVariable String dirName,
                                    @PathVariable String asset,
                                    HttpServletResponse response) {
        try {
            FileUtils.download3dModelAsset(dirName, asset, response);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

}
