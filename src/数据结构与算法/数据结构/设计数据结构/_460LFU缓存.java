package 数据结构与算法.数据结构.设计数据结构;

import java.util.*;

public class _460LFU缓存 {
    /*
    请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。  -->  使用频度低(优先) + 最久未使用

    实现 LFUCache 类：
    LFUCache(int capacity)
        用数据结构的容量 capacity 初始化对象
    int get(int key)
        如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
    void put(int key, int value)
        如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。
        当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。
        在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。

    为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。
    使用计数最小的键是最久未使用的键。

    当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。
    对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。

    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     */
}

class LFUCache {
    private static class Node {
        int key, value, freq = 1; // 新书只读了一次
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> keyToNode = new HashMap<>();
    private final Map<Integer, Node> freqToDummy = new HashMap<>();
    private int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
     */
    public int get(int key) {
        Node node = getNode(key);
        return node != null ? node.value : -1;
    }

    /**
     如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。
     当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。
     在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
     */
    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) { // 有这本书
            node.value = value; // 更新 value
            return;
        }
        //没有这本书
        if (keyToNode.size() == capacity) { // 书太多了,移除freq最小的一摞里最久不使用的
            Node dummy = freqToDummy.get(minFreq);//找到最小频次的一摞
            Node backNode = dummy.prev; // 最下面的书(最久不使用)
            keyToNode.remove(backNode.key);// 移除
            remove(backNode);
            if (dummy.prev == dummy) { // 这摞书被移空了
                freqToDummy.remove(minFreq); // 移除空链表(后面频次置为1)
            }
        }
        node = new Node(key, value); // 新书
        keyToNode.put(key, node);
        pushFront(1, node); // 放在「看过 1 次」的最上面
        minFreq = 1;
    }

    private Node getNode(int key) {
        if (!keyToNode.containsKey(key)) { // 没有这本书
            return null;
        }
        Node node = keyToNode.get(key); // 有这本书
        remove(node); // 把这本书抽出来
        Node dummy = freqToDummy.get(node.freq);
        if (dummy.prev == dummy) { // 这摞书被移空了
            freqToDummy.remove(node.freq); // 移除空链表
            if (minFreq == node.freq) { // 这摞书是最左边的
                minFreq++;
            }
        }
        node.freq++;
        pushFront(node.freq, node); // 放在右边这摞书的最上面
        return node;
    }

    // 创建一个新的双向链表
    private Node newList() {
        Node dummy = new Node(0, 0); // 哨兵节点
        dummy.prev = dummy;
        dummy.next = dummy;
        return dummy;
    }

    // 在链表头添加一个节点（把一本书放在最上面）
    private void pushFront(int freq, Node x) {
        Node dummy = freqToDummy.computeIfAbsent(freq, k -> newList());
        x.prev = dummy;
        x.next = dummy.next;
        x.prev.next = x;
        x.next.prev = x;
    }

    // 删除一个节点（抽出一本书）
    private void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }
}
