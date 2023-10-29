package 网络编程.UDP的三种通信方式.组播;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class 接1 {
    public static void main(String[] args) throws IOException {
        MulticastSocket ms=new MulticastSocket(11451);
        //将当前本机加入组播的组当中
        InetAddress address=InetAddress.getByName("224.0.0.1");
        ms.joinGroup(address);
        //创建数据包对象
        byte[]bytes=new byte[1024];
        DatagramPacket dp=new DatagramPacket(bytes,bytes.length);
        //接收数据
        ms.receive(dp);
        //解析数据
        byte[]data=dp.getData();
        int len=dp.getLength();
        String ip=dp.getAddress().getHostAddress();
        String name=dp.getAddress().getHostName();
        System.out.println("ip为:"+ip+",主机名为:"+name+"的人,发送了数据:"+new String(data,0,len));
        ms.close();

    }
}
