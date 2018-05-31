package com.yeta.sbl2.controller;

import com.yeta.sbl2.service.TaskService;
import com.yeta.sbl2.domain.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.Future;

/**
 * 任务接口定义
 * Created by YETA666 on 2018/4/24 0024.
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/asyncTaskTest")
    public MyResponse asyncTaskTest() throws Exception {

        long startTime = System.currentTimeMillis();

        Future<Boolean> task1 = taskService.asyncTaskTest1();
        Future<Boolean> task2 = taskService.asyncTaskTest2();

        while (!task1.isDone() || !task2.isDone()) {
            if (task1.isDone() && task2.isDone()) {
                break;
            }
        }

        long endTime = System.currentTimeMillis();

        MyResponse myResponse = new MyResponse();
        myResponse.setMessage("任务1、2完成耗时：" + (endTime - startTime) + "毫秒");
        System.out.println("任务1、2完成耗时：" + (endTime - startTime) + "毫秒");
        return myResponse;
    }
}
