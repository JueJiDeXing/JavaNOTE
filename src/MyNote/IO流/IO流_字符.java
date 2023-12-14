package MyNote.IO流;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class IO流_字符 {
    //ASCII码表:字节是计算机存储的最小单元,1个字节8个比特位,0~127
    //字符集:
    //GB2312-80:国家标准,版本2312,1980年颁布
    //BIG5:台湾地区繁体中文
    //GBK:GB扩展包含GB13000-1的全部中日韩文字,以及BIG5
    //    windows系统默认使用GBK,为了统一不同版本,系统显示为ANSI
    //Unicode字符集:国际标准字符集,万国码


    //GBK完全兼容ASCII,一个字母占一个字节,一定以0开头
    // GBK汉字占两个字节,高位字节一定以1开头(高位转十进制后一定是负数,低位不一定)

    //Unicode
    // 编码格式UTF-8:使用1~4个字节保存
    // ASCII一个字节     0x
    // 阿拉伯文等两个字节  110x   10x
    // 中文三个字节       1110x  10x 10x
    // 其他语言四个字节    11110x 10x 10x 10x
    //UTF-16:使用2~4个字节保存
    //UTF-32:使用4个字节保存

    public static void main(String[] args) throws IOException {
        //编码与解码
        //byte[] getBytes()按默认方式utf-8编码
        //byte[] getBytes(String charsetName)按指定方式编码
        //String(byte[] bytes )按默认方式解码
        //String(byte[] bytes,String charsetName)按指定方式解码
        String str = "你好";
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));//[-28, -67, -96, -27, -91, -67]
        byte[] bytes1 = str.getBytes("GBK");
        System.out.println(Arrays.toString(bytes1));//[-60, -29, -70, -61]

        System.out.println(new String(bytes));//你好
        System.out.println(new String(bytes, "GBK"));//浣犲ソ
        //           IO流
        //   ┌────────┴────────┐
        //  字节流             字符流
        //                  (纯文本文件)
        //            ┌───────┴───────┐
        //          Reader          Writer
        //        (字符输入流)      (字符输出流)
        //字符流=字节流+字符集

        //FileReader--------------
        //构造方法,参数:FIle对象或路径
        FileReader fr = new FileReader("NOTE\\src\\IO流\\fff2.txt");
        //int read()按字节读取,遇到中文一次读取多个字节
        //   读取后解码返回十进制整数,未读取到数据返回-1
        //int read(char[] buffer)读取多个数据,读取后解码返回十进制整数,转为字符后放入字符数组
        //   空参read+强制类型转换
        int ch;
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }
        fr.close();
        FileReader fr2 = new FileReader("NOTE\\src\\IO流\\fff2.txt");
        char[] chars = new char[2];
        int len;
        while ((len = fr2.read(chars)) != -1) {
            System.out.println(new String(chars,0,len));
        }
        fr2.close();

        //FileWriter---------
        //构造方法
        //参1:File对象或路径,可选参2:append,是否追加写
        //成员方法
        //write(int c)写入一个字符(根据当前字符集编码格式编码后写入)
        //write(String str)写入一个字符串
        //write(String str,int off,int len)写入一个字符串的一部分
        //write(char[] cbuf)写入一个字符数组
        //write(char[] cbuf,int off,int len)写入一个字符数组的一部分

        //字符流原理解析---------------------------------------------
        //创建输入流对象时,在内存中创建一个长度为8192的字节数组缓冲区(字节流没有缓冲区)
        //每次读取时先判断缓冲区中是否有数据可以被读取
        //如果没有,则从文件中读取,尽可能装满缓冲区(从数组索引0处开始覆盖),然后从缓冲区读取数据
        //如果有,直接从缓冲区读取数据
        //如果文件中也没有数据了,返回-1
        //FileWriter会清空文件,但不会清空缓存区
        FileReader fr1=new FileReader("NOTE\\src\\IO流\\fff2.txt");
        fr1.read();
        //清空文件
        FileWriter fw1=new FileWriter("NOTE\\src\\IO流\\fff2.txt");
        fr1.read();//能够读取缓冲区的数据

        //创建输出流对象时,在内存中创建一个长度为8192的字节数组缓冲区(字节流没有缓冲区)
        //每次写入时先判断缓冲区是否装满
        //如果装满了则把缓冲区内容写入到目的地文件,没有则继续写入缓冲区
        //缓冲区装满,或手动flush刷新,或close关闭文件时,会把缓冲区写入文件
    }
}
