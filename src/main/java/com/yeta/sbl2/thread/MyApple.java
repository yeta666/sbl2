package com.yeta.sbl2.thread;

/**
 * @author YETA
 * @date 2018/05/28/20:26
 */
public class MyApple {

    private int notEatNumber = 1000000;

    private int eatNumber = 0;

    private final Object lock = new Object();

    /**
     * 如果不加锁，会导致不守恒
     * @param num
     * @return
     */
    public boolean eat(int num) {

        //对lock对象加锁实现线程互斥
        synchronized (lock) {
            if (notEatNumber > 0) {
                notEatNumber -= num;
                eatNumber += num;
                System.out.println("未吃数量：" + notEatNumber + " 已吃数量：" + eatNumber + " 总共数量：" + (notEatNumber + eatNumber));
                return true;
            } else {
                return false;
            }
        }
    }
}
