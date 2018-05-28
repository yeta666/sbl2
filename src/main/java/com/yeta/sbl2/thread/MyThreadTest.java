package com.yeta.sbl2.thread;

/**
 * @author YETA
 * @date 2018/05/28/16:12
 */
public class MyThreadTest {

    public static void func1() throws InterruptedException {
        System.out.println("开始执行！");

        MyRunnable myRunnable1 = new MyRunnable();
        Thread xiaogang = new Thread(myRunnable1, "小刚");
        xiaogang.start();

        //让小刚线程跑一会，然后停止它，小刚线程能完整的把内部的循环执行完
        Thread.sleep(5);
        myRunnable1.run = false;

        MyThread xiaoming = new MyThread();
        xiaoming.setName("小明");
        xiaoming.start();
        xiaoming.join();        //main函数等小明线程执行完了才往下执行

        MyThread xiaohong = new MyThread();
        xiaohong.setName("小红");
        xiaohong.start();

        System.out.println("执行完毕！");
    }

    public static void func2() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        MyApple myApple = new MyApple();

        for (int i = 0; i < 25; i++) {
            Thread thread = new Thread(new MyRunnable(myApple), ("线程" + i));
            thread.start();
            if (i == 24) {
                thread.join();
            }
        }

        System.out.println((System.currentTimeMillis() - startTime) / 1000f + "秒");

    }

    public static void main(String[] args) throws InterruptedException {
        //func1();
        func2();
    }
}
