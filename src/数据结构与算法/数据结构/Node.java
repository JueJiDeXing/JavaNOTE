package 数据结构与算法.数据结构;
/**
 节点类

 @param <E>
 */
public class Node<E> {

    public E value;
    public Node<E> next;

    public Node(E value, Node<E> next) {
        this.value = value;
        this.next = next;
    }

}
