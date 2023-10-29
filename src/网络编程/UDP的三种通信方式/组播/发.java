package 网络编程.UDP的三种通信方式.组播;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class 发 {
    //组播:向一组设备发送信息
    // 组播地址:224.0.0.0~239.255.255.255
    // 其中224.0.0.0~224.0.0.255为预留的组播地址

    //广播:向局域网的所有电脑发送信息, 广播地址,255.255.255.255

    public static void main(String[] args) throws IOException {
        //指定组播地址(接收端只有加入该组播地址才能接收到数据)
        InetAddress address=InetAddress.getByName("224.0.0.1");
        //MulticastSocket MulticastSocket([int port][,SocketAddress bindAddress]);
        MulticastSocket ms=new MulticastSocket();
        //打包数据
        String str="你好你好";
        byte[]bytes=str.getBytes();
        int port=11451;
        DatagramPacket dp=new DatagramPacket(bytes,bytes.length,address,port);
        //发送
        ms.send(dp);
        ms.close();

    }
}
