package com.yeta.sbl2.utils;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * http请求get方法和post方法
 * Created by YETA666 on 2018/5/2 0002.
 */
@Component
public class HttpUtil {

    public String doGetStr(String url) throws IOException {
        //初始化返回结果
        String result = "";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            Header contentType = entity.getContentType();
            //如果是文件类型，直接保存到文件夹
            if ("image/jpeg".equals(contentType.getValue())) {
                //获取输入流
                InputStream inputStream = entity.getContent();
                //项目根路径
                String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
                //保存文件的目录
                String saveDirName = "static/upload/";
                FileUtils.copyToFile(inputStream, new File(path + saveDirName + "qrcode.jpeg"));
            } else {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        }
        return result;
    }

    public String doPostStr(String url, String outStr) throws IOException {
        //初始化返回结果
        String result = "";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        if (!"".equals(outStr) && outStr != null) {
            post.setEntity(new StringEntity(outStr, "UTF-8"));
        }
        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity, "UTF-8");
        }
        return result;
    }
}
