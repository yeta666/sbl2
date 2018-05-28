package com.yeta.sbl2.thread;

/**
 * @author YETA
 * @date 2018/05/28/15:49
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + " 执行第" + (i + 1) + "次！");
        }
        System.out.println(getName() + " 执行结束！");
    }
}
