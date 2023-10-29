package IO流;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class IO_高级流 {
    //                           IO流
    //               ┌────────────┴──────────────┐
    //            字节流                         字符流
    //      ┌────────┴────────┐           ┌───────┴───────┐
    //  InputStream     OutputStream    Reader          Writer
    //
    // 缓冲流:BufferedInputStream,BufferedOutputStream,BufferedReader,BufferedWriter
    // 转换流:InputStreamReader,OutputStreamWriter
    // 序列流:ObjectOutputStream,ObjectInputStream
    // 打印流:PrintStream,PrintWriter
    // 压缩流:ZipInputStream,ZipOutputStream
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //缓冲流-----------------------------------------------------------
        //BufferedInputStream(InputStream is)字节缓冲流,8k的字节数组缓冲区,使效率提高
        //BufferedOutputStream(OutputStream os)
        //BufferedReader(FileReader fr)字符缓冲流,8k的字符数组(效率提升不明显,但有两个特有方法)
        //BufferedWriter(FileWriter fw)
        //字符缓冲流特有方法
        //String readLine()读取一行数据,没有数据读了返回null
        //void newLine()输出流换行输出(可跨系统的换行)

        BufferedWriter bw = new BufferedWriter(new FileWriter(""));
        bw.write("aaa");
        bw.newLine();//对不同的系统都能换行
        bw.write("bbb");

        BufferedReader br = new BufferedReader(new FileReader(""));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        //转换流-------------------------------------------------------------
        //字符流与字节流的桥梁
        // 作用1:指定字符集读写(淘汰,使用FileReader)  作用2:给字节流提供字符流的方法
        //InputStreamReader
        //OutputStreamWriter

        //例:读取GBK
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream("gbk.txt"), "GBK"
        );
        int ch;
        while ((ch = isr.read()) != -1) {
            System.out.println((char) ch);
        }
        isr.close();
        //替换方案
        FileReader fr = new FileReader("gbk.txt", Charset.forName("GBK"));
        int ch2;
        while ((ch2 = fr.read()) != -1) {
            System.out.println((char) ch2);
        }
        fr.close();
        //序列流-----------------------------------
        // 对象的写入与写出
        // ObjectOutputStream(OutputStream out)输出流包装为序列化流
        // writeObject(Object obj)把对象写入文件,该对象类需要实现Serializable接口
        Student3 student = new Student3();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(""));
        oos.writeObject(student);
        oos.close();
        //反序列化流
        // ObjectInputStream(InputStream in)输入流包装为反序列化流
        // Object readObject()每次读取一个对象,没有读取到对象会报错
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(""));
        Object o = ois.readObject();
        System.out.println(o);
        ois.close();
        //序列流会根据对象的属性计算出一个序列号(版本号),如果写入后修改类(如增加一个属性)序列号改变,读取时会报错
        //固定版本号: private static final long serialVersionUID=1L;可在设置里设置自动添加
        //transient瞬态关键字,使属性不可序列化

        //对于多个对象,写入对象时一般放入集合中ArrayList,直接序列化集合
        //读取时,读取集合,然后遍历集合取出对象

        //打印流-------------------------------------------------
        //只能操作文件目的地,不能操作数据源
        //特有的写出方法,数据原样写出到文件,并且可以实现自动刷新和换行
        /*字节打印流构造方法参数:PrintStream
        1. (OutputStream/File/String)关联文件
        2. (String fileName,Charset charset)关联文件并指定字符编码
        3. (OutputStream out,boolean autoFlush [,String encoding])自动刷新,字节打印流没有缓冲区开不开都一样
         */
        /*字符打印流构造方法:PrintWriter
        1. (Write/File/String)
        2. (String fileName,Charset charset)
        3. (Write w,boolean autoFlush)
        4. (OutputStream out,boolean autoFlush,Charset chars et)
         */
        PrintStream ps = new PrintStream(new FileOutputStream(""), true, StandardCharsets.UTF_8);
        /*打印流成员方法write(int b)
        特有方法:
        println()打印任意数据,自动刷新,自动换行
        print()不换行
        printf(String format,Object...args)占位符打印,不换行
        %n换行,%s字符串,%c字符,%d数字,%b布尔,%x十六进制,%o八进制,%f浮点
         */
        ps.print("你好");
        ps.close();

        //打印流应用场景
        //System.out.println();
        PrintStream printStream = System.out;//特殊打印流,默认指向控制台(标准输出流,不能关闭!)
        printStream.println(123);

        //压缩流--------------------------------------------------------
        //Java只能识别zip压缩包
        //解压本质:把每一个文件(ZipEntry对象)按照层级拷贝至另一个文件夹
        File src1 = new File("D:\\a.zip");
        File dest1 = new File("D:\\a");
        unzip(src1, dest1);
        //压缩本质:把每一个文件(ZipEntry对象)放至压缩包中
        File src2 = new File("D:\\AAA");
        File dest2 = new File(src2.getParentFile(),src2.getName()+".zip");
        ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(dest2));
        toZip(src2,zos,src2.getName());
        zos.close();



    }
    /*
    * 数据源
    * 目的地
    * 压缩包里的路径
    * */
    private static void toZip(File src, ZipOutputStream zos,String name) throws IOException {
        File[]files=src.listFiles();
        for (File file:files){
            if (file.isFile()){
                ZipEntry zipEntry =new ZipEntry(name+"\\"+file.getName());//压缩包里面的路径
                zos.putNextEntry(zipEntry);
                FileInputStream fis=new FileInputStream(file);
                int b;
                while ((b=fis.read())!=-1){
                    zos.write(b);
                }
                fis.close();
                zos.closeEntry();
            }else{
                toZip(file,zos,name+"\\"+file.getName());
            }

        }

    }

    private static void unzip(File src, File dest) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(src));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {//获取下一个ZipEntry对象(自动递进),没有文件返回null
            System.out.println(entry);
            if (entry.isDirectory()) {
                //创建文件夹
                File file = new File(dest, entry.toString());
                file.mkdirs();
            } else {
                //拷贝文件
                //读
                FileOutputStream fos = new FileOutputStream(new File(dest, entry.toString()));
                int b;
                while ((b = zis.read()) != -1) {
                    //写
                    fos.write(b);
                    fos.close();
                    zis.closeEntry();//表示压缩包里的一个文件处理完毕了
                }
            }
        }
        zis.close();

    }
}

class Student3 implements Serializable {
    @Serial
    private static final long serialVersionUID = -4250420735907564415L;
    //Serializable标记型接口
    String name;
    int age;
    transient String a;
}