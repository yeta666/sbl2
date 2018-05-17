package com.yeta.sbl2.service;

import com.yeta.sbl2.pojo.Seckill;
import com.yeta.sbl2.pojo.SeckillSuccessed;

import java.util.List;

/**
 * @author YETA
 * @date 2018/05/17/17:09
 */
public interface SeckillService {

    List<Seckill> findAllSeckill();

    Seckill findById(Integer id);

    int reduceNumber(Integer id);

    List<SeckillSuccessed> findSeckillSuccessedBySeckillIdAndUsername(Integer seckillId, String username);

    int insertSeckillSuccessed();
}
