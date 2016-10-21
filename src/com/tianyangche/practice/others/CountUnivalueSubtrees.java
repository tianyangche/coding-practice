package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 3/19/16.
 */
public class CountUnivalueSubtrees {
    class ResNode {
        int uniValue;
        int size;
        boolean meetsRequirements;
        public ResNode() {
            uniValue = -1;
            size = 0;
            meetsRequirements = true;
        }
        public ResNode(int value) {
            uniValue = value;
            size = 1;
            meetsRequirements = true;
        }
    }
    public int countUnivalSubtrees(TreeNode root) {
        ResNode res = dfs(root);
        return res.uniValue;
    }

    private ResNode dfs(TreeNode root) {
        if (root == null) {
            return new ResNode();
        }
        if (root.left == null && root.right == null) {
            return new ResNode(root.val);
        }
        ResNode res = new ResNode(root.val);
        if (root.left == null) {
            ResNode right = dfs(root.right);
            if (right.meetsRequirements && res.uniValue == right.uniValue) {
                res.size = right.uniValue + 1;
            } else {
                res.meetsRequirements = false;
                res.size = right.uniValue;
            }
        } else if (root.right == null) {
            ResNode left = dfs(root.left);
            if (left.meetsRequirements && res.uniValue == left.uniValue) {
                res.size = left.uniValue + 1;
            } else {
                res.meetsRequirements = false;
                res.size = left.uniValue;
            }
        } else {
            ResNode left = dfs(root.left);
            ResNode right = dfs(root.right);
            if (left.meetsRequirements && right.meetsRequirements && res.uniValue == left.uniValue && res.uniValue == right.uniValue) {
                res.size = left.size + right.size + 1;
            } else {
                res.size = left.size + right.size;
                res.meetsRequirements = false;
            }
        }

        return res;
    }
}
