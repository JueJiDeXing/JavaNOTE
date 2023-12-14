package MyNote.IO流;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Commons_io {
    //使用方法:创建lib,jar包粘贴至lib文件夹,点击Add as Library
    public static void main(String[] args) throws IOException {
        /*
        FileUtils类
            copyFile(File srcFile,File destFile)复制文件
            copyDirectory(File srcDir,File destDir)复制文件夹(拷贝文件夹下的所有文件)
            copyDirectoryToDirectory(File srcDir,File destDir)复制文件夹(拷贝整个文件夹)
            deleteDirectory(File directory)删除文件夹
            cleanFile(File directory)清空文件夹
            String readFileToString(File file,Charset encoding)读取文件数据
            write(File file,CharSequence data,Charset encoding)写出数据

         IOUtils类
            int copy(InputStream input,OutputStream out)复制文件
            int copyLarge(Reader input,Writer output)复制大文件
            String readLines(Reader input)读取数据
            write(String data,OutputStream output)写出数据
         */
        FileUtils.copyFile(new File(""), new File(""));

    }
}
