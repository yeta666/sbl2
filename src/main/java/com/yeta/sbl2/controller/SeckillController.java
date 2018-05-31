package com.yeta.sbl2.controller;

import com.yeta.sbl2.service.SeckillService;
import com.yeta.sbl2.domain.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 秒杀相关接口定义
 * @author YETA
 * @date 2018/05/18/20:34
 */
@RestController
@RequestMapping(value = "/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 获取所有秒杀信息接口
     * @return
     */
    @GetMapping(value = "/list")
    public MyResponse list() {
        return seckillService.findAllSeckill();
    }

    /**
     * 根据id获取秒杀信息接口
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}/detail")
    public MyResponse detail(@PathVariable("id") Integer id) {
        return seckillService.findSeckillById(id);
    }

    /**
     * 获取秒杀路径接口
     * @param id
     * @return
     */
    @PostMapping(value = "/{id}/md5")
    public MyResponse md5(@PathVariable("id") Integer id) {
        return seckillService.getSeckillUrl(id);
    }

    /**
     * 秒杀接口
     * @param id
     * @param md5
     * @param sbl2Login
     * @return
     */
    @PostMapping(value = "/{id}/{md5}/seckill")
    public MyResponse seckill(@PathVariable("id") Integer id,
                              @PathVariable("md5") String md5,
                              @CookieValue(value = "sbl2Login", required = true) String sbl2Login) {
        return seckillService.seckill(id, md5, sbl2Login);
    }

    /**
     * 获取系统时间接口
     * @return
     */
    @GetMapping(value = "/time")
    public MyResponse time() {
        return new MyResponse(new Date());
    }
}
