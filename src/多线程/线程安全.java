package 多线程;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 线程安全 {
    public static void main(String[] args) {
        MyThread2 t1 = new MyThread2();
        MyThread2 t2 = new MyThread2();
        MyThread2 t3 = new MyThread2();
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");//三个窗口卖100张票,票不允许重复
        t1.start();
        t2.start();
        t3.start();

        //第二种方法
        MyRunnable mr = new MyRunnable();
        Thread th1 = new Thread(mr);
        Thread th2 = new Thread(mr);
        Thread th3 = new Thread(mr);
        th1.setName("窗口1");
        th2.setName("窗口2");
        th3.setName("窗口3");//三个窗口卖100张票,票不允许重复
        th1.start();
        th2.start();
        th3.start();

    }
}

class MyThread2 extends Thread {
    //ticket没有用静态修饰->票从1到100每个窗口都卖一遍,共300张票
    //原因:创建多个Thread对象,ticket不同步,如果使用Runnable接口,可以不使用static修饰
    //用静态修饰->还是会重复,并且可能超出范围
    //原因:线程的执行有随机性,还没来得及运行完,就被其他线程抢走CPU
    static int ticket = 0;

    //同步代码块:
    // 格式:synchronized(锁){操作共享数据的代码}
    // 锁默认打开,当有线程进入后,自动关闭,当锁里的代码全部执行完毕,线程出来,自动打开
    //锁对象必须是唯一的,例:static final Object obj = new Object();//synchronized (obj) {}
    //如果锁对象不一样,还是会重复//synchronized (this) {}
    //一般使用类的字节码 synchronized (MyThread2.class) {}

    //同步方法:
    //把锁加在整个方法上, 修饰符 synchronized 返回值类型 方法名(形参){}
    //特点:锁对象不能指定,非静态方法为this,静态方法为当前类的字节码对象

    //StringBuilder不安全,如果需要同步建议使用StringBuffer;两者用法一样,但是StringBuffer的方法都加了锁

    //Lock锁,比synchronized操作性更强,提供了lock()获得锁,与unlock()释放锁的方法
    //ReentrantLock()创建ReentrantLock的实例
    @Override
    public void run() {
        while (true) {
            if (soldTicket()) break;
        }
    }

    private synchronized boolean soldTicket() {
        if (ticket < 100) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "卖出了第" + ticket + "张票");
            ticket++;
        } else {
            return true;
        }
        return false;
    }
}

class MyRunnable implements Runnable {
    static Lock lock = new ReentrantLock();//static多个类对象共享一把锁(Runnable接口可不加static修饰)
    int ticket = 0;

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (ticket < 100) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "卖出了第" + ticket + "张票");
                    ticket++;
                } else {
                    //lock.unlock();//先开锁再退出,不然程序会一直运行
                    //优化方法:用try catch finally包裹加锁的循环
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {//break后跳至finally再退出while循环
                lock.unlock();
            }
        }
    }
}

class 死锁 implements Runnable {
    Object objA = new Object();
    Object objB = new Object();

    @Override
    public void run() {
        while (true) {
            //程序会卡死,因为A与B都拿到一把锁,等待对方释放锁
            if ("线程A".equals(Thread.currentThread().getName())) {
                synchronized (objA) {
                    System.out.println("线程A拿到锁A,等待锁B开启");
                    synchronized (objB) {
                        System.out.println("线程A拿到锁B,执行完一轮");
                    }
                }
            } else if ("线程B".equals(Thread.currentThread().getName())) {
                synchronized (objB) {
                    System.out.println("线程B拿到锁B,等待锁A开启");
                    synchronized (objA) {
                        System.out.println("线程B拿到锁A,执行完一轮");
                    }
                }
            }
        }
    }
}