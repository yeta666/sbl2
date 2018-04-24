package com.yeta.sbl2.service.impl;

import com.github.pagehelper.PageHelper;
import com.yeta.sbl2.mapper.MyUserMapper;
import com.yeta.sbl2.mapper.UserMapper;
import com.yeta.sbl2.pojo.User;
import com.yeta.sbl2.service.UserService;
import com.yeta.sbl2.utils.JsonUtils;
import com.yeta.sbl2.utils.MyResponse;
import com.yeta.sbl2.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户相关操作接口实现类
 * Created by YETA666 on 2018/4/20 0020.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 注入userMapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 注入自定义userMapper
     */
    @Autowired
    private MyUserMapper myUserMapper;

    /**
     * 注入自定义的Redis封装操作类
     */
    @Autowired
    private RedisOperator redisOperator;

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
        redisOperator.set("user:" + user.getId().toString(), JsonUtils.objectToJson(user));
        //取出来
        myResponse.setData(JsonUtils.jsonToPojo(redisOperator.get("user:" + user.getId().toString()), User.class));

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
}
