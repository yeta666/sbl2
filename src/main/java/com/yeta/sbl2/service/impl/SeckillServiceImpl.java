package com.yeta.sbl2.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yeta.sbl2.exception.SeckillCloseException;
import com.yeta.sbl2.exception.SeckillException;
import com.yeta.sbl2.exception.SeckillRepeatException;
import com.yeta.sbl2.mapper.MySeckillMapper;
import com.yeta.sbl2.mapper.MySeckillSuccessedMapper;
import com.yeta.sbl2.pojo.Seckill;
import com.yeta.sbl2.pojo.SeckillSuccessed;
import com.yeta.sbl2.service.SeckillService;
import com.yeta.sbl2.utils.MD5Util;
import com.yeta.sbl2.utils.MyResponse;
import com.yeta.sbl2.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

/**
 * 秒杀相关逻辑处理
 * @author YETA
 * @date 2018/05/17/17:10
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private MySeckillMapper mySeckillMapper;

    @Autowired
    private MySeckillSuccessedMapper mySeckillSuccessedMapper;

    @Autowired
    private RedisOperator redisOperator;
    
    private static final String SECKILL_NOT_FOUND = "未获取到秒杀信息！";

    private static final String SECKILL_CLOSE = "秒杀未开启！";

    private static final String SECKILL_MD5_ERROR = "秒杀凭据错误！";

    private static final String SECKILL_REPEAT = "重复秒杀！";

    private static final String SECKILL_SUCCESS = "秒杀成功！";

    @Override
    public MyResponse findAllSeckill() {

        //查询数据库
        List<Seckill> seckillList = mySeckillMapper.findAllSeckill();

        if (seckillList == null || seckillList.size() == 0) {
            throw new SeckillException(SECKILL_NOT_FOUND);
        }

        return new MyResponse(seckillList);

    }

    @Override
    public MyResponse findSeckillById(Integer id) {

        //查询数据库
        Seckill seckill = mySeckillMapper.findById(id);

        if (seckill == null) {
            throw new SeckillException(SECKILL_NOT_FOUND);
        }

        return new MyResponse(seckill);
    }

    @Override
    public MyResponse getSeckillUrl(Integer id) {
        //获取秒杀信息，通过Redis缓存

        //从Redis中获取该id对应的Seckill对象
        Seckill seckill = JSON.parseObject(redisOperator.get("seckill:" + id), new TypeReference<Seckill>(){});

        //如果为空，则从数据库中获取，并缓存进Redis
        if (seckill == null) {
            seckill = mySeckillMapper.findById(id);
            redisOperator.set("seckill:" + id, JSON.toJSONString(seckill), 60 * 60);        //缓存1小时
        }

        //判断是否获取到秒杀信息
        if (seckill == null) {
            throw new SeckillException(SECKILL_NOT_FOUND);
        }

        //判断当前是否是秒杀时间
        Date nowTime = new Date();
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            throw new SeckillCloseException(SECKILL_CLOSE);
        }

        return new MyResponse(MD5Util.getMd5(id.toString()));
    }

    /*@Override
    @Transactional      //开启事务
    public MyResponse seckill(Integer id, String md5, String sbl2Login) {

        if (md5 == null || !md5.equals(MD5Util.getMd5(id.toString()))) {
            throw new SeckillException(SECKILL_MD5_ERROR);
        }

        //查询秒杀记录
        Seckill seckill = mySeckillMapper.findById(id);

        //判断是否获取到秒杀信息
        if (seckill == null) {
            throw new SeckillException(SECKILL_NOT_FOUND);
        }

        //减库存
        int reduceResult = mySeckillMapper.reduceNumber(id, new Date());

        if (reduceResult < 1) {
            throw new SeckillCloseException(SECKILL_CLOSE);     //回滚
        }

        //插入秒杀成功信息
        SeckillSuccessed seckillSuccessed = new SeckillSuccessed();
        seckillSuccessed.setSeckillId(id);
        seckillSuccessed.setUsername(sbl2Login.split("#")[2]);
        seckillSuccessed.setName(sbl2Login.split("#")[3]);
        seckillSuccessed.setState(0);
        int insertResult = mySeckillSuccessedMapper.insertSeckillSuccessed(seckillSuccessed);

        if (insertResult < 1) {
            throw new SeckillRepeatException(SECKILL_REPEAT);       //回滚
        }
        return new MyResponse(SECKILL_SUCCESS);
    }*/

    /**
     * 优化，先执行insert，再执行update
     * 由于只有update会加行级锁，所以insert操作不会等update操作执行结束后再去执行
     * 其他优化方法：存储过程，通过节省行级锁的持有时间
     *              消息队列等
     * @param id
     * @param md5
     * @param sbl2Login
     * @return
     */
    @Override
    @Transactional      //开启事务
    public MyResponse seckill(Integer id, String md5, String sbl2Login) {

        if (md5 == null || !md5.equals(MD5Util.getMd5(id.toString()))) {
            throw new SeckillException(SECKILL_MD5_ERROR);
        }

        //查询秒杀记录
        Seckill seckill = mySeckillMapper.findById(id);

        //判断是否获取到秒杀信息
        if (seckill == null) {
            throw new SeckillException(SECKILL_NOT_FOUND);
        }

        //插入秒杀成功信息
        SeckillSuccessed seckillSuccessed = new SeckillSuccessed();
        seckillSuccessed.setSeckillId(id);
        seckillSuccessed.setUsername(sbl2Login.split("#")[2]);
        seckillSuccessed.setName(sbl2Login.split("#")[3]);
        seckillSuccessed.setState(0);
        int insertResult = mySeckillSuccessedMapper.insertSeckillSuccessed(seckillSuccessed);

        if (insertResult < 1) {
            throw new SeckillRepeatException(SECKILL_REPEAT);       //回滚
        } else {
            //减库存
            int reduceResult = mySeckillMapper.reduceNumber(id, new Date());

            if (reduceResult < 1) {
                throw new SeckillCloseException(SECKILL_CLOSE);     //回滚
            }
        }
        return new MyResponse(SECKILL_SUCCESS);
    }

}
