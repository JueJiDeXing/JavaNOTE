package IO流;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hutool_io {
            /*
            IOUtil
                int copy(InputStream input,OutputStream out)复制文件
                int copyLarge(Reader input,Writer output)复制大文件
                String readLines(Reader input)读取数据
                write(String data,OutputStream output)写出数据

            FileUtil
                file()根据参数创建file对象
                touch()根据参数创建文件
                copy()拷贝

                writeLines()集合数据写出到文件(覆盖)
                appendLines()集合数据写出到文件(追加)
                readLines()读取文件数据到集合(一行数据为一个元素)
                readUtf8Lines()utf-8读取


            FileTypeUtil

            WatchMonitor

             ClassPathResource

             FileReader

             FileWriter
         */
            public static void main(String[] args) {
                File file = FileUtil.file("D:\\", "aaa","b.txt");
                File touch=FileUtil.touch(file);

                ArrayList<String>arrayList=new ArrayList<>();
                Collections.addAll(arrayList,"aaa","awad");
                File file1 = FileUtil.writeLines(arrayList, file, "UTF-8", true);

                List<String> list = FileUtil.readLines(file, "UTF-8");
            }

}
