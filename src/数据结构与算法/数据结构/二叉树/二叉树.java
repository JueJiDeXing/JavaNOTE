package 数据结构与算法.数据结构.二叉树;

import 数据结构与算法.数据结构.队列.LinkedListQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static 数据结构与算法.数据结构.二叉树.RBTree.Color.BLACK;
import static 数据结构与算法.数据结构.二叉树.RBTree.Color.RED;

public class 二叉树 {
    public static void main(String[] args) {
        BSTree tree = new BSTree();
        tree.put(1, "a");
        tree.put(2, "b");
        tree.put(3, "c");
        tree.put(1, "d");
        tree.put(5, "e");
        System.out.println(tree.get(1));
        System.out.println(tree.get(2));
    }
}

/**
 Binary Search Tree 二叉搜索树
 */
class BSTree {

    BSTNode root;//根节点

    public BSTree(BSTNode root) {
        this.root = root;
    }

    public BSTree() {
    }

    /**
     存储关键字对应的值

     @param key   关键字
     @param value 值
     */
    public void put(int key, Object value) {
        BSTNode node = root;
        BSTNode parent = null;//新增节点的父节点
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                //有key则更新
                node.value = value;
                return;
            }
        }
        //没有key则新增节点
        if (parent == null) {
            root = new BSTNode(key, value);
            return;
        }
        if (key < parent.key) {
            parent.left = new BSTNode(key, value);
        } else {
            parent.right = new BSTNode(key, value);
        }


    }

    /**
     查找key对应的值
     递归实现

     @param key 关键字
     @return 关键字对应的值
     */
    public Object get2(int key) {
        return doGet(root, key);
    }

    /**
     递归查找
     */
    private Object doGet(BSTNode node, int key) {
        if (node == null) {
            return null;//没找到
        }
        if (key < node.key) {
            return doGet(node.left, key);//向左找,尾递归在java不支持自动优化,可以自己转写为非递归方法
        } else if (key > node.key) {
            return doGet(node.right, key);//向右找
        } else {
            return node.value;//找到了
        }
    }

    /**
     查找key对应的值
     <b>非递归实现</b>

     @param key 关键字
     @return 关键字对应的值
     */
    public Object get(int key) {
        BSTNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /**
     查找最大关键字对应值

     @return 关键字对应的值
     */
    public Object max() {
        return max(root);
    }

    /**
     从某节点查找树最大关键字对应值

     @return 关键字对应的值
     */
    public Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    /**
     查找最小关键字对应值

     @return 关键字对应的值
     */
    public Object min() {
        return min(root);
    }

    /**
     从某节点开始查找数最小关键字对应值

     @return 关键字对应的值
     */
    public Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    /**
     查找前驱

     @param key 关键字
     @return 前驱值
     */
    public Object predecessor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                ancestorFromLeft = p;//记录最近一次自左而来的祖先
                p = p.right;
            } else {
                break;
            }
        }//查找key对应节点
        if (p == null) {//没找到
            return null;
        }
        //找到节点

        if (p.left != null) {
            //如果节点有左子树,那么它的前驱为左子树里的最大值
            return max(p.left);
        }
        //如果没有左子树,那么它的前驱为离它最近的自左而来的祖先
        // (自左而来:节点处于某祖先节点的右子树,则将祖先称为自左而来)
        return ancestorFromLeft == null ? null : ancestorFromLeft.value;
    }

    /**
     查找后继

     @param key 关键字
     @return 后继值
     */
    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (key < p.key) {
                ancestorFromRight = p;//记录最近一次自右而来的祖先
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                break;
            }
        }//查找key对应节点
        if (p == null) {//没找到
            return null;
        }
        //找到节点
        if (p.right != null) {
            //如果节点有右子树,那么它的后继为右子树里的最小值
            return min(p.right);
        }
        //如果没有右子树,那么它的后继为离它最近的自右而来的祖先
        // (自右而来:节点处于某祖先节点的左子树,则将祖先称为自右而来)
        return ancestorFromRight == null ? null : ancestorFromRight.value;
    }


    /**
     根据key删除

     @param key 关键字
     @return 被删除的值
     */
    public Object delete(int key) {
        /*
        情况分析:
        1. 被删除的节点只有左孩子或右孩子,将左孩子或右孩子托孤给parent(如果没有孩子将null托孤)
        2. 被删除的节点有左右孩子,设它的后继节点为next,next的父亲为nextP (这里也可以去寻找前驱节点)
            2.1 如果nextP就是被删除的节点,则将next托孤给parent
            2.2 如果nextP不是被删除的节点,则将next的后任托孤给nextP,再将next托孤给parent
         */
        BSTNode deleted = root;//被删除的节点
        BSTNode parent = null;//记录父节点
        while (deleted != null) {
            if (key < deleted.key) {
                parent = deleted;
                deleted = deleted.left;
            } else if (key > deleted.key) {
                parent = deleted;
                deleted = deleted.right;
            } else {
                break;
            }
        }
        if (deleted == null) {
            return null;//没找到
        }
        //找到,执行删除操作
        if (deleted.left == null) {//孩子数<=1
            shift(parent, deleted, deleted.right);
        } else if (deleted.right == null) {
            shift(parent, deleted, deleted.left);
        } else {//有左右孩子
            //查找后继
            BSTNode next = deleted.right;
            BSTNode nextP = null;//后继节点的父节点
            while (next.left != null) {
                nextP = next;
                next = next.left;
            }
            //如果删除节点与后继节点不相邻,处理后继的后任
            if (deleted != nextP) {
                //这里不需要考虑左后任,因为后继是子树里的最小值,它不可能有左孩子,只可能有右孩子
                shift(nextP, next, next.right);
                next.right = deleted.right;
            }
            //后继取代被删除节点
            shift(parent, deleted, next);
            next.left = deleted.left;
        }

        return deleted.value;
    }

    /**
     托孤方法

     @param parent  父节点
     @param deleted 被删除的节点
     @param child   要托孤的孩子节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {//被删除的节点为根节点
            root = child;
        } else if (parent.left == deleted) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /**
     根据key删除  递归实现

     @param key 关键字
     @return 被删除的值
     */
    public Object delete2(int key) {
        ArrayList<Object> result = new ArrayList<>();//保存值
        root = doDelete(root, key, result);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     @param node   起点
     @param key    被删除的节点的key
     @param result 保存需要返回的value值
     @return 删除的节点的孩子
     */
    private BSTNode doDelete(BSTNode node, int key, ArrayList<Object> result) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {//删除的节点在左子树
            node.left = doDelete(node.left, key, result);//在左子树里去删
            return node;
        }
        if (key > node.key) {
            node.right = doDelete(node.right, key, result);
            return node;
        }
        // 已经找到要删除的节点node

        result.add(node.value);
        // 1.孩子数<=1,返回删除节点的孩子
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        // 2.有左右孩子
        //查找后继
        BSTNode next = node.right;
        while (next.left != null) {
            next = next.left;
        }
        //调整后继的孩子关系,然后返回后继节点
        next.right = doDelete(node.right, next.key, new ArrayList<>());//处理后继的后任,相当于在右子树里删除后继,再将这个子树作为后继的右孩子
        next.left = node.left;//接替node的左孩子
        return next;
    }

    // 根据key的范围查找---核心思想:中序遍历

    /**
     查找小于key的节点值
     (-∞,key) -> list:values
     */
    public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {//向左子树走到头
                stack.push(p);
                p = p.left;
            } else {//走到头往回走处理右子树
                BSTNode pop = stack.pop();
                if (pop.key < key) {
                    result.add(pop.value);
                } else {//已经大于key了就不用再查找了(节点的右子树都是大于这个节点的)
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    /**
     查找大于key的节点的key
     (key,+∞) -> list:values
     */
    public List<Object> greater(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;//倒序中序遍历,在查找大值时可以提前退出
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.left;
            }
        }
        return result;
    }

    /**
     查找处于key1与key2之间的节点的value
     [key1,key2] -> list:values
     */
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (key1 <= pop.key && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }
}


/**
 Binary Search Tree 二叉搜索树,泛型版本
 */
class BSTree_T<K extends Comparable<K>, V> {

    BSTNode_T<K, V> root;//根节点

    public BSTree_T(BSTNode_T<K, V> root) {
        this.root = root;
    }

    public BSTree_T() {
    }

    /**
     存储关键字对应的值

     @param key   关键字
     @param value 值
     */
    public void put(K key, V value) {

    }

    /**
     查找key对应的值
     递归实现

     @param key 关键字
     @return 关键字对应的值
     */
    public V get2(K key) {
        return doGet(root, key);
    }

    /**
     递归查找
     */
    private V doGet(BSTNode_T<K, V> node, K key) {
        if (node == null) {
            return null;//没找到
        }
        int result = key.compareTo(node.key);
        if (result < 0) {
            return doGet(node.left, key);//向左找,尾递归在java不支持自动优化,可以自己转写为非递归方法
        } else if (result > 0) {
            return doGet(node.right, key);//向右找
        } else {
            return node.value;//找到了
        }
    }

    /**
     查找key对应的值
     <b>非递归实现</b>

     @param key 关键字
     @return 关键字对应的值
     */
    public V get(K key) {
        BSTNode_T<K, V> p = root;
        while (p != null) {
            if (key == null) {
                throw new RuntimeException("key为null");
            }
            int result = key.compareTo(p.key);//key可能为null
            if (result < 0) {//compare:-1表示前小,0表示相等,1表示前一个大
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                return p.value;
            }
        }
        return null;
    }

    /**
     查找最大关键字对应值

     @return 关键字对应的值
     */
    public V max() {
        if (root == null) {
            return null;
        }
        BSTNode_T<K, V> p = root;
        while (p.right != null) {
            p = p.right;
        }
        return null;
    }

    /**
     查找最小关键字对应值

     @return 关键字对应的值
     */
    public V min() {
        if (root == null) {
            return null;
        }
        BSTNode_T<K, V> p = root;
        while (p.left != null) {
            p = p.left;
        }
        return null;
    }

    /**
     查找前驱

     @param key 关键字
     @return 前驱值
     */
    public V successor(K key) {
        return null;
    }

    /**
     查找后继

     @param key 关键字
     @return 后继值
     */
    public V predecessor(K key) {
        return null;
    }

    /**
     根据key删除

     @param key 关键字
     @return 被删除的值
     */
    public V delete(K key) {
        return null;
    }
}

/**
 在插入/删除节点时通过旋转保持平衡的自平衡二叉搜索树<br>
 AVL是实现之一
 */
class AVLTree {
    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1;//高度

        public AVLNode() {
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    //左右子树高度差大于1则旋转
    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(AVLNode node) {
        int heightLeft = getHeight(node.left);
        int heightRight = getHeight(node.right);
        node.height = Integer.max(heightLeft, heightRight) + 1;
    }

    private int balanceFactor(AVLNode node) {
        // -1,0,1表示平衡
        // <-1 表示右重
        // >1 表示左重
        return getHeight(node.left) - getHeight(node.right);
    }

    /*不平衡情况
      LL:失衡节点左边高,左孩子左边高或等高   需要右旋
                 5                        3
             3        6     右旋       2       5
          2    4            -->     1       4    6
        1
      LR:失衡节点左边高,左孩子右边高     需要对左子树左旋,再右旋
                 6                         6                    4
            2         7    先左旋       4        7   再右旋    2       6
        1      4           -->      2     5         -->    1   3    5   7
             3   5                1   3
      RL:失衡节点右边高,左孩子左边高     需要对右子树左旋,再左旋
      RR:失衡节点右边高,左孩子右边高或等高      需要左旋
     */
    private AVLNode rightRotate(AVLNode downNode) {
        AVLNode upNode = downNode.left;
        downNode.left = upNode.right;//给上位节点的右孩子换爹
        upNode.right = downNode;//上位
        updateHeight(downNode);//旋转只会改变上位与下潜的两个节点的高度
        updateHeight(upNode);//必须先更新下面节点的高度,再更新上面节点的高度
        return upNode;
    }

    private AVLNode leftRotate(AVLNode downNode) {
        AVLNode upNode = downNode.right;
        downNode.right = upNode.left;
        upNode.left = downNode;
        updateHeight(downNode);
        updateHeight(upNode);
        return upNode;
    }

    private AVLNode leftRightRotate(AVLNode node) {
        leftRotate(node.left);
        return rightRotate(node);
    }

    private AVLNode rightLeftRotate(AVLNode node) {
        rightRotate(node.right);
        return leftRotate(node);
    }

    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = balanceFactor(node);
        if (bf > 1) {//左高
            int bfLeft = balanceFactor(node.left);
            if (bfLeft >= 0) {//LL
                return rightRotate(node);
            } else {//LR
                return leftRightRotate(node);
            }
        } else if (bf < -1) {//右高
            int bfRight = balanceFactor(node.right);
            if (bfRight <= 0) {//RR
                return leftRotate(node);
            } else {//RL
                return rightLeftRotate(node);
            }
        } else {
            return node;
        }
    }

    AVLNode root;

    public void put(int key, Object value) {
        root = doPut(root, key, value);

    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        //1.找到空位
        if (node == null) {
            return new AVLNode(key, value);
        }
        //2.已经存在key,更新节点值
        if (key == node.key) {
            node.value = value;
        }
        //3.还没找到,继续查找
        if (key < node.key) {
            node.left = doPut(node.left, key, value);//向左
        } else {
            node.right = doPut(node.right, key, value);//向右
        }
        //递归出栈
        updateHeight(node);//更新高度
        return balance(node);//检查失衡,返回子树根节点
    }

    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        // 1. node==null
        if (node == null) {
            return null;
        }
        // 2. 没找到key,继续查找
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (key > node.key) {
            node.right = doRemove(node.right, key);
        } else {
            // 3. 找到key
            if (node.left == null && node.right == null) { // (1)没有孩子,返回null
                return null;
            } else if (node.left == null) { // (2)只有一个孩子,返回该孩子
                node = node.right; //右子树需要检查平衡,所以不立刻返回,而是暂存到node里
            } else if (node.right == null) {
                node = node.left;//暂存
            } else { // (3)有两个孩子,后继上位,返回后继
                //找后继
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                //后继上位
                s.right = doRemove(node.right, s.key);//处理后继的后任
                s.left = node.left;
                node = s;//暂存
            }
        }
        //递归出栈
        updateHeight(node);//更新高度
        return balance(node);//检查失衡,返回子树根节点
    }
}

/**
 红黑树,旋转次数少于AVL树
 */
class RBTree {
    /*
    红黑规则:
    1. 每个节点都有两种颜色:红与黑
    2. 根节点是黑色的
    3. 所有叶子节点(null/nil)都被视为黑色
    4. 红色节点不能相邻
    5. 从根节点到任意一个叶子节点的最短路径中黑色节点数量相同
    (忽略null节点,黑色的叶子节点必须成对出现[有兄弟])
     */

    RBNode root;

    enum Color {RED, BLACK}

    static class RBNode {
        int key;
        Object value;
        Color color = RED;
        RBNode left;
        RBNode right;
        RBNode parent;

        public RBNode(int key, Color color, RBNode left, RBNode right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.color = color;
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }

        public RBNode() {
        }

        public RBNode(int key, Object value, RBNode left, RBNode right, RBNode parent) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }

        public RBNode(int key, Color color) {
            this.key = key;
            this.color = color;
        }

        public RBNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public RBNode(int key) {
            this.key = key;
        }

        //找叔叔
        RBNode uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        //找兄弟
        RBNode brother() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

        //是否是左孩子
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }
    }

    public RBTree() {
    }

    public RBTree(RBNode root) {
        root.color = BLACK;
        this.root = root;
    }

    //判断节点颜色
    boolean isRed(RBNode node) {
        return node != null && node.color == RED;
    }

    boolean isBlack(RBNode node) {
        return node == null || node.color == BLACK;
    }

    public boolean contains(int key) {
        RBNode rbNode = find(key);
        return rbNode != null;
    }

    //旋转
    // 不同点: 需要处理parent ,旋转后的父子关系需要在函数内完成
    private void leftRotate(RBNode downNode) {
        RBNode upNode = downNode.right;//上位节点
        RBNode parentNode = downNode.parent;//更上层节点
        RBNode leftChild = upNode.left;//上位节点的左孩子
        //左孩子托孤
        downNode.right = leftChild;
        if (leftChild != null) {
            leftChild.parent = downNode;
        }
        //上位
        upNode.left = downNode;
        upNode.parent = parentNode;
        downNode.parent = upNode;

        if (parentNode == null) {//新根
            root = upNode;
        } else if (parentNode.left == downNode) {
            parentNode.left = upNode;
        } else {
            parentNode.right = upNode;
        }
    }

    private void rightRotate(RBNode downNode) {
        RBNode upNode = downNode.left; //上位节点
        RBNode parentNode = downNode.parent;//更上层节点
        RBNode rightChild = upNode.right;//上位节点的右孩子
        //右孩子托孤
        downNode.left = rightChild;
        if (rightChild != null) {
            rightChild.parent = downNode;
        }
        //上位
        upNode.right = downNode;
        upNode.parent = parentNode;
        downNode.parent = upNode;

        if (parentNode == null) {//新根
            root = upNode;
        } else if (parentNode.left == downNode) {
            parentNode.left = upNode;
        } else {
            parentNode.right = upNode;
        }
    }

    /**
     新增/更新<br>
     如果遇到红红不平衡则需要调整
     */
    public void put(int key, Object value) {
        RBNode p = root;
        RBNode parent = null;
        while (p != null) {//查找插入位置
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.value = value;//有则直接更新值
                return;
            }
        }
        //插入
        RBNode inserted = new RBNode(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
        } else {
            parent.right = inserted;
        }
        inserted.parent = parent;
        fixRedRed(inserted);//检查红黑规则-红红不能相邻
    }

    void fixRedRed(RBNode node) {
        if (node == root) {// 1.插入的为根节点,将节点变黑
            node.color = BLACK;
            return;
        }
        if (isBlack(node.parent)) {// 2.插入节点的父节点为黑色,不需要调整
            return;
        }
        // 3.父亲为红,触发红红相邻,需要调整
        RBNode parent = node.parent;
        RBNode uncle = node.uncle();
        RBNode grandparent = parent.parent;
        // 3.1 叔叔为红
        if (isRed(uncle)) {
            // 将父亲变黑
            parent.color = BLACK;
            // 叔叔为红,为保持平衡,将叔叔也改为黑色
            uncle.color = BLACK;
            // 子树黑色过多,需要将祖父变为红色,此时可能会触发祖父与曾祖父的红红相邻
            grandparent.color = RED;
            fixRedRed(grandparent);//可以递归求解,一直到根节点变黑
            return;
        }
        // 3.2 叔叔为黑
        if (parent.isLeftChild()) {
            if (!node.isLeftChild()) { //LR
                // 对父亲左旋,从LR变为LL的状态
                leftRotate(parent);
                parent = node;//注意:节点上位后,该节点变为父节点
            }
            //父亲变黑,祖父变红
            parent.color = BLACK;
            grandparent.color = RED;
            // 此时由于祖父变红,叔叔分支黑色减少
            rightRotate(grandparent);// 需要右旋将祖父拉下来,父亲上位填补黑色
        } else {
            if (node.isLeftChild()) {//RL
                rightRotate(parent);
                parent = node;
            }
            //RR
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }

    /**
     删除<br>
     如果遇到黑黑不平衡则需要调整
     */
    public void remove(int key) {
        RBNode deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    //查找删除节点
    RBNode find(int key) {
        RBNode p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    //查找剩余节点
    RBNode findReplaced(RBNode deleted) {
        if (deleted.left == null && deleted.right == null) {//没有孩子返回null,
            return null;
        }
        if (deleted.left == null) {//一个孩子返回孩子节点
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        //有两个孩子,查找后继
        RBNode s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }

    void doRemove(RBNode deleted) {
        RBNode replaced = findReplaced(deleted);
        RBNode parent = deleted.parent;
        // 1.没有孩子
        if (replaced == null) {
            if (deleted == root) { //删除的为根节点
                root = null;
            } else {//非根节点(叶子)
                // 颜色检查
                if (isBlack(deleted)) {
                    //删除的为黑色节点,触发双黑(少了一个节点)
                    fixDoubleBlack(deleted);
                    //删除红色节点不需要考虑平衡
                    // 红色节点要么是叶子节点,可以直接删除; 要么是有两个黑色孩子节点,可以使用李代桃僵法删除后继
                }

                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;//垃圾回收
            }
            return;
        }
        // 2.有一个孩子
        if (deleted.left == null || deleted.right == null) {
            if (deleted == root) { //删除的为根节点
                //根节点如果只有一个孩子,那么孩子一定为红色
                // 李代桃僵:将根节点与孩子节点进行键值交换,再删除孩子
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;
            } else {//非根节点(叶子)
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;//垃圾回收
                if (isBlack(deleted) && isBlack(replaced)) {
                    //删黑,剩黑,触发双黑(少了一个节点)
                    fixDoubleBlack(replaced);//因为先调整的位置,所以传入replaced
                } else {
                    // 删黑,剩红,用红色补齐
                    replaced.color = BLACK;
                }
            }
            return;
        }
        // 3.有两个孩子 -> 1 or 2
        // 李代桃僵:将待删除节点与它的后继进行键值交换,再删除后继(递归走上面的没有孩子和只有一个孩子的分支)
        int tempKey = deleted.key;
        Object tempValue = deleted.value;
        deleted.key = replaced.key;
        deleted.value = replaced.value;
        replaced.key = tempKey;
        replaced.value = tempValue;
        doRemove(replaced);
    }

    /*双黑
    1.被调整节点的兄弟为红,此时两个侄子定为黑,通过一次旋转获取一个侄子成为黑色兄弟,过渡到下面的两个情况
    2.兄弟为黑,两个侄子都为黑
    3.兄弟为黑,两个侄子至少一个红色
     */
    private void fixDoubleBlack(RBNode node) {
        if (node == root) {//递归到根节点不需要调整
            return;
        }
        RBNode parent = node.parent;
        RBNode brother = node.brother();
        //1.被调整节点的兄弟为红
        if (isRed(brother)) {//对父亲左旋or右旋
            if (node.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            //换颜色,暂时平衡
            parent.color = RED;//因为兄弟为红,所以父亲一定为黑色
            brother.color = BLACK;
            fixDoubleBlack(node);
            return;
        }
        if (brother == null) {// TODO 不可能发生的情况,兄弟为null,树一定不平衡???
            fixDoubleBlack(parent);
            System.out.println("---兄弟为null---");
            return;
        }
        //2.兄弟为黑,两个侄子都为黑
        if (isBlack(brother.left) && isBlack(brother.right)) {
            //将兄弟变红,两边黑色同时减1
            brother.color = RED;
            if (isRed(parent)) {// 如果父亲为红,则直接用父亲填补即可
                parent.color = BLACK;
            } else {
                fixDoubleBlack(parent);// 如果父亲为黑,则该路径依然少一个黑色,父亲进入递归
            }
        }
        //3.兄弟为黑,两个侄子不都为黑
        else {
            if (brother.isLeftChild()) {
                //LL
                if (isRed(brother.left)) {
                    //右旋,兄弟上位
                    rightRotate(parent);
                    brother.left.color = BLACK;//侄子填补左边黑色(因为兄弟取代父亲,如果父亲为黑,则左边少一个黑,如果父亲为红,则兄弟变红,左边少一个黑)
                    brother.color = parent.color;//兄弟取代父亲
                }//LR
                else {
                    //兄弟为黑不用变颜色,旋转后兄侄关系改变,所以将变色放在旋转之前
                    brother.right.color = parent.color;//红色侄子取代父亲,填补右侧黑色
                    leftRotate(brother);
                    rightRotate(parent);
                }
            } else {
                //RL
                if (isRed(brother.left)) {
                    brother.left.color = parent.color;
                    rightRotate(brother);
                    leftRotate(parent);
                }
                //RR
                else {
                    leftRotate(parent);
                    brother.right.color = BLACK;
                    brother.color = parent.color;
                }
            }
            //提取出的公共部分
            parent.color = BLACK;//父亲填补黑色(兄弟替换父亲的颜色,那么右树因为要删除黑色节点所以会少一个黑色)
        }
    }

    public static List<List<Object>> levelOrder(RBNode root) {
        List<List<Object>> result = new ArrayList<>();//存储层序遍历的结果
        if (root == null) {
            return result;
        }
        //每次从队列中获取元素,并把左右子树添加进队列
        LinkedListQueue<RBNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1;//当前层数

        while (!queue.isEmpty()) {
            int c2 = 0;//下一层节点数
            List<Object> level = new ArrayList<>();
            for (int i = 0; i < c1; i++) {
                RBNode n = queue.poll();
                //System.out.print(n+" ");
                level.add(n.key + ":" + n.value + ":" + n.color);
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;//统计下一层节点数
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            //System.out.println();//换行
            result.add(level);
            c1 = c2;//更新当前层的节点数
        }
        return result;
    }

    @Override
    public String toString() {
        List<List<Object>> lists = levelOrder(root);
        return lists.toString();
    }


}

/**
 B树(Balance) <br>
 B树的高度低,磁盘IO次数少,适合磁盘存储<br>
 100万数据,使用avl树需要log(100万)≈20层,而使用B树(最小度数500时),树高约3层
 */
class BTree {

    //度/阶:节点孩子数
    /*B树特性
    1.每个节点最多有m个孩子,m为阶
    2.除根与叶子,其他每个节点至少有ceil(m/2)个孩子
    3.所有叶子节点都在同一层
    4.每个非叶子节点由n个key和n+1个指针组成(有n+1个孩子)
    5.key为升序排列:节点内keys[i]<=keys[i+1] , p[i]指向key值位于keys[i]~keys[i+1]的子树
    孩子数: ceil(m/2) ~ m
    key数: ceil(m/2)-1 ~ m-1
     */
    static class BNode {
        int[] keys;
        BNode[] children;
        Object[] values;
        int keyNumber;//有效关键字数
        boolean leaf = true;//是否是叶子节点
        int t;//最小度数

        public BNode(int t) { //t>=2
            this.t = t;
            this.keys = new int[2 * t - 1];
            this.children = new BNode[2 * t];
            this.values = new Object[2 * t - 1];
        }

        public BNode(int[] keys) { //t>=2
            this.t = 2;
            this.keys = keys;
            this.values = new Object[keys.length];
            this.children = new BNode[keys.length + 1];
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        public BNode get(int key) {
            int i = 0;
            while (i < keyNumber) {
                if (keys[i] == key) {//找到
                    return this;
                }
                if (keys[i] > key) {//遇到更大的key,需要到下一层寻找
                    break;
                }
                i++;
            }
            if (leaf) {//已经到叶子节点
                return null;
            }
            //非叶子情况,到下一层寻找
            // 1.keys[i]>key 2.i==keyNumber
            return children[i].get(key);
        }

        public void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);//搬移元素
            keys[index] = key;
            keyNumber++;
        }

        public void insertChild(BNode child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);//搬移元素
            children[index] = child;
        }

        public void insertValue(Object value, int index) {
            System.arraycopy(values, index, values, index + 1, keyNumber - index);//搬移元素
            values[index] = value;
        }

        //删除key
        public int removeKey(int index) {
            int temp = keys[index];
            System.arraycopy(keys, index + 1, keys, index, keyNumber - index);
            keyNumber--;
            return temp;
        }

        public int removeLeftmostKey() {
            return removeKey(0);
        }

        public int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        //删除孩子
        public BNode removeChild(int index) {
            BNode temp = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber - index);//搬移
            children[keyNumber] = null;
            return temp;
        }

        public BNode removeLeftmostChild() {
            return removeChild(0);
        }

        public BNode removeRightmostChild() {
            return removeChild(keyNumber);
        }

        //删除值
        public Object removeValue(int index) {
            Object temp = values[index];
            System.arraycopy(values, index + 1, values, index, keyNumber - index);
            values[keyNumber] = null;
            return temp;
        }

        public Object removeLeftmostValue() {
            return removeValue(0);
        }

        public Object removeRightmostValue() {
            return removeValue(keyNumber - 1);
        }

        //找索引的左孩子
        public BNode childLeftBrother(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        public BNode childRightBrother(int index) {
            return index == keyNumber ? null : children[index + 1];
        }


        //把当前节点的key-value-child数据复制到target节点后
        public void moveToTarget(BNode target) {
            int start = target.keyNumber;
            if (!leaf) {
                System.arraycopy(children, 0, target.children, start, keyNumber + 1);
                Arrays.fill(children, null);
            }
            System.arraycopy(values, 0, target.values, start, keyNumber);
            System.arraycopy(keys, 0, target.keys, start, keyNumber);
            target.keyNumber += keyNumber;
        }
    }

    BNode root;
    int t;//最小度数
    final int MIN_keyNumber;//最小key数
    final int MAX_keyNumber;//最大key数

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new BNode(t);
        MAX_keyNumber = 2 * t - 1;
        MIN_keyNumber = t - 1;
    }

    public boolean contains(int key) {
        return root.get(key) != null;
    }

    public void put(int key, Object value) {
        doPut(root, key, value, null, 0);
    }

    /**
     @param node   待查找的节点
     @param key    插入位置
     @param value  插入值
     @param parent 父节点
     @param index  分裂时node节点的中间key插入到parent的位置
     */
    private void doPut(BNode node, int key, Object value, BNode parent, int index) {
        int i = 0;//寻找插入位置
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                node.values[i] = value;//找到相同的key,更新
                return;
            }
            if (node.keys[i] > key) {//遇到更大的key,需要到下一层寻找
                break;
            }
            i++;
        }
        if (node.leaf) {//已经到叶子节点,直接插入
            node.insertValue(value, i);
            node.insertKey(key, i);
        } else {
            //非叶子情况,到下一层寻找孩子节点
            doPut(node.children[i], key, value, node, i);
        }
        //检查分裂
        if (node.keyNumber == MAX_keyNumber) {
            split(node, parent, index);
        }
    }

    /**
     分裂,找到中间的key进行三段拆分<br>
     左侧不动,中间节点移动到父节点的index处,右侧的作为新节点插入到index+1处<br>
     例:<br>
     [[2] | [1],[3,4,5]-->[[2,4] | [1],[3],[5]]<br>

     @param left   要分裂的节点
     @param parent 父亲节点
     @param index  要分裂的节点是children[index]
     */
    public void split(BNode left, BNode parent, int index) {
        if (left == root) {//分裂根节点,创建新根取代原根
            BNode newRoot = new BNode(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            root = newRoot;
            parent = newRoot;
        }
        BNode right = new BNode(t);
        right.leaf = left.leaf;//新节点要插入到parent的children中,所以是同一层的
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);//t-1为中间索引
        System.arraycopy(left.values, t, right.values, 0, t - 1);
        if (!left.leaf) {
            //非叶子节点的分裂需要把t以后的孩子节点也拷贝过去
            System.arraycopy(left.children, t, right.children, 0, t);//t-1为中间索引
            for (int i = t; i <= left.keyNumber; i++) {
                left.children[i] = null;
            }
        }
        left.keyNumber = t - 1;
        right.keyNumber = t - 1;
        //插入中间节点
        int middleKey = left.keys[t - 1];
        Object middleValue = left.values[t - 1];
        parent.insertValue(middleValue, index);
        parent.insertKey(middleKey, index);
        parent.insertChild(right, index + 1);//右节点变孩子
    }


    public void remove(int key) {
        doRemove(null, root, 0, key);
    }

    /**
     1.叶子,没找到    return<br>
     2.叶子,找到     直接删除<br>
     3.非叶子,没找到   到下一层找<br>
     4.非叶子,找到    李代桃僵删后继<br>
     5.删除后keyNumber<MIN,不平衡<br>
     6.根节点<br>

     @param parent 父节点
     @param node   正在查找的节点
     @param index  正在查找的节点是parent.children[index]
     @param key    查找删除节点的key
     */
    public void doRemove(BNode parent, BNode node, int index, int key) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        //
        if (node.leaf) {
            if (!found(node, key, i)) {//1.叶子,没找到,直接返回
                return;
            } else {//2.叶子,找到,删除
                node.removeKey(i);
            }
        } else {
            if (!found(node, key, i)) {//3.非叶子,没找到,到下层寻找
                doRemove(node, node.children[i], i, key);
            } else {//4.非叶子,找到
                //找后继
                BNode s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                //李代桃僵
                int sKey = s.keys[0];
                node.keys[i] = sKey;
                doRemove(node, node.children[i + 1], i + 1, sKey);
            }
        }
        //依次出栈,检查平衡
        if (node.keyNumber < MIN_keyNumber) {
            balance(parent, node, index);
        }
    }

    /**
     旋转:被调整节点借父亲的key,父亲的key从左兄弟或右兄弟借key,如果兄弟有孩子,则将孩子过继给被调整节点<br>
     合并:左右兄弟不够借,进行节点合并,将父节点的对应key转移到左兄弟或者自身,然后与左兄弟或右兄弟进行合并

     @param parent 被调整节点的父节点
     @param node   被调整节点
     @param index  被调整节点是parent.children[index]
     */
    public void balance(BNode parent, BNode node, int index) {
        if (node == root) {
            //合并节点会使父节点key少一个
            //根节点key==0时需要使孩子成为根,没有孩子则不变
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        //旋转
        BNode left = parent.childLeftBrother(index);
        BNode right = parent.childRightBrother(index);
        if (left != null && left.keyNumber > MIN_keyNumber) {//左边兄弟富裕,右旋借key
            //兄弟的一个key上去,父亲的一个key下来补足自身
            node.insertValue(parent.values[index - 1], 0);
            node.insertKey(parent.keys[index - 1], 0);//index-1为node的前驱key(左)
            if (!left.leaf) {//左边兄弟如果有孩子,需要将对应key的孩子也过继
                node.insertChild(left.removeRightmostChild(), 0);//left最右边的孩子过继到node的最左边
            }
            parent.values[index - 1] = left.removeRightmostValue();
            parent.keys[index - 1] = left.removeRightmostKey();//父亲的key补齐
            return;
        }
        if (right != null && right.keyNumber > MIN_keyNumber) { //右边兄弟富裕,左旋借key
            //兄弟的一个key上去,父亲的一个key下来补足自身
            node.insertValue(parent.values[index], node.keyNumber);
            node.insertKey(parent.keys[index], node.keyNumber);//index指向为node的key(右)
            if (!right.leaf) {//右边兄弟如果有孩子,需要将对应key的孩子也过继
                node.insertChild(right.removeLeftmostChild(), node.keyNumber);//right最左边的孩子过继到node的最右边
            }
            parent.values[index] = right.removeLeftmostValue();
            parent.keys[index] = right.removeLeftmostKey();
            return;
        }
        //合并
        if (left != null) {//自己向左兄弟合并
            // 拿一个父节点的key
            parent.removeChild(index);
            left.insertValue(parent.removeValue(index - 1), left.keyNumber);
            left.insertKey(parent.removeKey(index - 1), left.keyNumber);
            //复制key-value-children到left上
            node.moveToTarget(left);
        } else {//右兄弟向自己合并
            parent.removeChild(index + 1);
            node.insertValue(parent.removeValue(index), node.keyNumber);
            node.insertKey(parent.removeKey(index), node.keyNumber);
            assert right != null;//如果没有左右孩子,那么只能是根节点
            right.moveToTarget(node);
        }
        //递归在doRemove方法里做了,这里不需要递归检查
    }

    public static boolean found(BNode node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }

    public void travel() {
        doTravel(root);
    }

    public void doTravel(BNode node) {
        if (node == null) {
            return;
        }
        int i = 0;
        for (; i < node.keyNumber; i++) {
            doTravel(node.children[i]);
            System.out.println(node.keys[i]);
        }
        doTravel(node.children[i]);
    }
}
