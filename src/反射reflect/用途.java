package 反射reflect;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class 用途 {


    public static void main(String[] args) throws IllegalAccessException, IOException {
        Object o = new Object();
        //用途1.保存任意对象的数据
        saveObject(o);
        //用途2.与配置文件结合,动态创建对象调用方法
    }

    public static void saveObject(Object obj) throws IllegalAccessException, IOException {
        //获取字节码对象
        Class<?> clazz = obj.getClass();
        //获取所有成员变量
        Field[] fields = clazz.getDeclaredFields();
        BufferedWriter bw = new BufferedWriter(new FileWriter("fff.txt"));
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();//获取变量名
            Object value = field.get(obj);//获取变量值
            System.out.println(name + ":" + value);
            //写出数据
            bw.write(name + ":" + value);
            bw.newLine();
        }
        bw.close();
    }

    public static void config(Object obj) throws IllegalAccessException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        //读取配置文件信息
        Properties prop = new Properties();//创建集合
        FileInputStream fis = new FileInputStream("p.properties");//输入流
        prop.load(fis);//加载配置信息到集合
        fis.close();
        //获取类名和方法名
        String className = (String) prop.get("classname");
        String methodName = (String) prop.get("method");
        //创建对象并运行方法
        Class<?> clazz = Class.forName(className);
        Constructor<?> con = clazz.getDeclaredConstructor();//获取构造方法
        Object o=con.newInstance();//创建对象
        Method method = clazz.getDeclaredMethod(methodName);//获取方法
        method.setAccessible(true);//解除权限
        Object invoke = method.invoke(o);//调用方法





































































    }

}
