package IO流;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class file操作 {
    //File->路径/文件,传入的路径可以是不存在的
    public static void main(String[] args) throws IOException {
        //构造方法-------------------------------------------------
        //File(String path)
        String str = "E:\\ideaProject\\untitled\\NOTE\\src\\IO流\\fff.txt";
        File f1 = new File(str);
        //File(String parent,String child)父路径+子路径
        String str1 = "E:\\ideaProject\\untitled\\NOTE\\src\\IO流";
        String str2 = "fff.txt";
        File f2 = new File(str1, str2);
        //File(File parent,String child)目录+子路径
        File f3 = new File(new File(str1), str2);

        //System.out.println(f1);
        //System.out.println(f2);
        System.out.println(f3);

        //成员方法-----------------------------------------------
        //1.判断与获取
        //boolean .isDirectory()判断是否为文件夹(不存在返回false)
        //boolean .isFile()判断是否为文件(不存在返回false)
        //boolean .exists()判断该路径是否存在
        //long .length()返回文件字节数(获取文件夹大小需要遍历文件)
        //String .getAbsolutePath()返回文件绝对路径
        //String .getPath()返回定义文件时(new File)使用的路径
        //String .getName()返回文件(或文件夹)名  (文件名带后缀)
        //long lastModified()返回文件最后修改时间(单位毫秒)

        //2.创建,删除
        // 创建文件如果文件已存在,返回false  如果父级路径不存在,报错
        //boolean .createNewFile()创建新的空文件,可以创建没有后缀名的文件
        File file1 = new File("NOTE\\src\\IO流\\不存在的文件.txt");
        boolean b = file1.createNewFile();
        //boolean .mkdir()创建单级文件夹
        //boolean .mkdirs()创建多级文件夹(也可以创建单级)
        //boolean .delete()删除文件,或空文件夹(不走回收站,不能删除非空文件夹),删除成功返回true
        //删除多级文件夹需要递归遍历

        //3.获取遍历
        //File[] .listFiles()获取文件夹下的所有文件
        //路径不存在,或路径是一个文件,或有权限才能访问时(C盘)返回null
        //空文件夹返回长度为0的数组
        //非空文件夹把所有文件(包括隐藏文件)都放入File数组中返回
        File file3 = new File("NOTE\\src\\IO流");
        File[] files = file3.listFiles();
        System.out.println(Arrays.toString(files));
        for (File file : files) {
            System.out.println(file);
        }

        //static File[] listRoots()列出可用的文件系统根(盘符)
        //String[] list()获取路径下所有内容(获取文件名,不是路径)
        //String[] list(FilenameFilter filter)利用文件名过滤器获取路径下所有内容
        //File[] .listFiles()获取文件夹下的所有文件
        //File[] .listFiles(FileFilter filter)利用文件名过滤器获取路径下所有内容
        //File[] .listFiles(FilenameFilter filter)利用文件名过滤器获取路径下所有内容
        File file3_ = new File("NOTE\\src\\IO流");
        File[] files_ = file3_.listFiles(new FilenameFilter() {
            //FileFilter()的accept参数是完整路径
            @Override
            public boolean accept(File dir, String name) {
                //参1:父级路径,参2:子级路径
                //返回值:true则放入数组,false则舍弃
                File src = new File(dir, name);
                if (src.isFile() && name.endsWith(".txt")) {
                    return true;
                }
                return false;//过滤出txt文件
            }
        });
        System.out.println(Arrays.toString(files_));

        //递归遍历
        File file4 = new File("NOTE\\src\\IO流");
        ArrayList<File> fileArrayList = method(file4);
        System.out.println(fileArrayList);
    }

    private static ArrayList<File> method(File file) {
        File[] files = file.listFiles();
        ArrayList<File> filesList = new ArrayList<>();
        if (files == null) {
            return null;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                method(f);//递归遍历
            } else {
                //操作文件
                filesList.add(f);
            }
        }

        return filesList;
    }

}