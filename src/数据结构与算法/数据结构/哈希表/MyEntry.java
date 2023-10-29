package 数据结构与算法.数据结构.哈希表;

public class MyEntry {
    int hash;//哈希码
    Object key;
    Object value;
    MyEntry next;

    public MyEntry(int hash, Object key, Object value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }

    public MyEntry(int hash, Object key, Object value, MyEntry next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
