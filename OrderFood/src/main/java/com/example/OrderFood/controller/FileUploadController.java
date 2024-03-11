package com.example.OrderFood.controller;

/**
 * Created by 永恒星辰
 * Date 2024/3/12 0:49
 * Description
 */
import com.example.OrderFood.entity.Result;

import jakarta.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class FileUploadController {

    @PostMapping("/upload")
    public Result<?> handleFileUpload(@RequestParam("file") MultipartFile file,
                                      @RequestParam Map<String, String> form) {

        Logger logger = LoggerFactory.getLogger(FileUploadController.class);
        if (file.isEmpty()) {
            return Result.failure("请选择一个文件");
        }

        try {
            // 获取静态目录的路径
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            String staticDir = resolver.getResource("classpath:").getFile().getAbsolutePath() + "\\static\\images\\";

            if (!form.isEmpty()) {
                String fileName = form.get("img");
                // 构建目标文件路径
                Path targetLocation = Paths.get(staticDir + fileName);

                // 判断文件是否存在，存在则覆盖
                if (Files.exists(targetLocation)) {
                    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.copy(file.getInputStream(), targetLocation);
                }
            }
            return Result.success();
        } catch (IOException ex) {
            ex.printStackTrace();
            return Result.failure("文件上传异常");
        }
    }
}
