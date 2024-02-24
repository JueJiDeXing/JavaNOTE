package 基础.多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class 线程 {
    //并发:同一时间,有多个指令在单个CPU上交替执行
    //并行:同一时间,有多个指令在多个CPU上同时执行
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //多线程的三种实现方式---------------------------
        /*多线程的第一种启动方式:继承
        特点:拓展性较差,不能继承其他类
        1.定义一个类继承Thread
        2.重写run方法
        3.创建子类对象并启动线程
         */
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.setName("线程1");
        thread2.setName("线程2");
        thread1.start();
        thread2.start();

        /*多线程的第二种启动方式:Runnable接口
        特点:拓展性强
        1.定义一个类实现Runnable接口
        2.重写run方法
        3.创建自己类对象
        4.创建Thread类对象,并开启线程
         */
        MyRun myRun = new MyRun();
        Thread t1 = new Thread(myRun);
        Thread t2 = new Thread(myRun);
        t1.setPriority(10);//优先级
        t2.setDaemon(true);//守护线程
        t1.setName("--线c1");
        t2.setName("--线c2");
        t1.start();
        t2.start();

        /*多线程的第三种启动方式:(Callable接口和Future接口)
        特点:可以获取多线程运行的结果
        1.创建一个类实现Callable,重写call方法(有返回值)
        2.创建类对象
        3.创建FutureTask对象,管理多线程运行的结果
        5.创建Thread对象,并启动
         */

        MyCallable mc = new MyCallable();//要执行的线程
        FutureTask<Integer> ft1 = new FutureTask<>(mc);//管理结果
        FutureTask<Integer> ft2 = new FutureTask<>(mc);//管理结果
        Thread th1 = new Thread(ft1);
        Thread th2 = new Thread(ft2);
        int result1 = ft1.get();
        int result2 = ft2.get();

        //Thread成员方法---------------------------
        /*
        String getName()获取线程名,默认Thread-X  (X从0开始)
        void setName(String name)设置线程名
        static Thread currentThread()返回当前线程
        static void sleep(long time)休眠,单位毫秒

        setPriority(int newPriority)设置线程优先级 1~10,默认为5
        int getPriority()获取线程优先级
        //抢占式调度,线程的优先级越高,抢到CPU的概率越大(数学期望)

        void setDaemon(boolean on)设置为守护线程
        //当非守护线程执行完毕后,守护线程也结束
        static void yield()设置为出让/礼让线程
        //出让当前CPU对本线程的执行权
        void join()设置为插入/插队线程
        //线程执行完后才执行其他线程
         */

        //线程的生命周期
        //创建线程对象->新建状态->start()->就绪状态(有执行资格,没有执行权)->抢到CPU的执行权->运行状态
        //->被其他线程抢走CPU->回到就绪状态
        //->线程阻塞(遇到sleep方法等,没有执行资格,没有执行权)->阻塞结束->回到就绪状态
        //->运行结束->死亡状态

        /*线程的六个状态:新建,就绪,阻塞,等待,计时等待,结束

                                     得到锁                  无法获取锁
                                  ┌─────没有执行资格,没有执行权←───────┐
                                  │              阻塞               │
                                  │                                │
                                  │                                │
                                  │ notify()               wait()  │
                                  ├──────没有执行资格,没有执行权←──────┤
                                  │             等待                │
                                  │                                │
                                  │                                │
                                  │                                │
                                  │ 到时                     sleep()│
                                  ├──────没有执行资格,没有执行权←──────┤
                                  │            计时等待              │
                                  │                                │
                                  │                                │
                                  ↓         被其他线程抢走了cpu       │
        创建线程对象────────→有执行资格,没有执行权←───────────────→有执行资格,有执行权───────────→线程垃圾
         新建     start()       就绪        抢到CPU的执行权       (运行状态)   Run()执行完毕    结束
                                                                (运行由操作系统管理)
        */


    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName() + ":" + i);
        }
    }
}

class MyRun implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            Thread.yield();//礼让线程
        }
    }
}

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
