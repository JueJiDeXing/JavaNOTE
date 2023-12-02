package 数据结构与算法.数据结构.二叉树.二叉树问题;

public class 和为target的路径 {
    /*

#include "MyTree.c"
#include <stdio.h>


    void printStack(SqStack stack) {
        printf("path: ");
        int len = StackLength(stack);
        for (int i = 0; i < len; i++) {
            printf("%c", stack.base[i]->data);
            if (i != len - 1) {
                printf("->");
            }
        }
        printf("\n");

    }

    void search(BiTNode *node, int target, SqStack *stack) {
        //当node为叶子节点,且target为0时说明找到路径
        if (node->left == NULL && node->right == NULL && target == 0) {
            printStack(*stack);
            return;
        }
        //如果node为叶子节点,而target不为0
        //或者target小于等于0,而node不是叶子节点
        //出栈回溯
        if (node->left == NULL && node->right == NULL || target <= 0) {
            return;
        }
        BiTree _;//pop函数的占位参数
        //搜索左边
        if (node->left != NULL) {
            Push(stack, node->left);
            search(node->left, target - node->left->data + '0', stack);
            Pop(stack, &_);//回溯
        }
        //搜索右边
        if (node->right != NULL) {
            Push(stack, node->right);
            search(node->right, target - node->right->data + '0', stack);
            Pop(stack, &_);//回溯
        }

    }

    void printPath(BiTNode T, int target) {
        SqStack stack;//用栈存储路径
        InitStack(&stack);
        Push(&stack, &T);//根节点必须入栈

        search(&T, target - T.data + '0', &stack);//搜索左右,+'0'是因为存储的数据为字符类型,要转为数字
    }

     */
}
