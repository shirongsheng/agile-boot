package com.shirs.agileboot.modules.study.tree;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BinaryTree {

    private BinaryTree left;

    private BinaryTree right;

    private BinaryTree root;

    private Object data;

    private List<BinaryTree> datas;

    public BinaryTree() {

    }

    public BinaryTree(BinaryTree left, BinaryTree right, Object data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BinaryTree(Object data) {
        this(null, null, data);
    }

    public void create(Object[] objects) {
        datas = new ArrayList<>();
        for (Object object : objects) {
            datas.add(new BinaryTree(object));
        }
        root = datas.get(0);
        for (int i = 0; i < objects.length / 2; i++) {
            datas.get(i).left = datas.get(i * 2 + 1);
            if (i * 2 + 2 < datas.size())
                datas.get(i).right = datas.get(i * 2 + 2);
        }
    }

    public void preOrder(BinaryTree root) {
        if (null != root) {
            System.out.print(root.data + ",");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void midOrder(BinaryTree root) {
        if (null != root) {
            midOrder(root.left);
            System.out.print(root.data + ",");
            midOrder(root.right);
        }
    }

    public void afterOrder(BinaryTree root) {
        if (null != root) {
            afterOrder(root.left);
            afterOrder(root.right);
            System.out.print(root.data + ",");
        }
    }

    public static void main(String[] args) {
        Object[] a = {0, 1, 2, 3, 4, 5, 6, 7};
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.create(a);
        binaryTree.preOrder(binaryTree.root);
        System.out.println();
        binaryTree.midOrder(binaryTree.root);
        System.out.println();
        
        binaryTree.afterOrder(binaryTree.root);
    }
}
