package com.yeta.sbl2.service;

import com.yeta.sbl2.domain.MyResponse;

/**
 * 秒杀逻辑层
 * @author YETA
 * @date 2018/05/17/17:09
 */
public interface SeckillService {

    /**
     * 查询所有秒杀信息
     * @return
     */
    MyResponse findAllSeckill();

    /**
     * 根据id查询秒杀信息
     * @param id
     * @return
     */
    MyResponse findSeckillById(Integer id);

    /**
     * 获取秒杀地址
     * 秒杀开启的时候返回地址
     * 秒杀关闭的时候返回秒杀开启时间
     * @param id
     */
    MyResponse getSeckillUrl(Integer id);

    /**
     * 秒杀
     * @param id
     * @param url
     * @param sbl2Login
     * @return
     */
    MyResponse seckill(Integer id, String url, String sbl2Login);

}
