package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 3/20/16.
 */
public class BinaryTreeLongestConsecutive {
    private int maxLength = Integer.MIN_VALUE;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        dfs(root, root.val - 1, 0);
        return maxLength;
    }

    private void dfs(TreeNode root, int prev, int currLength) {
        if (root == null) {
            return;
        }
        if (prev == root.val - 1) {
            maxLength = Math.max(maxLength, currLength + 1);
            dfs(root.left, root.val, currLength + 1);
            dfs(root.right, root.val, currLength + 1);
        } else {
            dfs(root.left, root.val, 1);
            dfs(root.right, root.val, 1);
        }
    }

    public static void main(String[] args) {
        BinaryTreeLongestConsecutive solution = new BinaryTreeLongestConsecutive();

//        TreeNode root = new TreeNode(1);
//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(2);
//        root.right.right = new TreeNode(4);
//        root.right.right.right = new TreeNode(5);
//
//        System.out.println(solution.longestConsecutive(root));


        TreeNode root1 = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(2);
        root1.right.left.left = new TreeNode(1);
        System.out.println(solution.longestConsecutive(root1));
    }
}
