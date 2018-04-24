package com.yeta.sbl2.service;

import java.util.concurrent.Future;

/**
 * 任务service
 * Created by YETA666 on 2018/4/20 0020.
 */
public interface TaskService {

    void scheduledTaskTest();

    Future<Boolean> asyncTaskTest1() throws Exception;

    Future<Boolean> asyncTaskTest2() throws Exception;

}
