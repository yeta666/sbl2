package com.yeta.sbl2.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yeta.sbl2.mapper.MyFunctionMapper;
import com.yeta.sbl2.mapper.MyRoleFunctionMapper;
import com.yeta.sbl2.mapper.MyUserMapper;
import com.yeta.sbl2.mapper.UserMapper;
import com.yeta.sbl2.mapper.MyUserRoleMapper;
import com.yeta.sbl2.pojo.Function;
import com.yeta.sbl2.pojo.RoleFunction;
import com.yeta.sbl2.pojo.User;
import com.yeta.sbl2.pojo.UserRole;
import com.yeta.sbl2.service.UserService;
import com.yeta.sbl2.utils.MailUtil;
import com.yeta.sbl2.utils.MyResponse;
import com.yeta.sbl2.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 用户相关操作接口实现类
 * Created by YETA666 on 2018/4/20 0020.
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyUserMapper myUserMapper;

    @Autowired
    private MyUserRoleMapper myUserRoleMapper;

    @Autowired
    private MyRoleFunctionMapper myRoleFunctionMapper;

    @Autowired
    private MyFunctionMapper myFunctionMapper;

    /**
     * 注入自定义的Redis封装操作类
     */
    @Autowired
    private RedisOperator redisOperator;

    /**
     * 注入自定义邮箱工具类
     */
    @Autowired
    private MailUtil mailUtil;

    /**
     * 保存用户
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)      //加了注解，遇到错误会回滚
    public MyResponse saveUser(User user) {
        MyResponse myResponse = new MyResponse();
        if (userMapper.insert(user) == 1) {
            myResponse.setMessage("save user success");
        }
        //int a = 1 / 0;
        return myResponse;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MyResponse updateUser(User user) {
        MyResponse myResponse = new MyResponse();
        if (userMapper.updateByPrimaryKeySelective(user) == 1) {
            myResponse.setMessage("update user success");
        }
        return myResponse;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MyResponse deleteUser(int id) {
        MyResponse myResponse = new MyResponse();
        if (userMapper.deleteByPrimaryKey(id) == 1) {
            myResponse.setMessage("delete user success");
        }
        return myResponse;
    }

    /**
     * 根据id查询用户
     * 操作Redis
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public MyResponse queryUserById(int id) {

        MyResponse myResponse = new MyResponse();
        User user = userMapper.selectByPrimaryKey(id);

        //将user存如redis
        redisOperator.set("user:" + user.getId().toString(), JSON.toJSONString(user));
        //取出来
        myResponse.setData(JSON.parse(redisOperator.get("user:" + user.getId().toString())));

        return myResponse;
    }

    /**
     * 自定义mapper实现
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public MyResponse findUserById(int id) {
        MyResponse myResponse = new MyResponse();
        myResponse.setData(myUserMapper.findUserById(id));
        return myResponse;
    }

    /**
     * 查询用户列表
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public MyResponse queryUsers(User user) {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmptyOrWhitespace(user.getName())) {
			//criteria.andEqualTo("name", user.getName());
            criteria.andLike("name", "%" + user.getName() + "%");
        }

        MyResponse myResponse = new MyResponse();
        myResponse.setData(userMapper.selectByExample(example));

        return myResponse;
    }

    /**
     * 查询用户列表并分页
     * @param page
     * @param pageSize
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public MyResponse queryUsersPaged(Integer page, Integer pageSize, User user) {
        //当前页
        if (page == null) {
            page = 1;
        }

        //每页条数
        if (pageSize == null)
        pageSize = 2;

        //开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(User.class);
        example.orderBy("id").desc();
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmptyOrWhitespace(user.getName())) {
            //criteria.andEqualTo("name", user.getName());
            criteria.andLike("name", "%" + user.getName() + "%");
        }

        MyResponse myResponse = new MyResponse();
        myResponse.setData(userMapper.selectByExample(example));

        return myResponse;
    }

    /**
     * 注册用户名方法
     * @param user
     * @return
     * @throws MessagingException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MyResponse register(User user) throws MessagingException {
        //设置用户状态为：未激活
        user.setState(false);
        //设置认证码
        user.setCode(UUID.randomUUID().toString().replace("-", ""));
        //存入数据库
        saveUser(user);
        //发送激活邮件
        mailUtil.sendMail(user.getEmail(), user.getCode());
        //初始化返回对象
        MyResponse myResponse = new MyResponse();
        return myResponse;
    }

    /**
     * 激活用户名方法
     * @param code
     * @return
     */
    @Override
    public MyResponse active(String code) {
        //根据激活码查询用户
        User user = myUserMapper.findUserByCode(code);
        if (user != null) {
            //修改用户状态为：激活
            user.setState(true);
            //设置认证码为空
            user.setCode(null);
            updateUser(user);
        }
        MyResponse myResponse = new MyResponse();
        return myResponse;
    }

    /**
     * 登陆方法
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public MyResponse login(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        //初始化返回结果
        MyResponse myResponse = new MyResponse();

        //根据用户名去数据库中查找
        User user = myUserMapper.findUserByUsername(username);

        if (user != null && user.getPassword() != null && password.equals(user.getPassword())) {
            //用户名存在且密码正确
            //写cookie
            String cookieValue = "true#" + user.getId().toString() + "#" + user.getUsername() + "#" + user.getName();
            Cookie cookie = new Cookie("sbl2Login", cookieValue);
            cookie.setPath("/");
            response.addCookie(cookie);

            //设置返回结果
            myResponse.setData(contextPath + "/home");
        } else {
            //用户名不存在或密码错误
            myResponse.setSuccess(false);
            myResponse.setMessage("用户名或密码错误！");
        }
        return myResponse;
    }

    /**
     * 注销方法
     * @param request
     * @param response
     * @return
     */
    @Override
    public MyResponse logout(HttpServletRequest request, HttpServletResponse response) {

        //初始化返回结果
        MyResponse myResponse = new MyResponse();

        //清除cookie
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("sbl2Login".equals(cookie.getName())) {
                    Cookie newCookie = new Cookie("sbl2Login", "false");
                    newCookie.setPath("/");
                    newCookie.setMaxAge(0);
                    response.addCookie(newCookie);
                }
            }
        }

        myResponse.setSuccess(true);
        myResponse.setData(contextPath + "/login");

        return myResponse;
    }

    /**
     * 根据权限获取功能
     * @param request
     * @param response
     * @return
     */
    @Override
    public MyResponse getMenu(HttpServletRequest request, HttpServletResponse response) {

        //初始化返回结果
        MyResponse myResponse = new MyResponse();

        //初始化功能列表
        List<Function> functionList = new ArrayList<>();

        //从cookie中获取用户id
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("sbl2Login".equals(cookie.getName())) {
                    Integer userId = Integer.valueOf(cookie.getValue().split("#")[1]);
                    //根据用户id获取用户对应的角色id
                    List<UserRole> userRoleList = myUserRoleMapper.findUserRoleByUserId(userId);
                    //遍历
                    for (UserRole userRole : userRoleList) {
                        Integer roleId = userRole.getRoleid();
                        //根据角色id获取角色对应的功能id
                        List<RoleFunction> roleFunctionList = myRoleFunctionMapper.findRoleFunctionByRoleId(roleId);
                        //遍历
                        for (RoleFunction roleFunction : roleFunctionList) {
                            Integer functionId = roleFunction.getFunctionid();
                            //根据功能id获取功能
                            Function function = myFunctionMapper.findFunctionById(functionId);
                            functionList.add(function);
                        }
                    }
                }
            }
        }

        //初始化菜单
        List<Map<String, Object>> menu = new ArrayList<>();

        //加入一级菜单
        for (Function function : functionList) {
            if (function.getParentid() == -1 && function.getLevel() == 1) {     //一级菜单
                Map<String, Object> menuLevel1 = new HashMap<>();
                menuLevel1.put("id", function.getId());
                menuLevel1.put("name", function.getName());
                menuLevel1.put("url", function.getUrl());
                menu.add(menuLevel1);
            }
        }

        //加入一级菜单对应的二级菜单
        for (Map<String, Object> menuLevel1 : menu) {
            List<Function> menuLevel2 = new ArrayList<>();
            for (Function function : functionList) {
                if (function.getParentid() == menuLevel1.get("id")) {       //是该一级菜单对应的二级菜单
                    menuLevel2.add(function);
                }
            }
            menuLevel1.put("menuLevel2", menuLevel2);
        }

        if (menu.size() == 0) {
            myResponse.setSuccess(false);
            myResponse.setMessage("该用户没有对应的菜单！");
        } else {
            myResponse.setData(menu);
        }
        return  myResponse;
    }
}
