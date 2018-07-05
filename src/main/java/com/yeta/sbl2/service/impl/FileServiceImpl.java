package com.yeta.sbl2.service.impl;

import com.alibaba.fastjson.JSON;
import com.yeta.sbl2.domain.MyResponse;
import com.yeta.sbl2.service.FileService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

    /**
     * 创建Excel文件
     * @param fileName
     * @throws IOException
     */
    public static void writeExcel(String fileName) throws IOException {
        //创建Excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet();

        //添加数据
        for (int i = 0; i < 10; i++) {
            //创建一行
            HSSFRow row = sheet.createRow(i);
            //设置单元格宽度
            for (int j = 0; j < 10; j++) {
                //创建一个单元格
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }

        //创建文件
        String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        File file = new File( path+ File.separator + "static/upload/" + fileName + "_" + System.currentTimeMillis() + ".xls");
        file.createNewFile();

        //将Excel写入到文件
        FileOutputStream fos = FileUtils.openOutputStream(file);
        workbook.write(fos);

        //清理
        fos.close();
    }

    /**
     * 读取Excel文件
     * @param fileName
     * @throws Exception
     */
    public static void readExcel(String fileName) throws Exception {
        //获取文件
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/upload/" + fileName);

        //判断文件是否存在
        if (!file.exists()) {
            throw new Exception("文件：" + fileName + "不存在！");
        }

        //通过文件创建Excel工作簿
        FileInputStream fis = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);

        //获取工作表sheet
        HSSFSheet sheet = workbook.getSheetAt(0);

        //读取数据
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                HSSFCell cell = row.getCell(j);
                Object value = null;
                //判断单元格类型
                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        //如果为时间格式的内容
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                            break;
                        } else {
                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // 字符串
                        value = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        value = cell.getBooleanCellValue() + "";
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        value = cell.getCellFormula() + "";
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        value = "";
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        value = "非法字符";
                        break;
                    default:
                        value = "未知类型";
                        break;
                }
                /*switch (cell.getCellTypeEnum()) {
                    case _NONE:
                        value = "_NONE";
                        break;
                    case BLANK:
                        value = "BLANK";
                        break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case ERROR:
                        value = cell.getErrorCellValue();
                        break;
                    case FORMULA:
                        value = cell.getCellFormula();
                        break;
                    case NUMERIC:
                        value = cell.getNumericCellValue();
                        break;
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    default:
                        value = "UNKNOW";
                        break;
                }*/
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
