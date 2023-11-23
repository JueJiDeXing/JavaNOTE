package 数据结构与算法.数据结构.二叉树;

import org.junit.Test;
import 数据结构与算法.数据结构.二叉树.Node.RBNode;
import 数据结构与算法.数据结构.二叉树.平衡搜索树.RBTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static 数据结构与算法.数据结构.二叉树.Node.RBNode.Color.BLACK;
import static 数据结构与算法.数据结构.二叉树.Node.RBNode.Color.RED;

public class TestRBTree {
    @Test
    public void put() {
        RBTree tree = new RBTree();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int key = random.nextInt();
            keys.add(key);
            tree.put(key, null);
            assertTrue(tree.contains(key));
            isValid(tree);
        }

        for (Integer key : keys) {
            tree.remove(key);
            assertFalse(tree.contains(key));
            isValid(tree);
        }

        assertNull(tree.root);
    }

    @Test
    public void remove() {
        RBTree tree = new RBTree();
        tree.root = new RBNode(8, BLACK,
                new RBNode(4, BLACK,
                        new RBNode(2, RED, new RBNode(1, BLACK), new RBNode(3, BLACK)),
                        new RBNode(6, RED, new RBNode(5, BLACK), new RBNode(7, BLACK))),
                new RBNode(12, BLACK,
                        new RBNode(10, RED, new RBNode(9, BLACK), new RBNode(11, BLACK)),
                        new RBNode(14, RED, new RBNode(13, BLACK), new RBNode(15, BLACK))
                )
        );
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        Collections.shuffle(list);
        for (Integer key : list) {
            tree.remove(key);
            assertFalse(tree.contains(key));
            isValid(tree);
        }
        assertNull(tree.root);
    }

    @Test
    public void test() {
        RBTree tree = new RBTree();
        tree.root = new RBNode(8, BLACK,
                new RBNode(4, BLACK,
                        new RBNode(2, RED, new RBNode(1, BLACK), new RBNode(3, BLACK)),
                        new RBNode(6, RED, new RBNode(5, BLACK), new RBNode(7, BLACK))),
                new RBNode(12, BLACK,
                        new RBNode(10, RED, new RBNode(9, BLACK), new RBNode(11, BLACK)),
                        new RBNode(14, RED, new RBNode(13, BLACK), new RBNode(15, BLACK))
                )
        );
        isValid(tree);
    }

    private void isValid(RBTree tree) {
        if (tree.root == null) {
            return;
        }
        // 规则 4 根节点必须是黑
        if (tree.root.color != BLACK) {
            throw new AssertionError("root's color must BLACK");
        }
        validateRedBlackInvariant(null, tree.root, 0, new MutableHeight());
    }

    private static class MutableHeight {
        private Integer value;
    }

    private void validateRedBlackInvariant(
            RBNode parent, RBNode node, int thisHeight, MutableHeight height) {
        // 规则5 每条路径黑色相等 node == null 表示一条路径走到头
        if (node == null) {
            // First NIL node?
            if (height.value == null) {
                height.value = thisHeight;
            } else if (height.value != thisHeight) {
                throw new AssertionError(
                        "Black-height rule violated (height.value = "
                                + height.value
                                + "; thisHeight = "
                                + thisHeight
                                + "; parent = "
                                + parent.key
                                + ")");
            }
            return;
        }

        // Count black nodes on path
        if (node.color == BLACK) {
            thisHeight++;
        }

        // 规则3 红色不相邻
        else if (parent != null && parent.color == RED) {
            throw new AssertionError("Node " + node.key + " and its parent " + parent.key + " are both red");
        }

        // We're using the simplified approach of not forcing the root to be black
        validateRedBlackInvariant(node, node.left, thisHeight, height);
        validateRedBlackInvariant(node, node.right, thisHeight, height);
    }
}
