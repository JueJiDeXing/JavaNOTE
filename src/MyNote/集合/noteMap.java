package MyNote.集合;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class noteMap {
    //双列集合
    //                Map
    //   ┌─────────────┼──────────┐
    // HashMap      TreeMap    Hashtable
    //   └──LinkedHashMap        └──Properties

    public static void main(String[] args) {
        // note_Map();
        // note_HashMap();
        // note_LinkedHashMap();
        // note_TreeMap();


    }
    private static void note_Map(){
        //成员方法
        //V .put(K key,V value)添加元素,并把原键对应的值推出,如果原键不存在,返回null
        //V .remove(Object key)根据键删除元素
        //void .clear()清空
        //boolean .containsKey(Object key)判断是否包含指定键
        //boolean .containsValue(Object value)判断是否包含指定值
        //boolean .isEmpty()判断集合是否为空
        //int .size()集合长度
        //V .get(K key)根据键获取值
        //Set<K> .keySet()返回键的单列集合
        //Set<Entry<K,V>> .entrySet()返回键值对的单列集合
        Map<String,Integer>map=new HashMap<>();
        Integer t = map.put("a", 2);//null
        Integer t2 = map.put("a", 3);//2
        map.put("b", 6);
        map.put("c", 4);//键不允许重复,值可以重复

        //遍历------------------
        //键找值--
        Set<String>keys=map.keySet();//获取键集
        for(String key:keys){
            System.out.println(key+"="+map.get(key));//获取值
        }

        //键值对--
        Set<Map.Entry<String, Integer>> entries=map.entrySet();
        //Entry是Map接口的内部接口
        for (Map.Entry<String, Integer> e:entries){
            System.out.println(e.getKey()+"="+e.getValue());
        }
        System.out.println(entries);//[a=3, b=6, c=4]

        //forEach--
        //default void forEach(BiConsumer<? super K,? super V> action)
        map.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s+"="+integer);
            }
        });
        //forEach底层还是先获取键值对集合再for循环



    }
    private static void note_HashMap(){
        //无序,不重复,无索引
        //HashMap与HashSet底层原理是一样的,都是哈希表结构
        //当传入数组时该索引已有元素,比较键的属性值,如果一样,则覆盖原元素
        //如果属性值不一样,则添加到链表尾(JDK8以前把老元素挂在下面)
        //链表长度大于8且数组长度大于等于64,链表自动转为红黑树
        //如果键为自定义对象,需要重写hashCode和equals方法

        //源码解读
        // put(K,V)->putVal(hash(K),K,V,false,false)第四个参数表示是否保留原元素
        // hash(K)根据键计算哈希值
        //
        /*

         */

    }
    private static void note_LinkedHashMap(){
        // 有序,不重复,无索引
        // 哈希表+双链表(根据元素添加顺序排序)
    }
    private static void note_TreeMap(){
        // 红黑树结构
        // 不重复,无索引,对键排序
        // 默认按键的从小到大顺序排序
        // 自定义排序规则,实现Comparable接口重写并hashCode和equals方法,
        // 或创建集合时传递Comparator比较器对象
        /*
        int .ceiling(int v)返回大于v的第一个值,没找到返回-1
         */

    }

}

