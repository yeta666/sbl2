package com.yeta.sbl2.service.impl;

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

        //获取秒杀信息
        Seckill seckill = mySeckillMapper.findById(id);

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

        //减库存
        int reduceResult = mySeckillMapper.reduceNumber(id, new Date());

        if (reduceResult < 1) {
            throw new SeckillCloseException(SECKILL_CLOSE);
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

    }

}
