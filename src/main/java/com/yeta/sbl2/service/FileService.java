package com.yeta.sbl2.service;

import com.yeta.sbl2.domain.MyResponse;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 文件上传下载逻辑层
 * @author YETA
 * @date 2018/05/31/20:47
 */
public interface FileService {

    MyResponse list() throws FileNotFoundException;

    MyResponse upload(List<MultipartFile> files) throws IOException;

    MyResponse delete(String fileName);

    void download(String fileName, HttpServletResponse response) throws Exception;
}
