package com.yeta.sbl2.service.impl;

import com.yeta.sbl2.mapper.MySeckillMapper;
import com.yeta.sbl2.mapper.MySeckillSuccessedMapper;
import com.yeta.sbl2.pojo.Seckill;
import com.yeta.sbl2.pojo.SeckillSuccessed;
import com.yeta.sbl2.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author YETA
 * @date 2018/05/17/17:10
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private MySeckillMapper mySeckillMapper;

    @Autowired
    private MySeckillSuccessedMapper mySeckillSuccessedMapper;

    @Override
    public List<Seckill> findAllSeckill() {
        return mySeckillMapper.findAllSeckill();
    }

    @Override
    public Seckill findById(Integer id) {
        return mySeckillMapper.findById(id);
    }

    @Override
    public int reduceNumber(Integer id) {
        Date killTime = new Date();
        System.out.println(killTime);
        return mySeckillMapper.reduceNumber(id, killTime);
    }

    @Override
    public List<SeckillSuccessed> findSeckillSuccessedBySeckillIdAndUsername(Integer seckillId, String username) {
        return mySeckillSuccessedMapper.findSeckillSuccessedBySeckillIdAndUsername(seckillId, username);
    }

    @Override
    public int insertSeckillSuccessed() {

        SeckillSuccessed seckillSuccessed = new SeckillSuccessed();
        seckillSuccessed.setSeckillId(1);
        seckillSuccessed.setUsername("yeta2");
        seckillSuccessed.setName("yeta2");
        seckillSuccessed.setState(1);

        return mySeckillSuccessedMapper.insertSeckillSuccessed(seckillSuccessed);
    }
}
