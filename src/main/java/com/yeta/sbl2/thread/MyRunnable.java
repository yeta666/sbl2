package com.yeta.sbl2.thread;

/**
 * @author YETA
 * @date 2018/05/28/16:06
 */
public class MyRunnable implements Runnable {

    //线程运行标志，volatile表示线程之间的可见性
    volatile boolean run = true;

    private MyApple myApple;

    public MyRunnable() {
    }

    public MyRunnable(MyApple myApple) {
        this.myApple = myApple;
    }

    @Override
    public void run() {
        while (run) {       //线程运行条件
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " 执行第" + (i + 1) + "次！");
                if (!myApple.eat(1)) {
                    run = false;
                    break;
                }
            }
        }
        //System.out.println(Thread.currentThread().getName() + " 执行结束！");
    }

}
