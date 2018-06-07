package com.yeta.sbl2.service.impl;

import com.yeta.sbl2.domain.MyResponse;
import com.yeta.sbl2.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件上传下载逻辑层实现
 * @author YETA
 * @date 2018/05/31/20:50
 */
@Service
public class FileServiceImpl implements FileService {

    //项目根路径
    private String path;
    //保存文件的目录
    private String saveDirName = "static/upload";

    //错误类型
    private static final String FILE_PATH_ERROR = "服务器路径错误！";
    private static final String FILE_NOT_EXISTS = "文件不存在！";

    /**
     * 获取文件列表方法
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public MyResponse list() throws FileNotFoundException {

        //获取项目根路径
        path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();

        //判断路径是否错误
        if (path == null || "".equals(path)) {
            return new MyResponse(false, FILE_PATH_ERROR);
        }

        //创建文件对象
        File file = new File(path, saveDirName);

        //如果文件夹不存在，则创建
        if (!file.exists()) {
            file.mkdir();
        }

        //获取该文件夹下有哪些文件
        List<String> files = new ArrayList<>();
        for (File temp : file.listFiles()) {
            //如果是一个文件
            if (temp.isFile()) {
                files.add(temp.getName());
            }
        }

        return new MyResponse(files);
    }

    /**
     * 上传文件方法
     * @param files
     * @return
     * @throws IOException
     */
    @Override
    public MyResponse upload(List<MultipartFile> files) throws IOException {

        //判断路径是否错误
        if (path == null || "".equals(path)) {
            return new MyResponse(false, FILE_PATH_ERROR);
        }

        //遍历保存所有文件
        for (MultipartFile file : files) {
            //创建文件
            //设置文件名
            String name = file.getOriginalFilename();
            String namePrefix = name.substring(0, name.lastIndexOf("."));
            String nameSuffix = name.substring(name.lastIndexOf("."), name.length());
            File newFile = new File(path + saveDirName + File.separator + namePrefix + "_" + System.currentTimeMillis() + nameSuffix);

            //保存文件
            file.transferTo(newFile);
        }
        return new MyResponse();
    }

    /**
     * 删除文件方法
     * @param fileName
     * @return
     */
    @Override
    public MyResponse delete(String fileName) {

        //创建文件
        File file = new File(path + saveDirName + File.separator + fileName);

        //判断文件是否存在
        if (!file.exists()) {
            return new MyResponse(false, FILE_NOT_EXISTS);
        }

        //删除文件
        file.delete();

        return new MyResponse();
    }

    /**
     * 下载文件方法
     * @param fileNames
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    public void download(String fileNames, HttpServletResponse response) throws Exception {

        //处理要下载的文件名
        String[] fileNamesArray = fileNames.split(",");

        //强制下载
        response.setContentType("application/force-download");

        //判断是单个文件下载还是批量下载
        if (fileNamesArray.length == 1) {
            //单个文件下载

            String fileName = fileNamesArray[0];
            //创建文件
            File file = new File(path + saveDirName + File.separator + fileName);
            //判断文件是否存在
            if (!file.exists()) {
                throw new Exception(FILE_NOT_EXISTS);
            }

            //设置下载文件的文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            //将 文件内容 从 文件 读取到 文件输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            //将 文件内容 从 文件输入流 读取到 缓存输入流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            //初始化响应输出流
            OutputStream outputStream = response.getOutputStream();
            //缓冲区
            byte[] buffer = new byte[1024];
            //将 文件内容 从 缓存输入流 读取到 缓冲区 并通过 响应输出流 输出
            while (bufferedInputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }

            //清理
            fileInputStream.close();
            outputStream.close();
            bufferedInputStream.close();
        } else {
            //批量下载

            //设置压缩包的文件名
            response.setHeader("Content-Disposition", "attachment;fileName=all.zip");

            //初始化压缩输出流
            ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

            //将所有文件添加到压缩文件中
            for (String fileName : fileNamesArray) {
                //创建文件
                File file = new File(path + saveDirName + File.separator + fileName);
                //判断文件是否存在
                if (!file.exists()) {
                    //如果不存在则跳过
                    continue;
                }

                //设置文件名
                zipOutputStream.putNextEntry(new ZipEntry(fileName));

                //将 文件内容 从 文件 读取到 文件输入流
                FileInputStream fileInputStream = new FileInputStream(file);
                //将 文件内容 从 文件输入流 读取到 缓存输入流
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                //缓冲区
                byte[] buffer = new byte[1024];
                //将 文件内容 从 缓存输入流 读取到 缓冲区 并通过 压缩输出流 输出
                while (bufferedInputStream.read(buffer) != -1) {
                    zipOutputStream.write(buffer);
                }

                //刷新压缩文件流
                zipOutputStream.flush();
                //关闭文件输入流、缓冲输入流
                fileInputStream.close();
                bufferedInputStream.close();
            }

            //注释信息
            zipOutputStream.setComment("");
            zipOutputStream.flush();
            zipOutputStream.finish();
        }
    }
}
