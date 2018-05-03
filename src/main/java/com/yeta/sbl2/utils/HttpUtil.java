package com.yeta.sbl2.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
            result = EntityUtils.toString(entity, "UTF-8");
        }
        return result;
    }

    public String doPostStr(String url, String outStr) throws IOException {
        //初始化返回结果
        String result = "";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(outStr, "UTF-8"));
        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity, "UTF-8");
        }
        return result;
    }
}
