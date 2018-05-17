package com.yeta.sbl2.service.impl;

import com.yeta.sbl2.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author YETA
 * @date 2018/05/17/17:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SeckillServiceImplTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void findAllSeckill() throws Exception {
        System.out.println(seckillService.findAllSeckill());
    }

    @Test
    public void findById() throws Exception {
        System.out.println(seckillService.findById(1));
    }

    @Test
    public void reduceNumber() throws Exception {
        System.out.println(seckillService.reduceNumber(1));
    }

    @Test
    public void findSeckillSuccessedBySeckillId() throws Exception {
        System.out.println(seckillService.findSeckillSuccessedBySeckillIdAndUsername(1, "yeta1"));
    }

    @Test
    public void insertSeckillSuccessed() throws Exception {
        System.out.println(seckillService.insertSeckillSuccessed());
    }

}