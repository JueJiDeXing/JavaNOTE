package J_API;

import java.io.IOException;

public class J_Runtime {
    //系统
    public static void main(String[] args) throws IOException {
        //Runtime getRuntime()当前系统的运行环境对象
        Runtime r1=Runtime.getRuntime();
        Runtime r2=Runtime.getRuntime();//r1与r2为同一个对象,因为Runtime只有一个对象,表示当前虚拟机的运行环境

        //void exit(int status)关闭虚拟机
        // r1.exit(0);
        // Runtime.getRuntime().exit(0);

        //int availableProcessors()获取CPU线程数
        System.out.println(r1.availableProcessors());//16
        //long maxMemory()JVM能从系统获取的总内存大小(byte)
        System.out.println(r1.maxMemory()/1024/1024);//3550M
        //long totalMemory()JVM已经从系统获取的总内存大小,/JVM剩余内存大小(byte)
        System.out.println(r1.totalMemory()/1024/1024);//222M
        //Process exec(String command)运行cmd命令
        r1.exec("qq");//打开qq,exec报错可以添加异常方法到签名
        /*cmd命令:
        * 关机shutdown:
        *   -s:默认1分钟
        *   -s -t time:指定时间(单位:s)
        *   -a:取消关机操作
        *   -r:关机重启*/
    }
}
