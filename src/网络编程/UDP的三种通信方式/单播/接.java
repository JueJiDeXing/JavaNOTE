package 网络编程.UDP的三种通信方式.单播;

import java.io.IOException;
import java.net.*;

public class 接 {
    public static void main(String[] args) throws IOException {
        InetAddress address= InetAddress.getByName("LinJiangXian");//底层会判断主机是IPv4还是IPv6
        System.out.println(address);// LinJiangXian/192.168.0.107
        String name = address.getHostName();
        String ip = address.getHostAddress();
        System.out.println(name+"/"+ip);// LinJiangXian/192.168.0.107
        int port=10086;
        //DatagramSocket接收数据
        //一定要绑定端口,而且必须和发送端口一致
        DatagramSocket ds2=new DatagramSocket(port);
        //DatagramPacket接收数据包
        // DatagramPacket DatagramPacket(byte[] buf [,int offset],int length)
        byte[]bytes2=new byte[1024];
        DatagramPacket dp2=new DatagramPacket(bytes2,bytes2.length);
        ds2.receive(dp2);//接收数据//该方法是阻塞的,会一直等待发送
        //解析数据包
        byte[]data=dp2.getData();
        int data_length = dp2.getLength();
        InetAddress data_address = dp2.getAddress();
        int data_port = dp2.getPort();
        System.out.println("接收到数据"+new String(data,0,data_length));
        System.out.println("该数据是从"+data_address+"这台电脑的"+data_port+"端口发出的");
        ds2.close();
    }
}
