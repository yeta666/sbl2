package com.yeta.sbl2.wechat.utils;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.yeta.sbl2.wechat.domain.*;
import com.yeta.sbl2.utils.HttpUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;

/**
 * 微信消息处理工具类
 * Created by YETA666 on 2018/5/2 0002.
 */
@Component
public class WechatMessageUtil {

    @Autowired
    private HttpUtil httpUtil;
    
    private static final String MYURL = "http://pj53jx.natappfree.cc/sbl2/";

    /**
     * 消息类型
     */
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_THUMB = "thumb";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";

    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_EVENT_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_EVENT_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_EVENT_CLICK = "CLICK";
    public static final String MESSAGE_EVENT_VIEW = "VIEW";
    public static final String MESSAGE_EVENT_SCANCODE_PUSH = "scancode_push";

    /**
     * 回复消息：本订阅号简介
     */
    public static final String RESPONSE_DESCRIPTION = "本订阅号使用愉快玩耍的！";

    /**
     * 回复消息：帮助
     */
    public static final String RESPONSE_MENU = "感谢关注YETA的订阅号，请按以下提示操作：\n\n" +
            "1. 回复1：   查看“文本”消息；\n" +
            "2. 回复2：   查看“图文”消息；\n" +
            "3. 回复3：   查看“图片”消息；\n" +
            "4. 回复4：   查看“音乐”消息；\n" +
            "5. 回复?/？：查看帮助。";

    /**
     * appID
     */
    private static final String APPID = "wxdb20c140c01bb64c";

    /**
     * appsecret
     */
    private static final String APPSECRET = "3914eccff2fcdf251ac30acb9ec2ce0f";

    /**
     * sha1加密方法
     *
     * @param str
     * @return
     */
    public String sha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * xml转map方法
     *
     * @param request
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public Map<String, String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
        //初始化返回对象
        Map<String, String> result = new HashMap<>();

        SAXReader reader = new SAXReader();
        InputStream inputStream = request.getInputStream();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        for (Element element : elements) {
            result.put(element.getName(), element.getText());
        }
        return result;
    }

    /**
     * textMessage转xml方法
     *
     * @param wechatTextMessage
     * @return
     */
    private String textMessageToXml(WechatTextMessage wechatTextMessage) {
        XStream xsTerm = new XStream();
        xsTerm.alias("xml", wechatTextMessage.getClass());
        return xsTerm.toXML(wechatTextMessage);
    }

    /**
     * newsMessage转xml方法
     *
     * @param wechatNewsMessage
     * @return
     */
    private String newsMessageToXml(WechatNewsMessage wechatNewsMessage) {
        XStream xsTerm = new XStream();
        xsTerm.alias("xml", wechatNewsMessage.getClass());
        xsTerm.alias("item", new WechatNews().getClass());
        return xsTerm.toXML(wechatNewsMessage);
    }

    /**
     * imageMessage转xml方法
     *
     * @param wechatImageMessage
     * @return
     */
    private String imageMessageToXml(WechatImageMessage wechatImageMessage) {
        XStream xsTerm = new XStream();
        xsTerm.alias("xml", wechatImageMessage.getClass());
        return xsTerm.toXML(wechatImageMessage);
    }

    /**
     * musicMessage转xml方法
     *
     * @param wechatMusicMessage
     * @return
     */
    private String musicMessageToXml(WechatMusicMessage wechatMusicMessage) {
        XStream xsTerm = new XStream();
        xsTerm.alias("xml", wechatMusicMessage.getClass());
        return xsTerm.toXML(wechatMusicMessage);
    }

    /**
     * 初始化回复的文本消息
     *
     * @param messageMap
     * @param responseMessage
     * @return
     */
    public String initTextMessage(Map<String, String> messageMap, String responseMessage) {
        WechatTextMessage wechatTextMessage = new WechatTextMessage();
        wechatTextMessage.setToUserName(messageMap.get("FromUserName"));
        wechatTextMessage.setFromUserName(messageMap.get("ToUserName"));
        wechatTextMessage.setMsgType(MESSAGE_TEXT);
        wechatTextMessage.setCreateTime(new Date().getTime());
        wechatTextMessage.setContent(responseMessage);
        return textMessageToXml(wechatTextMessage);
    }

    /**
     * 初始化回复的图文消息
     *
     * @param messageMap
     * @return
     */
    public String initNewsMessage(Map<String, String> messageMap) {

        WechatNewsMessage wechatNewsMessage = new WechatNewsMessage();

        List<WechatNews> wechatNewsList = new ArrayList<>();

        WechatNews wechatNews = new WechatNews();
        wechatNews.setTitle("YETA简介");
        wechatNews.setDescription("YETA是...");
        wechatNews.setPicUrl(MYURL + "img/yeta.png");
        wechatNews.setUrl("www.baidu.com");
        wechatNewsList.add(wechatNews);

        WechatNews wechatNews1 = new WechatNews();
        wechatNews1.setTitle("RAY简介");
        wechatNews1.setDescription("RAY是...");
        wechatNews1.setPicUrl(MYURL + "img/yeta.png");
        wechatNews1.setUrl("www.qq.com");
        wechatNewsList.add(wechatNews1);

        wechatNewsMessage.setToUserName(messageMap.get("FromUserName"));
        wechatNewsMessage.setFromUserName(messageMap.get("ToUserName"));
        wechatNewsMessage.setCreateTime(new Date().getTime());
        wechatNewsMessage.setMsgType(MESSAGE_NEWS);
        wechatNewsMessage.setArticleCount(wechatNewsList.size());
        wechatNewsMessage.setArticles(wechatNewsList);
        return newsMessageToXml(wechatNewsMessage);
    }

    /**
     * 初始化回复的图片消息
     *
     * @param messageMap
     * @return
     * @throws Exception
     */
    public String initImageMessage(Map<String, String> messageMap) throws Exception {

        WechatImage wechatImage = new WechatImage();
        wechatImage.setMediaId(uploadGetMedialId("F:/Projects/Java/sbl2/src/main/resources/static/img/yeta.png", MESSAGE_IMAGE));

        WechatImageMessage wechatImageMessage = new WechatImageMessage();
        wechatImageMessage.setToUserName(messageMap.get("FromUserName"));
        wechatImageMessage.setFromUserName(messageMap.get("ToUserName"));
        wechatImageMessage.setCreateTime(new Date().getTime());
        wechatImageMessage.setMsgType(MESSAGE_IMAGE);
        wechatImageMessage.setWechatImage(wechatImage);

        return imageMessageToXml(wechatImageMessage);
    }

    /**
     * 初始化回复的音乐消息
     *
     * @param messageMap
     * @return
     */
    public String initMusicMessage(Map<String, String> messageMap) throws Exception {

        WechatMusic wechatMusic = new WechatMusic();
        wechatMusic.setTitle("凉凉");
        wechatMusic.setDescription("三生三世，十里桃花主题曲。");
        wechatMusic.setMusicUrl(MYURL + "music/liangliang.mp3");
        wechatMusic.setHQMusicUrl(MYURL + "music/liangliang.mp3");
        wechatMusic.setThumbMediaId(uploadGetMedialId("F:/Projects/Java/sbl2/src/main/resources/static/img/yeta.png", MESSAGE_THUMB));

        WechatMusicMessage wechatMusicMessage = new WechatMusicMessage();
        wechatMusicMessage.setToUserName(messageMap.get("FromUserName"));
        wechatMusicMessage.setFromUserName(messageMap.get("ToUserName"));
        wechatMusicMessage.setCreateTime(new Date().getTime());
        wechatMusicMessage.setMsgType(MESSAGE_MUSIC);
        wechatMusicMessage.setWechatMusic(wechatMusic);

        return musicMessageToXml(wechatMusicMessage);

    }

    /**
     * 获取access token方法
     *
     * @return
     * @throws IOException
     */
    public WechatAccessToken getAccessToken() throws IOException {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"
                .replace("APPID", APPID)
                .replace("APPSECRET", APPSECRET);
        return (WechatAccessToken) JSON.parse(httpUtil.doGetStr(accessTokenUrl));
    }

    /**
     * 文件上传方法
     *
     * @param filePath
     * @param type
     * @return
     * @throws Exception
     */
    public String uploadGetMedialId(String filePath, String type) throws Exception {

        //判断文件是否存在
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new Exception("文件不存在！");
        }

        //设置上传路径
        String uploadUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadGetMedialId?access_token=ACCESS_TOKEN&type=TYPE"
                .replace("ACCESS_TOKEN", getAccessToken().getAccess_token())
                .replace("TYPE", type);

        //连接
        URL url = new URL(uploadUrl);
        HttpURLConnection connection = ((HttpURLConnection) url.openConnection());

        //设置提交方式
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        //设置请求头信息
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Charset", "UTF-8");

        //设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        //获得输出流
        OutputStream out = new DataOutputStream(connection.getOutputStream());
        //输出表头
        out.write(head);

        //文件正文部分
        //把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();

        //结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

        out.write(foot);

        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            //定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        String typeName = "media_id";
        if (!"image".equals(type)) {
            typeName = type + "_media_id";
        }
        Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
        if (map.get("errcode") != null) {
            throw new Exception("errcode: " + map.get("errcode") + "\t errmsg: " + map.get("errmsg"));
        }
        return map.get(typeName).toString();
    }

    /**
     * 初始化菜单方法
     * @throws IOException
     */
    public void initMenu() throws IOException {

        WechatButtonView one1 = new WechatButtonView();
        one1.setName("登陆");
        one1.setType("view");
        one1.setUrl("http://pj53jx.natappfree.cc/sbl2/login");

        WechatButtonClick one2 = new WechatButtonClick();
        one2.setName("?/？");
        one2.setType("click");
        one2.setKey("1");

        WechatButtonClick two1 = new WechatButtonClick();
        two1.setName("扫一扫");
        two1.setType("scancode_push");
        two1.setKey("11");

        WechatButtonClick two2 = new WechatButtonClick();
        two2.setName("位置");
        two2.setType("location_select");
        two2.setKey("12");

        WechatButton one3 = new WechatButton();
        one3.setName("功能");
        one3.setSub_button(new WechatButton[]{two1, two2});

        WechatMenu wechatMenu = new WechatMenu();
        wechatMenu.setButton(new WechatButton[]{one1, one2, one3});

        String result = httpUtil.doPostStr("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"
                .replace("ACCESS_TOKEN", getAccessToken().getAccess_token()), JSON.toJSONString(wechatMenu));

        Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
        if (0 == (int)map.get("errcode")) {
            System.out.println("初始化菜单成功！");
        } else {
            System.out.println("初始化菜单失败！错误码：" + map.get("errcode") + "，错误信息：" + map.get("errmsg"));
        }
    }
}
