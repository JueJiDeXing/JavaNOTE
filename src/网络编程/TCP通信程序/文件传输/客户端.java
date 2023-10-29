package 网络编程.TCP通信程序.文件传输;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class 客户端 {
    //UUID.randomUUID()//返回随机ID,例:df94a3f1-c2be-474f-99f6-2c03123903b8
    public static void main(String[] args) throws IOException {
        InetAddress address=InetAddress.getByName("LinJiangXian");
        int port=14514;
        Socket socket=new Socket(address,port);
        //读取本地文件 //传给服务器
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream("NOTE\\\\src\\网络编程\\TCP通信程序\\文件传输\\WeChatAccount.jpg"));
        BufferedOutputStream bos=new BufferedOutputStream(socket.getOutputStream());
        byte[]bytes=new byte[1024];
        int len;
        System.out.println("开始上传文件");
        while ((len=bis.read(bytes))!=-1){//--------
            bos.write(bytes,0,len);
        }
        bis.close();
        bos.flush();
        socket.shutdownOutput();
        //接收服务器回复
        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = br.readLine();
        System.out.println(s);
        //释放资源
        br.close();
        socket.close();
    }
}
