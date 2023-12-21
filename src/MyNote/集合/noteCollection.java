package MyNote.集合;

import java.util.*;
import java.util.function.Consumer;

public class noteCollection {
    //单列集合
    //                        Collection
    //               ┌──────────┴─────────┐
    //       List(有序,可重复)          Set(无序,不重复)
    //    ┌──────────┼───────x───┐       ┌───────┴───────┐
    // ArrayList  LinkedList  Vector   HashSet        TreeSet
    //  (动态数组)     (双链表)    (淘汰)   (哈希表)         (树表)
    //                                   └──LinkedHashSet(双链哈希表)

    //二叉树:每个结点的子结点数≤2
    //二叉搜索数:左结点<父结点<右结点
    //平衡二叉树:每个结点的左右子树层数差≤1
    //平衡二叉树的旋转机制
    public static void main(String[] args) {
        //note_Collection();

        //note_List();
        //note_Set();

        //note_ArrayList();
        //note_LinkedList();

        //note_HashSet();
        //note_LinkedHashSet();
        //note_TreeSet();
    }

    private static void note_Collection() {
        //成员方法------------------------------------
        // boolean .add(E e)添加对象,添加失败返回false

        // void .clear()清空元素

        // boolean .remove(E e)删除指定元素 ,元素不存在返回false
        // (Set没有索引,所以Collection不能指定索引删除元素)

        // boolean .contains(Object obj)判断元素是否存在
        //底层依赖equals方法比较地址值,如果存储的是自定义对象,需要在自定义类中 重写equals方法

        // boolean .isEmpty()判断是否为空集

        // int .size()返回集合长度

        //遍历--------------------------------------------
        //迭代器
        //Iterator<E>iterator迭代器()返回迭代器对象
        //boolean .hasNext()判断当前位置是否有元素
        //E .next()获取当前位置元素并将迭代器移至下一个位置
        // 指针不会复位,要再次遍历需要生成一个新迭代器对象
        // 迭代时不允许使用Collection增加或删除元素,可以用迭代器的remove方法删除元素,增加元素没有办法
        Collection<String> coll = new ArrayList<>();
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");
        Iterator<String> it = coll.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }
        System.out.println("-----1------");
        // 迭代器底层原理
        // 创建迭代器时在底层创建一个内部类Itr()对象
        // Itr成员变量:int cursor光标,指针 ,int lastRet上一次操作的索引
        // int expectedModCount=modCount ,modCount集合变化的次数,增加或删除元素都会自增
        // hasNext()->{return cursor!=size
        // next()->{checkForComodification();int i=cursor;/* 判断i是否越界 */ cursor=i+1;return elementData[lastRet=i];}
        // checkForComodification()->判断modCount!=expectedModCount


        //增强for,快捷方式: 集合名.for
        for (String s : coll) {
            System.out.println(s);
        }
        System.out.println("-----2------");

        //Lambda表达式  forEach(Consume<? super T> action)
        coll.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                //s->集合的每一个元素
                System.out.println(s);
            }
        });
        System.out.println("-----3------");

        coll.forEach(s -> System.out.println(s));
        System.out.println("------4-----");
        coll.forEach(System.out::println);
    }

    private static void note_List() {
        //成员方法----------------------------------------------------
        //void .add([int index,] E e) (指定位置)插入指定元素,原索引元素后移
        //E .remove(int index)删除指定索引元素,并返回该元素
        //boolean .remove(E e)删除指定元素,并返回该元素
        //E .set(int index,E e)修改指定索引元素
        //E .get(int index)获取指定索引元素
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0, 4);

        list.remove(1);//移除指定索引,有重载时方法优先调用实参与形参类型一致的方法
        list.remove(Integer.valueOf(1));//手动装箱,移除元素而不是索引

        //遍历---------------------------
        //迭代器
        Iterator<Integer> it = list.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        //列表迭代器---#############
        //.add .remove .set   元素可增加,指针可前移
        //.hasNext()    .hasPrevious()
        //.next()       .previous()
        ListIterator<Integer> it2 = list.listIterator();


        //列表迭代器---#############
        //增强for
        for (Integer n : list) {
            System.out.println(n);
        }
        //普通for
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        //Lambda表达式 forEach
        list.forEach(n -> System.out.println(n));

    }

    private static void note_Set() {
        //与Collection的方法基本一致
        Set<String> s = new HashSet<>();
        s.add("aaa");
        s.add("aaa");//添加失败返回false
        s.add("bbb");
        s.add("ccc");
        System.out.println(s);//[aaa, ccc, bbb]无序输出
        //遍历
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        //...
    }

    private static void note_ArrayList() {
        // 底层为数组结构
        // 空参创建集合时,底层创建默认长度为0的数组elementData
        // 添加第一个元素时创建一个长度为10以null填充的新数组,size=0
        // 再将元素放入第一个位置,size+1(size:元素个数、下一个元素应存入的位置)
        // 当原数组放满之后,添加元素时,创建一个长度为1.5倍的新数组,将原数组拷贝至新数组
        // 如果一次添加多个元素,超过1.5倍,则新数组长度以实际为准(正好放满全部位置)


        //.add   .addAll(list)
    }

    private static void note_LinkedList() {
        // 底层为双向链表结构
        // LinkedList,成员变量:int size长度,Node<E>first头结点对象,Node<E>last尾结点对象
        // Node结点,成员变量:E item自身的值,Node<E>next后结点,Node<E>prev前结点
        // 添加元素时.add(E e),l=last记录原尾结点对象(上一个结点)
        // 创建一个新结点new Node<>(l,e,null),赋值给last (使链表尾指向这个结点)
        // 如果l为空(没有上一个结点,说明是第一次添加元素),把新结点赋值给头结点first
        // 否则赋值给l.next(使上一个结点的next指向这个结点)
        // 然后size+1
        // 总结:取到上一个结点地址,使这个结点的prev指向上一个结点
        // 再把链表的尾结点last指向这个结点,再使上一个结点的next指向这个结点
        // 即:改变last指向,并且使两个结点互相指向对方

        //特有方法
        //.addFirst(E e)  .addLast(E e)
        //E .getFirst()   E .getLast()
        //E .removeFirst()  E .removeLast()
    }

    private static void note_HashSet() {
        //哈希表组成
        //JDK8以前由数组+链表组成
        //JDK8开始由数组+链表+红黑树组成

        //哈希值:对象的整数表现形式
        //哈希值的计算默认使用地址值,不同对象计算出的哈希值是不同的
        //在重写hashCode方法后使用对象内部的属性值进行计算,只要属性值相同,则计算出的哈希值相同
        //哈希碰撞:不同的属性值或不同的地址值计算出相同的哈希值(小概率事件)

        System.out.println("abc".hashCode());
        System.out.println("acD".hashCode());//发生哈希碰撞

        //哈希表底层原理
        //1.创建一个默认长度16,默认加载因子0.75的数组table
        //2.根据元素哈希值和数组长度计算出应存入的位置int index=(length-1)&哈希值
        //3.如果当前元素为null,直接存入
        //4.如果不为null,则调用equals方法比较属性值(比较链表所有元素的属性值)
        //5.如果与链表的某个元素属性值一样,不存入,如果不一样,挂在链表尾
        //JDK8以前老元素挂在新元素下面,JDK8以后新元素挂在老元素下面

        //加载因子:当数组存储16*0.75=12个元素时,数组扩容至原来的两倍
        //当链表长度大于8且数组长度大于等64,链表自动转为红黑树结构
        //如果存储的是自定义对象,必须重写hashCode和equals方法

        //创建HashSet调用的时HashMap构造
    }

    private static void note_LinkedHashSet() {
        // 有序,不重复,无索引(有序指存储与取出元素的顺序一致)
        // 底层数据结构:哈希表+双链表(记录存储顺序)
        // 元素之间记录地址值,数组存储的链表既有单链表又有双链表
        //一般使用HashSet去重,只有需要去重且有序时才使用LinkedHashSet

        //创建LinkedHashSet调用的时LinkedHashMap构造
    }

    private static void note_TreeSet() {
        //不重复,无索引,可排序(默认从小到大)
        //底层由红黑树结构实现排序
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(12);
        ts.add(2);
        ts.add(8);
        ts.add(1);
        System.out.println(ts);//[1, 2, 8, 12]
        //String从首字母开始依次按ASCII码表升序

        // 对于自定义对象
        // 默认排序规则/自然排序:对自定义类实现Comparable<>接口,指定比较方法,此时不需要重写hashCode和equals方法
        /* compareTo(Student o){
                return this.age-o.age;//按年龄升序(不重复)
                //this表示当前要添加的元素,o表示红黑树里已有的元素
                //负数则表示小,存左边,正数表示大,存右边,0表示一样大则元素已存在不存
            }
        */
        // 比较器排序,带参构造,传入比较器
        TreeSet<String> ts2 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i = o1.length() - o2.length();//先按字符串长度排序
                i = i == 0 ? o1.compareTo(o2) : i;//长度一样时再按ASCII码表排序
                return i;
            }
        });

        /*
        T .celling(T e)返回大于等于e的最小值 .floor(T e)
        T .higher(T e)返回大于e的最小值 lower(T e)
        T .first()返回首位 .last
        T .pollFirst()抛出首位 .pollLast()
        boolean .contain(Object o)判断是否存在
        boolean .remove(Object o)删除

         */
    }
}

