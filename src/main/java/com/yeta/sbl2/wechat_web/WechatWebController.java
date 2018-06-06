package com.yeta.sbl2.wechat_web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yeta.sbl2.domain.MyResponse;
import com.yeta.sbl2.mapper.MyUserMapper;
import com.yeta.sbl2.pojo.User;
import com.yeta.sbl2.utils.HttpUtil;
import com.yeta.sbl2.wechat_web.domain.WechatWebUser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信登陆接口
 * @author YETA
 * @date 2018/06/05/10:09
 */
@RestController
@RequestMapping(value = "/wechatWeb")
public class WechatWebController {

    private String uuid = "";

    private String redirectURI = "";

    private Map<String, String> loginInfo = new HashMap<>();

    private WechatWebUser wechatWebUser = null;

    @Autowired
    private MyUserMapper myUserMapper;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @GetMapping(value = "/qrcode")
    private MyResponse qrcode() throws Exception {

        //获取UUID
        getUUID();

        //获取登陆二维码
        getQRCODE();

        //返回二维码路径
        return new MyResponse("upload/qrcode.jpeg");
    }

    @GetMapping(value = "/login")
    private MyResponse login(HttpServletResponse response) throws Exception {

        //获取扫码结果
        getScanLoginResult();

        //获取登陆相关重要信息
        getLoginInfo();

        //获取Uin
        Integer uin = Integer.valueOf(loginInfo.get("wxuin"));

        //判断uin是否获取成功
        if (uin == null) {
            throw new Exception("获取Uin错误！");
        }

        //根据Uin查询用户是否存在
        User user = myUserMapper.findByUin(uin);

        //如果用户不存在，要求绑定微信到已有账号，如果没有账号，还是需要注册
        if (user == null) {
            return new MyResponse(false, "请先绑定微信账号！");
        } else {
            String cookieValue = "true#" + user.getId().toString() + "#" + user.getUsername() + "#" + user.getName();
            Cookie cookie = new Cookie("sbl2Login", cookieValue);
            //cookie.setMaxAge(0);      //不记录cookie
            //cookie.setMaxAge(-1);      //会话级cookie，关闭浏览器失效
            cookie.setMaxAge(60 * 30);      //过期时间为60 * 30秒
            cookie.setPath(contextPath);
            response.addCookie(cookie);

            //返回
            return new MyResponse(contextPath + "/home");
        }
    }

    /**
     * 获取UUID方法
     * @return
     * @throws Exception
     */
    public String getUUID() throws Exception {

        //GET请求获取UUID
        HttpUtil httpUtil = new HttpUtil();
        String result = httpUtil.doGetStr("https://login.weixin.qq.com/jslogin?appid=wx782c26e4c19acffb&fun=new&lang=zh_CN&_=" + System.currentTimeMillis());

        //获取返回结果
        Integer code = Integer.valueOf(result.substring(result.indexOf("=") + 2, result.indexOf(";")));

        //判断获取是否成功
        if (code == null || code != 200) {
            throw new Exception("获取UUID发生错误！");
        }

        uuid = result.substring(result.indexOf("\"") + 1, result.lastIndexOf(";") - 1);

        return uuid;
    }

    /**
     * 获取登录二维码
     * @throws Exception
     */
    public void getQRCODE() throws Exception {
        HttpUtil httpUtil = new HttpUtil();
        //判断uuid是否存在
        if ("".equals(uuid)) {
            throw new Exception("获取UUID发生错误！");
        }
        httpUtil.doGetStr("https://login.weixin.qq.com/qrcode/" + uuid);
    }

    /**
     * 获取扫码登陆状态和结果
     * @throws Exception
     */
    public void getScanLoginResult() throws Exception {
        HttpUtil httpUtil = new HttpUtil();
        int tip = 1;    //未扫描
        //判断uuid是否存在
        if ("".equals(uuid)) {
            throw new Exception("获取UUID发生错误！");
        }
        while (true) {
            String result = httpUtil.doGetStr("https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/login?uuid=" + uuid + "&tip=" + tip + "&_=" + System.currentTimeMillis());
            Integer code = Integer.valueOf(result.substring(result.indexOf("=") + 1, result.indexOf(";")));
            if (code == 408) {
                System.out.println("等待扫描！");
            } else if (code == 201){
                System.out.println("已扫描，等待确认！");
                tip = 0;    //已扫描
                Thread.sleep(100);
            } else if (code == 200) {
                redirectURI = result.substring(result.indexOf("\"") + 1, result.lastIndexOf(";") - 1);
                System.out.println("已确认，跳转地址：" + redirectURI);
                break;
            }
        }
    }

    /**
     * 获取登陆的重要信息
     * @throws IOException
     * @throws DocumentException
     */
    public void getLoginInfo() throws Exception {
        //读取xml对象
        SAXReader reader = new SAXReader();
        //判断redirectURI是否存在
        if ("".equals(redirectURI)) {
            throw new Exception("获取redirectURI错误！");
        }
        Document document = reader.read(redirectURI + "&fun=new");
        //封装到map对象
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        for (Element element : elements) {
            loginInfo.put(element.getName(), element.getText());
        }
    }

    /**
     * 微信初始化
     * @throws IOException
     */
    public void wechatInit() throws Exception {

        //1. 获取初始化信息（账号、头像、好友、群组、公众号等，其中好友、群组、公众号等是最近活跃的）
        getInitInfo();

        //2. 开启微信状态通知
        openStatusNotify();

        //3. 获取好友列表
        getMemberList();
    }

    /**
     * 获取初始化信息（账号、头像、好友、群组、公众号等，其中好友、群组、公众号等是最近活跃的）
     * @throws Exception
     */
    public void getInitInfo() throws Exception {
        if (loginInfo.size() == 0) {
            throw new Exception("获取loginInfo错误！");
        }
        //初始化参数
        Map<String, Object> params = new HashMap<>();
        Map<String, String> baseRequest = new HashMap<>();
        baseRequest.put("Uin", loginInfo.get("wxuin"));
        baseRequest.put("Sid", loginInfo.get("wxsid"));
        baseRequest.put("Skey", loginInfo.get("skey"));
        baseRequest.put("DeviceID", "e123456789012345");
        params.put("BaseRequest", baseRequest);
        //POST请求
        HttpUtil httpUtil = new HttpUtil();
        String result = httpUtil.doPostStr("https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxinit?r=" + System.currentTimeMillis() + "&lang=ch_ZN&pass_ticket=" + loginInfo.get("pass_ticket"),
                JSON.toJSONString(params));
        //转换为模型对象
        wechatWebUser = JSON.parseObject(result, new TypeReference<WechatWebUser>() {});
    }

    /**
     * 开启微信状态通知
     * @throws IOException
     */
    public void openStatusNotify() throws Exception {
        if (loginInfo.size() == 0) {
            throw new Exception("获取loginInfo错误！");
        }
        //初始化参数
        Map<String, Object> params = new HashMap<>();
        Map<String, String> baseRequest = new HashMap<>();
        baseRequest.put("Uin", loginInfo.get("wxuin"));
        baseRequest.put("Sid", loginInfo.get("wxsid"));
        baseRequest.put("Skey", loginInfo.get("skey"));
        baseRequest.put("DeviceID", "e123456789012345");
        params.put("BaseRequest", baseRequest);
        params.put("Code", 3);
        params.put("FromUserName", "yeta123");
        params.put("ToUserName", "yeta123");
        params.put("ClientMsgId", System.currentTimeMillis());
        //POST请求
        HttpUtil httpUtil = new HttpUtil();
        String result = httpUtil.doPostStr("https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxstatusnotify?lang=zh_CN&pass_ticket=" + loginInfo.get("pass_ticket"),
                JSON.toJSONString(params));
        System.out.println(result);
    }

    /**
     * 获取好友列表
     * @throws Exception
     */
    public void getMemberList() throws Exception {
        if (loginInfo.size() == 0) {
            throw new Exception("获取loginInfo错误！");
        }

        HttpUtil httpUtil = new HttpUtil();
        String result = httpUtil.doGetStr("https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact" +
                "?r=" + System.currentTimeMillis() +
                "&seq=0" +
                "&skey=" + loginInfo.get("skey"));
        System.out.println(result);
    }
}
