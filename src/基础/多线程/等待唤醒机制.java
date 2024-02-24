package 基础.多线程;

import java.util.concurrent.ArrayBlockingQueue;

public class 等待唤醒机制 {
    public static void main(String[] args) {
        //生产者与消费者
        //实现线程轮流交替执行效果
        //lock.wait()  lock.notifyAll()
        Cook c = new Cook();
        Foodie f = new Foodie();
        c.setName("厨师");
        f.setName("吃货");
        c.start();
        f.start();

        //阻塞队列
        //take()  put()
        //阻塞队列的继承实现关系
        //接口:iterable,Collection,Queue,BlockingQueue
        //实现类:ArrayBlockingQueue(底层是数组,有界),LinkedBlockingQueuq(底层是链表,伪无界)
        //等待唤醒的线程必须使用同一个阻塞队列
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);//容量为1
        Cook2 c2 = new Cook2(queue);
        Foodie2 f2 = new Foodie2(queue);


    }
}

class Cook extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {//不能再吃了,退出循环
                    break;
                } else {
                    if (Desk.foodFlag == 0) {
                        //没有食物.运行
                        Desk.foodFlag = 1;
                        Desk.lock.notifyAll();//唤醒
                        System.out.println("已做好食物,可以吃了");
                    } else if (Desk.foodFlag == 1) {
                        //有食物,等待
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        }
    }

}

class Foodie extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {//执行十次
                    break;
                } else {
                    if (Desk.foodFlag == 0) {
                        //没有食物,等待
                        try {
                            Desk.lock.wait();//让当前线程与锁绑定
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (Desk.foodFlag == 1) {
                        //有,运行
                        Desk.count--;
                        Desk.foodFlag = 0;
                        System.out.println("还能吃" + Desk.count + "碗");
                        //唤醒厨师
                        Desk.lock.notifyAll();//唤醒锁的所有线程
                    }
                }
            }
        }
    }
}

class Desk {
    //控制生产者与消费者的执行
    public static int foodFlag = 0;//是否有食物(需求可能变更为更多线程,所有用int表示)
    public static int count = 10;//执行次数
    public static final Object lock = new Object();
}

class Cook2 extends Thread {
    ArrayBlockingQueue<String> queue;

    public Cook2(ArrayBlockingQueue<String> queue) {
        this.queue = queue;//传递阻塞队列
    }

    @Override
    public void run() {
        while (true) {
            try {
                //向队列中放入数据,如果队列装满,则等待
                queue.put("面条");//put底层会加锁
                System.out.println("厨师放了一碗面条");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }
}

class Foodie2 extends Thread {
    ArrayBlockingQueue<String> queue;

    public Foodie2(ArrayBlockingQueue<String> queue) {
        this.queue = queue;//传递阻塞队列
    }

    @Override
    public void run() {
        while (true) {
            try {
                //从队列中取出数据,如果队列没有数据,则等待
                String food = queue.take();
                System.out.println("拿到:"+food);
                //注:打印语句位于锁的外面,打印可能会有重复,但是对数据没有影响
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
