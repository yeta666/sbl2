package com.yeta.sbl2.controller;

import com.yeta.sbl2.service.SeckillService;
import com.yeta.sbl2.utils.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping(value = "/findAllSeckill")
    public MyResponse findAllSeckill() {
        return seckillService.findAllSeckill();
    }

    /**
     * 根据id获取秒杀信息接口
     * @param id
     * @return
     */
    @GetMapping(value = "/findSeckillById")
    public MyResponse findSeckillById(@RequestParam(value = "id", required = true) Integer id) {
        return seckillService.findSeckillById(id);
    }

    /**
     * 获取秒杀路径接口
     * @param id
     * @return
     */
    @GetMapping(value = "/getSeckillUrl")
    public MyResponse getSeckillUrl(@RequestParam(value = "id", required = true) Integer id) {
        return seckillService.getSeckillUrl(id);
    }

    /**
     * 秒杀接口
     * @param id
     * @param md5
     * @param request
     * @return
     */
    @GetMapping(value = "/seckill")
    public MyResponse seckill(@RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "md5", required = true) String md5,
                              HttpServletRequest request) {
        return seckillService.seckill(id, md5, request);
    }
}
