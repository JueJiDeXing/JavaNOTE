package MyNote.IO流;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IO流_字节 {
    //                       IO流
    //           ┌────────────┴───────────┐
    //          字节流                     字符流
    //        (所有类型文件)              (纯文本文件)
    //     ┌────────┴────────┐
    // InputStream     OutputStream
    // (字节输入流)       (字节输出流)
    //    读                 写

    public static void main(String[] args) throws IOException {
        // 字符输出流FileOutputStream------
        // 构造方法 参数1:路径或File对象  参数2:是否追加(默认false)
        // 文件不存在则创建新文件,但需要保证父级路径是存在的,关闭追加会清空文件内容
        FileOutputStream fos = new FileOutputStream("NOTE\\src\\IO流\\fff.txt");
        // write(int b)一次写入一个字节数据
        // write(byte[] b)一次写入一个字节数组数据
        // write(byte[] b,int off,int len)一次写入一个字节数组的部分数据
        byte[] bytes = {97, 98, 99, 100, 101, 102};
        fos.write(97);//ASCII,写入字符a
        fos.write(bytes, 1, 3);//追加写入bcd->文件abcd

        //换行:
        //windows:\r\n  java底层有优化,只写其中一个可以自动补全
        //linux:\n   mac:\r
        String str = "\r\njuejidexing";
        fos.write(str.getBytes());//获取字符串的字节数组

        fos.close();//释放资源

        // 字符输入流FileInputStream--------
        // 构造方法 参数:路径或File对象
        // 文件不存在,报错
        FileInputStream fis = new FileInputStream("NOTE\\src\\IO流\\fff.txt");
        // read()读取一个字符,读取后光标移动,没有读取到字符返回-1
        // read(byte[] buffer)一次读取字节数组,每次读取都会尽可能把数组装满,返回读取到的字节数
        // read(byte[] buffer,int off,int len)一次读取字节数组的一部分
        int b = fis.read();//读取第一个字符的ASCII 97 ,
        System.out.println((char) b);//a
        while ((b = fis.read()) != -1) {
            System.out.println((char) b);
        }
        fis.close();

        //文件拷贝------------
        FileInputStream fis2 = new FileInputStream("NOTE\\src\\IO流\\fff.txt");
        FileOutputStream fos2 = new FileOutputStream("NOTE\\src\\IO流\\拷贝.txt");
        byte[] bytes1 = new byte[5];//一次读取5个字节
        //弊端:每次读取是覆盖操作,如果最后读取到的数据字节数小于5,后面的几个字节会保留
        int len;
        while ((len = fis2.read(bytes1)) != -1) {
            fos2.write(bytes1, 0, len);//解决弊端
        }
        fos2.close();
        fis2.close();//栈,先开后关

        //MyNote.异常处理
        FileInputStream fis3 = null;
        FileOutputStream fos3 = null;
        try {
            fis3 = new FileInputStream("NOTE\\src\\IO流\\fff.txt");
            fos3 = new FileOutputStream("NOTE\\src\\IO流\\拷贝.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos3 != null) {
                try {
                    fos3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis3 != null) {
                try {
                    fis3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //异常处理优化
        //接口AutoCloseable,自动释放资源
        /*
         * JDK7
         * try(创建流对象){//有多个对象要以分号隔开
         * }catch(){
         * }
         * JDK9
         * 创建流对象  //throws异常抛出
         * try(流对象){//有多个对象要以分号隔开
         * }catch(){
         * }
         * */
        FileInputStream fis4 = new FileInputStream("NOTE\\src\\IO流\\fff.txt");
        FileOutputStream fos4 = new FileOutputStream("NOTE\\src\\IO流\\拷贝.txt");
        try (fis4; fos4) {
            System.out.println("try");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
