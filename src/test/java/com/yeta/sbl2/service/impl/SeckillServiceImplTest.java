package com.yeta.sbl2.service.impl;

import com.yeta.sbl2.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

/**
 * @author YETA
 * @date 2018/05/18/20:27
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
    public void findSeckillById() throws Exception {
        System.out.println(seckillService.findSeckillById(1));
    }

    @Test
    public void getSeckillUrl() throws Exception {
        System.out.println(seckillService.getSeckillUrl(1));
    }

    @Test
    public void seckill() throws Exception {

    }

}