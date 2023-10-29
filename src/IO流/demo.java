package IO流;

import java.io.*;
import java.util.Arrays;

public class demo {

    public static void main(String[] args) throws IOException {
        //拷贝文件夹------------
        File src=new File("NOTE\\src\\demo");
        File dest=new File("NOTE\\src\\拷贝demo");
        //method1(src,dest);

        //文件内容排序---------------
        //2-1-9-4-8-7
        File src2=new File("NOTE\\src\\IO流\\fff3.txt");
        method2(src2);


    }

    private static void method2(File src2) throws IOException {
        //读
        FileInputStream fis=new FileInputStream(src2);
        int b;
        StringBuilder stringBuilder=new StringBuilder();
        while ((b= fis.read())!=-1){
            stringBuilder.append((char)b);
        }
        fis.close();

        //排
        Integer[]list = Arrays.stream(stringBuilder.toString().split("-"))
                .map(Integer::parseInt)
                .sorted()
                .toArray(Integer[]::new);

        //写
        FileWriter fw=new FileWriter(src2);
        for (int i=0;i<list.length;i++) {
            if (i == list.length- 1) {
                fw.write(list[i] + "");
            } else {
                fw.write(list[i] + "-");
            }
        }
        fw.close();
    }

    public static void method1(File src,File dest) throws IOException {
        dest.mkdirs();
        File[] files = src.listFiles();
        for (File f:files){
            if (f.isFile()){
                FileInputStream fis=new FileInputStream(f);
                FileOutputStream fos=new FileOutputStream(new File(dest,f.getName()));
                byte[]bytes=new byte[5];
                int len;
                while ((len=fis.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                }
                fos.close();
                fis.close();
            }
            else {
                method1(f,new File(dest,f.getName()));
            }
        }
    }
}
