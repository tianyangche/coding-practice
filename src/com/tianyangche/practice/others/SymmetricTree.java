package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 3/25/16.
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode leftRoot, TreeNode rightRoot) {
        if (leftRoot == null && rightRoot == null) {
            return true;
        }
        if (leftRoot == null || rightRoot == null) {
            return false;
        }
        return isSymmetric(leftRoot.left, rightRoot.right) && isSymmetric(leftRoot.right, rightRoot.left);
    }
}
