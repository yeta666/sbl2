package com.yeta.sbl2.service.impl;

import com.yeta.sbl2.service.TaskService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * 任务相关操作接口实现类
 * Created by YETA666 on 2018/4/20 0020.
 */
@Service
public class TaskServiceImpl implements TaskService {

    /**
     * 定时任务测试
     */
    @Override
    @Scheduled(fixedRate = 10000)        //每10秒执行一次
    public void scheduledTaskTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
    }

    /**
     * 异步任务测试
     * @return
     * @throws Exception
     */
    @Override
    @Async
    public Future<Boolean> asyncTaskTest1() throws Exception {
        long startTime = System.currentTimeMillis();
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis();
        System.out.println("任务1耗时：" + (endTime - startTime) + "毫秒");
        return new AsyncResult<>(true);
    }

    /**
     * 异步任务
     * @return
     * @throws Exception
     */
    @Override
    @Async
    public Future<Boolean> asyncTaskTest2() throws Exception {
        long startTime = System.currentTimeMillis();
        Thread.sleep(700);
        long endTime = System.currentTimeMillis();
        System.out.println("任务2耗时：" + (endTime - startTime) + "毫秒");
        return new AsyncResult<>(true);
    }

}
