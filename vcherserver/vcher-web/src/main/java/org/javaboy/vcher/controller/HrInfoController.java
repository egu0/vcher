package org.javaboy.vcher.controller;

import org.javaboy.vcher.model.Hr;
import org.javaboy.vcher.model.RespBean;
import org.javaboy.vcher.service.HrService;
import org.javaboy.vcher.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.Map;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2020-03-01 13:07
 */
@RestController
public class HrInfoController {

    @Autowired
    HrService hrService;

    @Value("${server.port}")
    Integer port;

    @Value("${proj.host}")
    String host;

    /**
     * 获取 hr 信息
     *
     * @param authentication
     * @return
     */
    @GetMapping("/hr/info")
    public Hr getCurrentHr(Authentication authentication) {
        return ((Hr) authentication.getPrincipal());
    }

    /**
     * hr 信息更新
     *
     * @param hr
     * @param authentication
     * @return
     */
    @PutMapping("/hr/info")
    public RespBean updateHr(@RequestBody Hr hr, Authentication authentication) {
        if (hrService.updateHr(hr) == 1) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hr, authentication.getCredentials(), authentication.getAuthorities()));
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 密码更新
     *
     * @param info
     * @return
     */
    @PutMapping("/hr/pass")
    public RespBean updateHrPasswd(@RequestBody Map<String, Object> info) {
        String oldpass = (String) info.get("oldpass");
        String pass = (String) info.get("pass");
        Integer hrid = (Integer) info.get("hrid");
        if (hrService.updateHrPasswd(oldpass, pass, hrid)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 上传头像
     *
     * @param file           文件
     * @param id             hr的id
     * @param authentication
     * @return
     */
    @PostMapping("/hr/userface")
    public RespBean updateHrUserface(MultipartFile file, Integer id, Authentication authentication) {

        String fileName = FileUtils.upload(file);
        if (fileName == null) {
            return RespBean.error("文件上传失败！");
        }
        String url = host + ":" + port + "/hr/img/" + fileName;

        if (hrService.updateUserface(url, id) == 1) {
            Hr hr = (Hr) authentication.getPrincipal();
            hr.setUserface(url);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hr, authentication.getCredentials(), authentication.getAuthorities()));
            return RespBean.ok("更新成功!", url);
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 下载图片
     *
     * @param flag     文件名
     * @param response
     */
    @GetMapping("/hr/img/{flag}")
    public void getFile(@PathVariable String flag,
                        HttpServletResponse response) {
        try {
            FileUtils.download(flag, response);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }


}