package demo.TreeMap;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        //demo1();
        //统计字符串里每个字符出现的次数,并按a(4)b(2)...的方式输出
        String str="sahvicasebqcvdbqiadbeuvbbdciajj";
        TreeMap<Character,Integer>map=new TreeMap<>();
        for (int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if (map.containsKey(c)){
                int count=map.get(c);
                map.put(c,++count);
            }
            else {
                map.put(c,1);
            }
        }
        StringBuilder stringBuilder=new StringBuilder();
        map.forEach((key,value)->{
            stringBuilder.append(key).append("(").append(value).append(")");
        });
        System.out.println(stringBuilder);
    }

    private static void demo1() {
        //存储<学生对象,籍贯>,按学生年龄排序,年龄一样按名字排序
        TreeMap<Student,String>map=new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int i=o1.age-o2.age;
                i= i==0?o1.name.compareTo(o2.name):i;
                return i;
            }
        });
        map.put(new Student("ZhanSan",20), "Beijing");
        map.put(new Student("LiSi",18), "Shanghai");
        map.put(new Student("ZhanSan",20), "Jiangsu");
        System.out.println(map);
    }
}
