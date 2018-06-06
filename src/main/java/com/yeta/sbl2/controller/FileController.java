package com.yeta.sbl2.controller;

import com.yeta.sbl2.domain.MyResponse;
import com.yeta.sbl2.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件上传下载接口
 * @author YETA
 * @date 2018/05/30/20:27
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/list")
    public MyResponse list() throws FileNotFoundException {
        return fileService.list();
    }

    @PostMapping(value = "/upload")
    public MyResponse upload(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
        return fileService.upload(file);
    }

    @PostMapping(value = "/delete")
    public MyResponse delete(@RequestParam(value = "fileName") String fileName) {
        return fileService.delete(fileName);
    }

    @GetMapping(value = "/download")
    public void download(@RequestParam(value = "fileNames") String fileNames, HttpServletResponse response) throws Exception {
        fileService.download(fileNames, response);
    }
}
