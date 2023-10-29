package 网络编程.TCP通信程序;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class 客户端 {
    //Socket客户端->输出流->输出流->ServerSocket服务端
    public static void main(String[] args) throws IOException {
        /* URL url = new URL("https://api.ipify.org"); // 使用ipify提供的API获取公网IP
        System.out.println(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        System.out.println(reader);
        String ipAddress = reader.readLine();
        System.out.println("Public IP Address: " + ipAddress);
        */
        InetAddress address = InetAddress.getByName("120.206.175.106");//服务器IP
        System.out.println(address);
        System.out.println(address.getHostName());
        System.out.println(address.getHostAddress());
        int port = 11451;//服务器端口
        //客户端--------------------------------
        //创建客户端的Socket对象,与指定服务端连接
        //Socket Socket(Proxy proxy)
        //Socket Socket(String host/InetAddress add,int port)
        Socket socket = new Socket(address, port);//如果连不上则报错,需要先运行服务端
        System.out.println("客户端已连接服务器");
        /*三次握手协议保证连接建立*/
        //获取输出流
        OutputStream os = socket.getOutputStream();//获取连接通道的输出流
        //写出数据
        Scanner sc = new Scanner(System.in);
        System.out.println("发送什么信息给服务器:");
        String str = sc.nextLine();
        os.write(str.getBytes());
        System.out.println("信息已发送");
        //结束标记
        socket.shutdownOutput();//断开连接通道的输出流
        //接收服务器回复
        InputStream is =socket.getInputStream();//字节输入流
        InputStreamReader isr = new InputStreamReader(is);//转换流,防止读取中文乱码
        BufferedReader br = new BufferedReader(isr);//缓冲流,提高读取效率
        int b;
        while ((b = br.read()) != -1) {
            System.out.print((char) b);//你好
        }

        //释放资源
        socket.close();
        /*四次挥手协议断开连接,而且保证连接通道的数据都处理完毕*/
    }
}
