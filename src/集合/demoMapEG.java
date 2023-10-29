package 集合;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class demoMapEG {
    public static void main(String[] args) {
        HashMap<String,Integer>map=new HashMap<>();
        int count_A=0;
        int count_B=0;
        int count_C=0;
        int count_D=0;
        String[]arr={"A","D","C","B","A","C","A","B","D","B","A","A","C","D","B","C"};
        for (String s :arr){
            switch (s){
                case "A"->map.put("A",++count_A);
                case "B"->map.put("B",++count_B);
                case "C"->map.put("C",++count_C);
                case "D"->map.put("D",++count_D);
                default -> System.out.println("没有这个选项");
            }
        }
        System.out.println(map);//{A=5, B=4, C=4, D=3}

        int max=0;
        Set<Map.Entry<String,Integer>>entries=map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            int count=entry.getValue();
            if (count>max){
                max=count;//记录最大值
            }
        }
        for (Map.Entry<String, Integer> entry : entries) {
            int count=entry.getValue();
            if (count==max){
                System.out.println(entry.getKey());//获取最大值的键
            }
        }

    }
}
