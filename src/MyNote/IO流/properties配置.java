package MyNote.IO流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class properties配置 {
    //文件命名格式:  xxx.properties
    //双列集合,具有Map集合特点
    //特有方法,可以把集合的数据按键值对写入文件(或从文件读取)
    //store(FileOutputStream fos,String message)
    //load(FileInputStream fis)
    public static void main(String[] args) throws IOException {
        Properties properties=new Properties();//不是泛型,可以添加任意类型数据,但一般只添加字符串型
        properties.put("username","AAA");
        properties.put("userpassword","123456");
        properties.put("count","0");
        //遍历
        for (Map.Entry<Object,Object>entry:properties.entrySet()){
            Object key=entry.getKey();
            Object value=entry.getValue();
            System.out.println(key+"="+value);
        }

        //特有方法--------------------------------
        File file=new File("NOTE\\src\\IO流\\fff.txt");
        FileOutputStream fos=new FileOutputStream(file);
        properties.store(fos,"test");//注释信息+日期(自动添加)+键值对
        /*
        #test
        #Tue Aug 15 23:04:47 CST 2023
        count=0
        username=AAA
        userpassword=123456
        */
        fos.close();
        FileInputStream fis=new FileInputStream(file);
        properties.load(fis);
        fis.close();
    }

}
