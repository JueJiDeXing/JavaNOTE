package 网络编程.TCP通信程序.文件传输;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class 服务端 {
    public static void main(String[] args) throws IOException {
        int port = 14514;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket accept = serverSocket.accept();
            System.out.println("已连接");
            ThreadPoolExecutor pool=new ThreadPoolExecutor(
                    3,
                    6,
                    60,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(3),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy()
            );
            pool.submit(new MyRunnable(accept));
        }


    }
}

class MyRunnable implements Runnable {
    Socket accept;

    public MyRunnable(Socket accept) {
        this.accept = accept;
    }

    @Override
    public void run() {
        try {
            //接收客户端文件
            BufferedInputStream bis = new BufferedInputStream(accept.getInputStream());
            String fileName = UUID.randomUUID().toString().replace("-", "");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("NOTE\\\\src\\网络编程\\TCP通信程序\\文件传输\\" + fileName + ".jpg"));
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.flush();
            bos.close();
            System.out.println("已上传");
            //回复
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            bw.write("上传成功");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {//释放资源
            if (accept!=null){
                try {
                    accept.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
