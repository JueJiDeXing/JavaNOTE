package MyNote.集合;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 不可变集合 {
    //创建之后不允许修改内容的集合
    public static void main(String[] args) {
        //file操作
        //static<E>List<E> .of(E...e)创建一个具有指定元素的List集合
        //static<E>Set<E> .of(E...e)创建一个具有指定元素的Set集合
        //static<K,V>Map<K,V> .of(E...e)创建一个具有指定元素的Map集合(参数最多10对)
        //static<K,V>Map<K,V> .ofEntries(Map.Entry[] arr)超过10对可使用
        List<String> list=List.of("a","b","c");
        System.out.println(list.remove(1));//运行报错

        //创建不可变Map-----
        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("a","1");
        hashMap.put("b","2");
        hashMap.put("c","3");
        hashMap.put("d","4");
        hashMap.put("e","5");
        hashMap.put("f","6");
        hashMap.put("g","7");
        hashMap.put("h","8");
        hashMap.put("i","9");
        hashMap.put("j","10");
        hashMap.put("k","11");
        hashMap.put("l","12");

        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        Map.Entry[] arr1=new Map.Entry[0];//创建长度为0的entry数组
        Map.Entry[]arr2=entries.toArray(arr1);//集合转数组
        Map map=Map.ofEntries(arr2);//不可变的map集合
        Map<Object, Object> map2 = Map.ofEntries(hashMap.entrySet().toArray(new Map.Entry[0]));

        Map<String, String> map3 = Map.copyOf(hashMap);//copyOf可将集合转为不可变集合
    }
}
