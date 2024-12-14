package org.javaboy.vcher.utils;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

public class FileUtils {

    /*
    保存文件的目录
     */
    private static final String STORE_PATH = "/files/";

    /*
    模型资源目录
     */
    private static final String MODEL_PATH = "/3dmodels/";

    /**
     * 将文件保存在指定位置
     *
     * @param file 要上传的文件对象
     * @return 时间戳文件名
     */
    public static String upload(MultipartFile file) {

        if (file == null) return null;
        String fileName = null;

        try {
            String originalFileName = file.getOriginalFilename();
            // hutool：通过读取文件的首部几个二进制位来判断常用的文件类型
            String type = FileTypeUtil.getType(file.getInputStream());
            if (type == null) {
                // 文件无类型
                fileName = IdUtil.fastSimpleUUID();
            } else {
                if ("zip".equals(type)) {
                    String[] parts = originalFileName.toLowerCase().split("\\.");
                    if (parts.length > 1)
                        type = parts[parts.length - 1];
                }
                // 同时上传文件名可能冲突，鸵鸟策略~
                fileName = IdUtil.fastSimpleUUID() + "." + type;
            }

            // 拼接路径。需要先创建 files 目录
            String storePath = System.getProperty("user.dir") + STORE_PATH;
            String filePath = storePath + fileName;
            ensureFilesDir(storePath);//确保文件目录存在
            // 存储文件
            File dest = new File(filePath);
            file.transferTo(dest);

        } catch (Exception e) {

        }
        return fileName;
    }

    /**
     * 确保保存文件的目录存在
     */
    private static void ensureFilesDir(String storePath) throws Exception {
        File f = new File(storePath);

        if (f.isDirectory()) return;

        if (f.isFile()) f.delete();

        if (!f.exists()) f.mkdirs();
    }

    /**
     * 文件下载
     *
     * @param file     文件名
     * @param response 请求响应
     */
    public static void download(String file, HttpServletResponse response) throws IOException {
        OutputStream os;
        String basePath = System.getProperty("user.dir") + STORE_PATH;
        List<String> fileNames = FileUtil.listFileNames(basePath);

        // 根据已知文件名 flag 进行查找，最多查到一个文件
        String fileName = fileNames.stream().filter(name -> name.equals(file)).findAny().orElse("");

        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
                os = response.getOutputStream();   // 通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件：/3dmodels/dirName/asset
     *
     * @param dirName
     * @param asset
     * @param response
     */
    public static void download3dModelAsset(String dirName, String asset, HttpServletResponse response) {
        OutputStream os;
        String basePath = System.getProperty("user.dir") + MODEL_PATH + dirName + "/";
        if (FileUtil.isDirectory(basePath)) {

            List<String> fileNames = FileUtil.listFileNames(basePath);

            // 根据已知文件名 flag 进行查找，最多查到一个文件
            String fileName = fileNames.stream().filter(name -> name.equals(asset)).findAny().orElse("");

            try {
                if (StrUtil.isNotEmpty(fileName)) {
                    response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                    response.setContentType("application/octet-stream");
                    byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
                    os = response.getOutputStream();   // 通过输出流返回文件
                    os.write(bytes);
                    os.flush();
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
