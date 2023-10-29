package 网络编程.UDP的三种通信方式.单播;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class 发 {
    //单播:一对一传输
    public static void main(String[] args) throws IOException {
        //InetAddress获取IP----------------------
        //InetAddress getByName(String host)获取IP对象,可输入主机名name或Ip地址
        //InetAddress[] getAllByName(String host)确定主机的IP地址
        //InetAddress getByAddress(Byte[] add)确定主机的IP地址
        //InetAddress getAllByName(String host,Byte[] add)确定主机的IP地址
        //String getHostName()获取此IP地址的主机名
        //String getHostAddress()返回文本显示中的IP地址字符串
        InetAddress address= InetAddress.getByName("LinJiangXian");//底层会判断主机是IPv4还是IPv6
        System.out.println(address);// LinJiangXian/192.168.0.107
        String name = address.getHostName();
        String ip = address.getHostAddress();
        System.out.println(name+"/"+ip);// LinJiangXian/192.168.0.107

        //DatagramSocket发送数据------------------------------------
        //DatagramSocket DatagramSocket()空参,随机绑定一个可用端口去发送数据
        //DatagramSocket DatagramSocket(int port [,InetAddress add])指定端口号去发送数据
        DatagramSocket ds=new DatagramSocket();
        //DatagramPacket发送数据包
        // DatagramPacket DatagramPacket(byte[] buf [,int offset],int length,
        // InetAddress add,int port)//需要指定发送到哪个IP的哪个端口
        byte[]bytes="你好".getBytes();
        int port=10086;
        DatagramPacket dp=new DatagramPacket(bytes,bytes.length,address,port);//打包数据
        ds.send(dp);//发送数据
        ds.close();//释放资源
    }
}
