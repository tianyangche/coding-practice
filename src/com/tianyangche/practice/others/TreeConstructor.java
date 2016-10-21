package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 3/24/16.
 */
public class TreeConstructor {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int a, int b, int[] inorder, int c, int d) {
        System.out.println(a + "\t" + b + "\t" + c + "\t" + d);
        if (a > b || c > d) {
            return null;
        }
        if (a == b) {
            return new TreeNode(preorder[a]);
        }

        TreeNode root = new TreeNode(preorder[a]);

        int i = c;
        while (inorder[i] != preorder[a]) {
            i++;
        }
        TreeNode left = null;
        TreeNode right = null;
        if (i == c) {
            right = buildTree(preorder, a + 1, b, inorder, c + 1, d);
        } else {
//            int j = a + 1;
//
//            while (preorder[j] != inorder[i - 1]) {
//                j++;
//            }

            left = buildTree(preorder, a + 1, a + i - c, inorder, c, i - 1);
            right = buildTree(preorder, a + i - c + 1, b, inorder, i + 1, d);
        }
        root.left = left;
        root.right = right;

        return root;
    }

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }

        return buildTree2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }


    private TreeNode buildTree2(int[] inorder, int a, int b, int[] postorder, int c, int d) {
        System.out.println(a + "\t" + b + "\t" + c + "\t" + d);
        if (a > b || c > d) {
            return null;
        }
        if (a == b) {
            return new TreeNode(inorder[a]);
        }

        TreeNode root = new TreeNode(inorder[a]);

        int i = a;
        while (inorder[i] != postorder[d]) {
            i++;
        }
        TreeNode left = null;
        TreeNode right = null;
        if (i == c) {
            right = buildTree(inorder, i + 1, b, postorder, b + a - i, b - 1);
        } else {
//            int j = a + 1;
//
//            while (preorder[j] != inorder[i - 1]) {
//                j++;
//            }

            left = buildTree(inorder, a, i - 1, postorder, c, d + a -i - 1);
            right = buildTree(inorder, i + 1, b, postorder, d + a - i, d - 1);
        }
        root.left = left;
        root.right = right;

        return root;
    }


    public static void main(String[] args) {
        TreeConstructor constructor = new TreeConstructor();
        int[] inorder = {1, 3, 4, 5, 7, 8, 9};
        int[] postorder = {1, 4, 3, 7, 9, 8, 5};
        constructor.buildTree2(inorder, postorder);

    }
}
