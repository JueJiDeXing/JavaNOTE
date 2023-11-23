package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.Node.TreeNode;

import java.util.Arrays;

public class 前序and中序构建 {
    public static void main(String[] args) {

    }

    /*
    示例:
        preOrder={1,2,4,3,6,7} // 输入的节点值是不重复的
        inOrder={4,2,1,6,3,7}
    树:
             1
          2     3
        4      6  7
    思路:前序遍历第一个为根,根节点在中序遍历时,左边为左子树,右边为右子树,然后分解为2个子问题

     示例:
         输入:preOrder={1,2,4,3,6,7},inOrder={4,2,1,6,3,7}
         根为1
         pre            in
     左  2,4            4,2
     右  3,6,7          6,3,7

     左: preOrder={2,4},inOrder={4,2}   右:preOrder={3,6,7},inOrder={6,3,7}
        根为2,根据中序,4为2的左子树            根为3,根据中序,6为3的左子树,7为右子树
     */
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0) return null;//两数组长度是一致的,所以添加只写一个即可
        //根节点
        int rootValue = preOrder[0];
        TreeNode root = new TreeNode(rootValue);
        //左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                int[] inLeft = Arrays.copyOfRange(inOrder, 0, i);//拷贝,左闭右开
                int[] inRight = Arrays.copyOfRange(inOrder, i + 1, inLeft.length);
                int[] preLeft = Arrays.copyOfRange(preOrder, 1, i + 1);
                int[] preRight = Arrays.copyOfRange(preOrder, i + 1, preOrder.length);
                root.left = buildTree(preLeft, inLeft);
                root.right = buildTree(preRight, inRight);
                break;
            }
        }
        return root;
    }

    //优化----------
    /*
    pre root la...lm rb...rn
    in  lc...lm root rd...rn
    */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 3001);

    }

    int pre = 0;
    int in = 0;

    /**
     @param preorder 前序遍历
     @param inorder  中序遍历
     @param stop     当前寻找的根节点值,初始值为3001表示查找整个树
     @return 返回从索引in到中序遍历到stop停止的子树的根节点
     */
    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre == preorder.length) {//树查找完了
            return null;
        }
        if (inorder[in] == stop) {//3.找到最小子树根节点,停止
            in++;
            return null;
        }
        int root_val = preorder[pre];//1.取当前子树根节点
        pre++;
        TreeNode root = new TreeNode(root_val);
        //中序遍历的根节点左边为左子树,右边为右子树
        root.left = build(preorder, inorder, root_val);//2.在inorder寻找根节点(左)
        root.right = build(preorder, inorder, stop);
        return root;
    }
}
