package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 3/19/16.
 */
public class LargestSubBSTTree {
    class ResNode {
        boolean isBST;
        int size;
        int smallest;
        int largest;
        public ResNode() {
            isBST = true;
            size = 0;
            smallest = Integer.MAX_VALUE;
            largest = Integer.MIN_VALUE;
        }
        public ResNode(boolean flag, int s) {
            isBST = flag;
            size = s;
            smallest = Integer.MAX_VALUE;
            largest = Integer.MIN_VALUE;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        ResNode curr = dfs(root);
        return curr.size;
    }

    private ResNode dfs(TreeNode root) {
        if (root == null) {
            return new ResNode();
        }
        if (root.left == null && root.right == null) {
            ResNode tempRes = new ResNode(true, 1);
            tempRes.largest = root.val;
            tempRes.smallest = root.val;
            return tempRes;
        }
        ResNode left = dfs(root.left);
        ResNode right = dfs(root.right);

        ResNode res = new ResNode();
        res.largest = root.val;
        res.smallest = root.val;
        if (left.isBST && right.isBST) {
            //left subTree is empty, go to the right subtree

            if (left.size == 0) {
                if (root.val >= right.smallest) {
                    res.isBST = false;
                    res.size = right.size;
                } else {

                    res.size = right.size + 1;
                    res.smallest = root.val;
                    res.largest = right.largest;
                }
            } else if (right.size == 0) {

                if (root.val <= left.largest) {

                    res.isBST = false;
                    res.size = left.size;
                } else {

                    res.size = left.size + 1;
                    res.largest = root.val;
                    res.smallest = left.smallest;
                }
            } else {
                if (root.val > left.largest && root.val < right.smallest) {
                    System.out.println("asdfadsf " + left.largest + "\t" + right.smallest);
                    res.size = left.size + right.size + 1;
                    res.smallest = left.smallest;
                    res.largest = right.largest;
                } else {
                    res.size = Math.max(left.size, right.size);
                    res.isBST = false;
                }
            }

            return res;
        }

        res.isBST = false;
        System.out.println(root.val);
        res.size = Math.max(left.size, right.size);

        return res;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        node4.left = node1;
        node3.left = node2;
        node3.right = node4;

        LargestSubBSTTree solution = new LargestSubBSTTree();
        ResNode res = solution.dfs(node3);
        System.out.println(res.size);
    }
}
