package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 3/20/16.
 */
public class HouseRouberThree {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }

        return dfs(root, 0, true);
    }

    private int dfs(TreeNode root, int sum, boolean canVisited) {
        if (root == null) {
            return 0;
        }
        int containRoot = sum;
        if (canVisited) {
            containRoot = sum + root.val;
            int left = dfs(root.left, 0, false);
            int right = dfs(root.right, 0, false);
            containRoot = containRoot + left + right;
        }

        int notContainRoot = sum;
        int left = dfs(root.left, 0, true);
        int right = dfs(root.right, 0, true);
        notContainRoot = notContainRoot + left + right;

        return canVisited ? Math.max(containRoot, notContainRoot) : notContainRoot;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        HouseRouberThree rouber = new HouseRouberThree();
        System.out.println(rouber.rob(root));
    }
}

