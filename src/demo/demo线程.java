package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demo线程 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        MyRunnable1 mr=new MyRunnable1();
        Thread t1=new Thread(mr);
        Thread t2=new Thread(mr);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t1.start();
        t2.start();

         */
/*
        MyRunnable2 mr=new MyRunnable2();
        Thread t1=new Thread(mr);
        Thread t2=new Thread(mr);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t1.start();
        t2.start();

 */
        /*
        MyRunnable3 mr = new MyRunnable3();
        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);

        t1.setName("抽奖1");
        t2.setName("抽奖2");

        t1.start();
        t2.start();
        */
        MyCallable mc=new MyCallable();
        FutureTask<Integer>ft1=new FutureTask<>(mc);
        FutureTask<Integer>ft2=new FutureTask<>(mc);
        Thread t1=new Thread(ft1);
        Thread t2=new Thread(ft2);
        t1.setName("抽奖1");
        t2.setName("抽奖2");

        t1.start();
        t2.start();
        Integer result1 = ft1.get();
        Integer result2 = ft2.get();
        if (result1>result2){
            System.out.println("抽奖1有最大奖"+result1);
        } else{
            System.out.println("抽奖2有最大奖"+result2);
        }
    }

}

class MyRunnable1 implements Runnable {
    int gift = 100;
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            try {
                if (gift == 0) {
                    break;
                } else {
                    Thread.sleep(100);
                    gift--;
                    System.out.println(Thread.currentThread().getName() + "剩余:" + gift);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("释放");
                lock.unlock();
            }

        }
    }
}

class MyRunnable2 implements Runnable {
    Object obj = new Object();
    int count = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                try {
                    if (count == 100) {
                        break;
                    } else {
                        Thread.sleep(100);
                        if (count % 2 == 1) {
                            System.out.println(count);
                        }
                        count++;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        }
    }
}

class MyRunnable3 implements Runnable {
    static ArrayList<Integer> arr = new ArrayList<>();
    static {
        Collections.addAll(arr, 500, 100, 30, 60, 5, 20, 50, 160, 200, 10, 400, 300, 40, 80);
    }

    Random random = new Random();
    static final Object lock = new Object();

    @Override
    public void run() {
        ArrayList<Integer> boxList = new ArrayList<>();//每个线程不共享,后面就不用判断是哪个线程了
        while (true) {
            String name = Thread.currentThread().getName();
            synchronized (lock) {
                if (arr.isEmpty()) {
                    System.out.println(name+boxList);
                    break;
                } else {
                    int prize=arr.remove(random.nextInt(arr.size()));
                    boxList.add(prize);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
class MyCallable implements Callable<Integer> {
    static ArrayList<Integer> arr = new ArrayList<>();
    static {
        Collections.addAll(arr, 500, 100, 30, 60, 5, 20, 50, 160, 200, 10, 400, 300, 40, 80);
    }

    Random random = new Random();
    static final Object lock = new Object();
    @Override
    public Integer call() throws Exception {
        ArrayList<Integer> boxList = new ArrayList<>();//每个线程不共享,后面就不用判断是哪个线程了
        while (true) {
            String name = Thread.currentThread().getName();
            synchronized (lock) {
                if (arr.isEmpty()) {
                    System.out.println(name+boxList);
                    break;
                } else {
                    int prize=arr.remove(random.nextInt(arr.size()));
                    boxList.add(prize);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //返回最大值
        return boxList.isEmpty()?null:Collections.max(boxList);
    }
}



