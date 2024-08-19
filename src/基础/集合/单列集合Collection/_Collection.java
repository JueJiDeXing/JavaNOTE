package 基础.集合.单列集合Collection;

import java.util.ArrayList;
import java.util.Collection;

public class _Collection {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>();
        boolean isAdd1 = collection.add(1);//添加元素
        //boolean isAdd2 = collection.addAll(List.of(1, 2, 3));//添加元素
        boolean isContained = collection.contains(1);//判断元素是否存在
        //boolean isRetain = collection.retainAll(List.of(1, 2));//取交集(可能含重复元素)
        collection.clear();//清空集合
        boolean isEmpty = collection.isEmpty();//判断集合是否为空
        boolean isRemove = collection.remove(1);//移除元素
        int size = collection.size();//获取集合内当前元素个数
        Integer[] array = collection.toArray(new Integer[0]);//转数组
    }
}
