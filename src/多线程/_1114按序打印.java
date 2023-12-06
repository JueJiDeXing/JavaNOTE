package 多线程;

import java.util.concurrent.atomic.AtomicInteger;

public class _1114按序打印 {
    /*
    给你一个类：

    public class Foo {
      public void first() { print("first"); }
      public void second() { print("second"); }
      public void third() { print("third"); }
    }
    三个不同的线程 A、B、C 将会共用一个 Foo 实例。

    线程 A 将会调用 first() 方法
    线程 B 将会调用 second() 方法
    线程 C 将会调用 third() 方法
    请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。

    提示：

    尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
    你看到的输入格式主要是为了确保测试的全面性。
     */
}

class Foo {

    AtomicInteger firstJobDone = new AtomicInteger(0);
    AtomicInteger secondJobDone = new AtomicInteger(0);

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first".
        printFirst.run();
        firstJobDone.incrementAndGet();//线程1执行完毕,传递信号给线程2
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJobDone.get() != 1) {
            //等待线程1执行

        }
        // printSecond.run() outputs "second".
        printSecond.run();
        secondJobDone.incrementAndGet();//线程2执行完毕,传递信号给线程3
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJobDone.get() != 1) {
            //等待线程2执行
        }
        // printThird.run() outputs "third".
        printThird.run();
    }
}

