package 网络编程.TCP通信程序;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class 服务端 {
    public static void main(String[] args) throws IOException {
        int port =11451;//服务器监听端口
        //服务器端-------------------------------
        //创建服务器端对象,默认本机
        //ServerSocket ServerSocket(int port [,int backLog]);
        ServerSocket serverSocket = new ServerSocket(port);
        //监听客户端连接,返回Socket对象,证明连接已建立
        Socket accept = serverSocket.accept();//阻塞方法,会一直等待客户端连接
        System.out.println("已建立连接");
        //获取输入流
        InputStream is = accept.getInputStream();//字节输入流
        InputStreamReader isr = new InputStreamReader(is);//转换流,防止读取中文乱码
        BufferedReader br = new BufferedReader(isr);//缓冲流,提高读取效率
        //读取数据
        int b;
        while ((b = br.read()) != -1) {//该循环只有遇到结束标记才停止读取
            System.out.print((char) b);//你好
        }

        //回复
        String str="你好,我是绝迹的星";
        OutputStream os = accept.getOutputStream();//创建客户端对象的输出流
        os.write(str.getBytes());

        accept.shutdownOutput();

        //释放资源
        accept.close();

    }
}
