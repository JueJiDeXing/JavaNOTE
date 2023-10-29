package 多线程;

import java.util.concurrent.*;

public class 线程池 {
    //线程复用,创建线程用完后存放线程,这样就不用再次创建
    //如果提交任务后线程池里没有空余线程,则创建线程,如果创建线程数量已达到线程池数量上限,则任务排队等待
    public static void main(String[] args) {
        //创建线程池
        //线程池工具类Executors
        //newCachedThreadPool([ThreadFactory tf])
        //newFixedThreadPool(int n [,ThreadFactory tf])
        ExecutorService Pool1 = Executors.newCachedThreadPool();//创建没有上限的线程池
        ExecutorService Pool2 = Executors.newFixedThreadPool(5);//有上限
        //提交任务
        //sbumit(Runnable task [,Object result])
        //sbumit(Callable task)
        Pool1.submit(new MyRunnable5());//1
        Pool1.submit(new MyRunnable5());//2
        Pool1.submit(new MyRunnable5());//3
        //所有任务执行完毕,关闭线程池(一般不会关闭,因为服务器24小时工作)
        Pool1.shutdown();
        Pool2.shutdown();

        //自定义线程池ThreadPoolExecutor-------------------------
        //核心线程(永不销毁),临时线程,消息队列
        //临时线程:核心线程与消息队列都排满时创建,超过空闲时间不工作则销毁
        /*参数:
        * 核心线程数量(大于0),
        * 最大线程数量(大于等于核心线程数),
        * 空闲时间(值),
        * 空闲时间(单位,使用TimeUnit),
        * 阻塞队列(非空),
        * 创建线程的方式(非空),
        * 执行任务过多的解决方案(非空)
        * */

        //任务拒绝策略 (内部类)
        //ThreadPoolExecutor.AbortPolicy 默认策略,丢弃任务抛出异常
        //ThreadPoolExecutor.DiscardPolicy 丢弃任务不抛出异常
        //ThreadPoolExecutor.DiscardOldestPolicy 丢弃队列中等待最久的任务,把当前任务加到队列中
        //ThreadPoolExecutor.CallerRunsPolicy 调用任务的run()方法,绕过线程池直接执行

        ThreadPoolExecutor pool1=new ThreadPoolExecutor(
                3,
                6,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        /*例:
        核心线程=3,临时线程=6-3,消息队列=3
        提交3个任务:创建3个核心线程
        提交5个任务:创建3个核心线程执行前3个任务,剩余2个任务放入消息队列等待
        提交8个任务:创建3个核心线程执行前3个任务,再将3个任务放入消息队列等待,再创建2个临时线程执行剩下的任务
        提交任务大于线程池最大容量时触发任务拒绝策略,多出的任务舍弃不执行
         */
        pool1.submit(new MyRunnable5());

        pool1.shutdown();
        //线程池大小计算-----------------------------------------
        //CPU密集型运算:最大并行数+1
        //   防止线程出问题,拿出一个候补线程
        //IO密集型运算:最大并行数*期望CPU利用率* 总时间(CPU计算时间+等待时间)/CPU计算时间
        //   CPU等待时间:从硬盘读取数据  计算时间:数据处理
        //   使用thread dump计算CPU的两个时间

        //最大并行数:4核8线程,8核16线程,利用超线程技术,一个处理器可以执行2个线程
        //Runtime.getRuntime().availableProcessors();获取虚拟机可用处理器数目
        System.out.println(Runtime.getRuntime().availableProcessors());//线程数为16,则最大并行数为16





    }
}

class MyRunnable5 implements Runnable {
    @Override
    public void run() {

    }
}
